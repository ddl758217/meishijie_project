package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.adapter.MoreTabLayoutAdapter;
import ddl.dalong.com.meishijie_project.bean.MoreTablayoutbean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.fragment.tab_fragment.MoreFragment;

public class MoreActivity extends AppCompatActivity {

    private List<MoreTablayoutbean.TimeShopBean> Tab_more = new ArrayList<>();
//    private MoreTablayoutbean moreTablayoutbean = new MoreTablayoutbean();
    public List<Fragment> listfragment = new ArrayList<>();

    private Context mContext;
    @BindView(R.id.detail__more_back_ib)
    ImageButton mIB_more;
    @BindView(R.id.viewPager_more)
    ViewPager mViewPager_more;
    @BindView(R.id.tablayout_more)
    TabLayout mTabLayout_more;
    @BindView(R.id.ll_more)
    LinearLayout mLinearLayout_more;
    @BindView(R.id.ll_times)
    LinearLayout mLinearLayout_times;
    private String des;
    private MoreTabLayoutAdapter moreTabLayoutAdapter;
    private SharedPreferences mSharedPreferences;
    private List<String> deses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        mContext = this;
        setupTabLayout();

    }


    private void setupTabLayout() {
        setupTabData();
        mTabLayout_more.setTabMode(TabLayout.MODE_FIXED);
//        //防止tab太多，都拥挤在一起
//        mTabLayout_more.setTabMode(TabLayout.MODE_SCROLLABLE);
        moreTabLayoutAdapter = new MoreTabLayoutAdapter(getSupportFragmentManager(),Tab_more,listfragment);
        mViewPager_more.setAdapter(moreTabLayoutAdapter);
        mTabLayout_more.setupWithViewPager(mViewPager_more);
    }

    private void setupTabData() {
        final Map<String,String> Tab_layout = new HashMap<>();
        Tab_layout.put("TIME", Contans.TIME);
        Tab_layout.put("PAGER",Contans.PAGER);
        OkHttpTool.newInstance().post(Tab_layout).start(Contans.TAB_SHOPP).callback(new IOKCallBack() {

            @Override
            public void success(String result) {
                Gson gson = new Gson();
                MoreTablayoutbean more_tablayout = gson.fromJson(result, MoreTablayoutbean.class);
                List<MoreTablayoutbean.TimeShopBean> time_shop = more_tablayout.getTime_shop();
                for (int i = 0; i < mLinearLayout_times.getChildCount(); i++) {
                    TextView times = (TextView) mLinearLayout_times.getChildAt(i);
                    times.setText(time_shop.get(i).getTime());
                }

                for (int i = 0; i < mLinearLayout_more.getChildCount(); i++) {
                    TextView des = (TextView) mLinearLayout_more.getChildAt(i);
                    deses.add(time_shop.get(i).getDes());
                    des.setText(time_shop.get(i).getDes());
                }
                Tab_more.addAll(time_shop);
                /**
                 * 初始化指南的Fragment
                 */
                initFragment(Tab_more);
                moreTabLayoutAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initFragment(List<MoreTablayoutbean.TimeShopBean> tab_more) {
        for (int i = 0; i < tab_more.size(); i++) {
            listfragment.add(MoreFragment.newInstance(deses.get(i),""+i));
        }
    }
}
