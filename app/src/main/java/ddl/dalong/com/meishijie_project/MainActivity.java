package ddl.dalong.com.meishijie_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.fragment.DiscoverFragment;
import ddl.dalong.com.meishijie_project.fragment.MineFragment;
import ddl.dalong.com.meishijie_project.fragment.RecommendFragment;
import ddl.dalong.com.meishijie_project.fragment.ShihuaFragment;
import ddl.dalong.com.meishijie_project.fragment.StoreFragment;
import ddl.dalong.com.meishijie_project.ui.SplashActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @BindView(R.id.rg_button)
    RadioGroup mRadioGroup;
    private RecommendFragment recommendFragment;
    private DiscoverFragment discoverFragment;
    private StoreFragment storeFragment;
    private ShihuaFragment shihuaFragment;
    private MineFragment mineFragment;
    private Fragment curFragment;
    private AlertDialog.Builder builder;
    private AlertDialog isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SplashActivity.activityList.add(this);
         fragmentManager = getSupportFragmentManager();
         initFragment();
         initListener();
    }

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb_recommend:
                        ShowFragment(recommendFragment);
                        break;
                    case R.id.rb_discover:
                        ShowFragment(discoverFragment);
                        break;
                    case R.id.rb_store:
                        ShowFragment(storeFragment);
                        break;
                    case R.id.rb_shihua:
                        ShowFragment(shihuaFragment);
                        break;
                    case R.id.rb_mine:
                        ShowFragment(mineFragment);
                        break;
                }
            }
        });

    }

    private void initFragment() {
        recommendFragment = RecommendFragment.newInstance();
        discoverFragment = DiscoverFragment.newInstance();
        storeFragment = StoreFragment.newInstance();
        shihuaFragment = ShihuaFragment.newInstance();
        mineFragment = MineFragment.newInstance();
        fragmentList.add(recommendFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(storeFragment);
        fragmentList.add(shihuaFragment);
        fragmentList.add(mineFragment);
        /**
         * 刚进程序初始化的界面
         */
        ShowFragment(recommendFragment);
        mRadioGroup.check(R.id.rb_recommend);
    }

    private void ShowFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(curFragment!=null&&curFragment.isAdded()){
            transaction.hide(curFragment);
        }
        if(!fragment.isAdded()){
            transaction.add(R.id.ll_fragment_main,fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
        curFragment = fragment;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            // 创建退出对话框
         builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null, false);
            builder.setView(view);
            Button btn_quding = (Button) view.findViewById(R.id.btn_queding);
            Button btn_quxiao = (Button) view.findViewById(R.id.btn_quxiao);
            isExit = builder.create();
            btn_quding.setOnClickListener(this);
            btn_quxiao.setOnClickListener(this);
//            // 设置对话框标题
//            isExit.setTitle("系统提示");
//            // 设置对话框消息
//            isExit.setMessage("确定要退出美食杰吗");
//
//            // 添加选择按钮并注册监听
//            isExit.setButton(DialogInterface.BUTTON_POSITIVE,"确定",listener);
//            isExit.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", listener);
            // 显示对话框
            isExit.show();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_queding:
                for(int i = 0; i< SplashActivity.activityList.size(); i++){
                        SplashActivity.activityList.get(i).finish();
                    }
                    SplashActivity.activityList.clear();
                break;
            case R.id.btn_quxiao:
                isExit.dismiss();
                break;
        }
    }

//    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialogInterface, int which) {
//            switch (which){
//                case AlertDialog.BUTTON_POSITIVE:
//                    for(int i = 0; i< SplashActivity.activityList.size(); i++){
//                        SplashActivity.activityList.get(i).finish();
//                    }
//                    SplashActivity.activityList.clear();
//            }
//        }
//    };

}
