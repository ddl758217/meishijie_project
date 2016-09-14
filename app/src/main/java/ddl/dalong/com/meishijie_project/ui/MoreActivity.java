package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;

public class MoreActivity extends AppCompatActivity {
    private Context mContext;
    @BindView(R.id.detail__more_back_ib)
    ImageButton mIB_more;
    @BindView(R.id.viewPager_more)
    ViewPager mViewPager_more;
    @BindView(R.id.tablayout_more)
    TabLayout mTabLayout_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        mContext = this;


    }


}
