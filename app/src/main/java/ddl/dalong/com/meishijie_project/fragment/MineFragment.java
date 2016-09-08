package ddl.dalong.com.meishijie_project.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.adapter.MineAdapter;
import ddl.dalong.com.meishijie_project.bean.MineBean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.ui.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ddl";
    private Context mContext;
    private List<MineBean.ObjBean> datas = new ArrayList<>();
    private MineAdapter mineAdapter;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @BindView(R.id.lv_show)
    ListView mListView;
    @BindView(R.id.slv_view)
    ScrollView mScrollView;
    @BindView(R.id.civ_show)
    CircleImageView mCircleImageView;
    @BindView(R.id.tv_denglu)
    TextView tv_denglu;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.tv_jifen)
    TextView tv_jifen;
    @BindView(R.id.tv_xiaoxi)
    TextView tv_xiaoxi;
    @BindView(R.id.tv_maicai)
    TextView tv_maicai;
    @BindView(R.id.tv_lixianbao)
    TextView tv_lixianbao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        setupListView();
        initListener();
        return view;
    }

    private void initListener() {
        mCircleImageView.setOnClickListener(this);
        tv_denglu.setOnClickListener(this);
        tv_guanzhu.setOnClickListener(this);
        tv_jifen.setOnClickListener(this);
        tv_xiaoxi.setOnClickListener(this);
        tv_maicai.setOnClickListener(this);
        tv_lixianbao.setOnClickListener(this);
    }

    private void setupListView() {
        mineAdapter = new MineAdapter(mContext,datas);
        initData();
    }

    private void initData() {
        OkHttpTool.newInstance().start(Contans.MINE_ACTIVITY).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                MineBean mineBean = gson.fromJson(result, MineBean.class);
                Log.d(TAG, "success: -----"+result);
                datas.addAll(mineBean.getObj());
//                List<MineBean.ObjBean> beanList = mineBean.getObj();
//                for (int i = 0; i < beanList.size() ; i++) {
//                    datas.add(beanList.get(i));
//                }
                mineAdapter.notifyDataSetChanged();
                mScrollView.scrollTo(10,10);
            }
        });
       mListView.setAdapter(mineAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.civ_show:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_denglu:
                Intent intent1 = new Intent(mContext, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_jifen:
                Intent intent3= new Intent(mContext, LoginActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_xiaoxi:
                Intent intent4 = new Intent(mContext, LoginActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_maicai:
                Intent intent5 = new Intent(mContext, LoginActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_lixianbao:
                Intent intent6 = new Intent(mContext, LoginActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
