package ddl.dalong.com.meishijie_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
}
