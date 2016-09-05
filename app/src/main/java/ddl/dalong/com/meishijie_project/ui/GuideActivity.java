package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.MainActivity;
import ddl.dalong.com.meishijie_project.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    private Context mContext;
    private List<Integer> datas = new ArrayList<>();

    @BindView(R.id.vp_guide)
    ViewPager mViewPager;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SplashActivity.activityList.add(this);
        ButterKnife.bind(this);
        mContext = this;
        setupViewPager();
    }

    private void setupViewPager() {
//        initData();
        datas.add(R.drawable.frist);
        datas.add(R.drawable.second);
        datas.add(R.drawable.thread);
        myPagerAdapter = new MyPagerAdapter();
        mViewPager.setAdapter(myPagerAdapter);
        myPagerAdapter.notifyDataSetChanged();
    }

//    private void initData() {
//
//
//    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return datas==null?0:datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.banner_view,container,false);
            ImageView mImageView = (ImageView) view.findViewById(R.id.iv_item_banner);
            mImageView.setBackgroundResource(datas.get(position));
            mImageView.setTag(position);
            container.addView(mImageView);
            return mImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }

}
