package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
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
import ddl.dalong.com.meishijie_project.adapter.HeaderViewAdapter;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.fragment.tab_fragment.BuyerFragment;
import ddl.dalong.com.meishijie_project.fragment.tab_fragment.ShoppingFragment;

public class TopdetailObjActivity extends AppCompatActivity {
    private TextView tv_total_store,tv_store,tv_obj_title,tv_obj_descr,tv_obj_price,tv_obj_guige,tv_obj_postage;

    private List<TopdetailBean.ObjBean.ImagesBean> obj_images = new ArrayList<>();

    private TopdetailBean topdetailBean = new TopdetailBean();

    private List<Fragment> fragmentList = new ArrayList<>();
    private TabAdaper tabAdaper;
    private Context mContext;
    @BindView(R.id.vp_header_view)
    ViewPager mViewPager;
    @BindView(R.id.ll_indtor_dots)
    LinearLayout mLlDots;
    private String goodsSource;
    private String i;
    private HeaderViewAdapter headerViewAdapter;
    private Intent intent;
    private boolean isAutoScoll=true;
    @BindView(R.id.view_pager)
    ViewPager obj_mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private int count = 0;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(count>Integer.MAX_VALUE){
                count = 0;
            }
            mViewPager.setCurrentItem(count%4);
            count++;
            if(isAutoScoll){
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topdetail_obj);
        mContext = this;
        ButterKnife.bind(this);
        intent = getIntent();
        goodsSource = intent.getStringExtra("goodsSource");
        i = intent.getStringExtra("id");
        setupViewPager();
        setupTablayout();
        initTextView();

    }

    private void initTextView() {
        tv_total_store = (TextView) findViewById(R.id.tv_total_store);
        tv_store = (TextView) findViewById(R.id.tv_store );
        tv_obj_title = (TextView) findViewById(R.id.tv_obj_title);
        tv_obj_descr = (TextView) findViewById(R.id.tv_obj_descr);
        tv_obj_price = (TextView) findViewById(R.id.tv_obj_price);
        tv_obj_guige = (TextView) findViewById(R.id.tv_obj_guige);
        tv_obj_postage = (TextView) findViewById(R.id.tv_obj_postage);
        setupDataTexView();

    }

    private void setupDataTexView() {
        Map<String,String> list_top = new HashMap<>();
        list_top.put("goodsSource",goodsSource);
        list_top.put("i",i);
        OkHttpTool.newInstance().post(list_top).start(Contans.STORE_AT_TOP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopdetailBean tailBean = gson.fromJson(result, TopdetailBean.class);
                topdetailBean = tailBean;
                tv_total_store.setText("仅限"+topdetailBean.getObj().getTotal_store()+"份");
//                tv_store.setText(topdetailBean.getObj().getStore());
                tv_obj_title.setText(topdetailBean.getObj().getTitle());
                tv_obj_descr.setText(topdetailBean.getObj().getDescr());
                tv_obj_price.setText("￥"+topdetailBean.getObj().getPrice());
                tv_obj_guige.setText(topdetailBean.getObj().getGuige());
                tv_obj_postage.setText(topdetailBean.getObj().getPostage());
            }
        });
    }

    private void setupTablayout() {
        tabAdaper = new TabAdaper(getSupportFragmentManager());
        obj_mViewPager.setAdapter(tabAdaper);
        mTabLayout.setupWithViewPager(obj_mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setupViewPager() {
        if(obj_images!=null){
        headerViewAdapter = new HeaderViewAdapter(obj_images,mContext);
        loadHeaderViewData();
        initDots();
        initListener();
        }
    }

    private void initDots() {
        int childCount = mLlDots.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View dots = mLlDots.getChildAt(i);
            dots.setEnabled(false);
        }
        View fristView = mLlDots.getChildAt(0);
        fristView.setEnabled(true);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mLlDots.getChildCount(); i++) {
                    mLlDots.getChildAt(i).setEnabled(false);
                }
                View dot = mLlDots.getChildAt(position);
                dot.setEnabled(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //按下时，自动播放停止
                        isAutoScoll = false;
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起时，恢复自动播放
                        isAutoScoll=true;
                        mHandler.sendEmptyMessageDelayed(1,1000);
                        break;
                }
                return false;
            }
        });
    }

    private void loadHeaderViewData() {
        Map<String,String> list_top = new HashMap<>();
        list_top.put("goodsSource",goodsSource);
        list_top.put("i",i);
        OkHttpTool.newInstance().post(list_top).start(Contans.STORE_AT_TOP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopdetailBean topdetailBean = gson.fromJson(result, TopdetailBean.class);
                obj_images.addAll(topdetailBean.getObj().getImages());
                headerViewAdapter.notifyDataSetChanged();
            }
        });
        mViewPager.setAdapter(headerViewAdapter);
    }



 //fragment的适配器类
    class TabAdaper extends FragmentStatePagerAdapter {

        // 标题数组
        String[] titles = {"商品详情", "买家留言"};

        public TabAdaper(FragmentManager fm) {
            super(fm);
            fragmentList.add(ShoppingFragment.newInstance(goodsSource,i));
            fragmentList.add(BuyerFragment.newInstance(goodsSource,i));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}