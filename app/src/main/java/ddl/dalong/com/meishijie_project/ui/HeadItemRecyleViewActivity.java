package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
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
import ddl.dalong.com.meishijie_project.adapter.CategoryAdapter;
import ddl.dalong.com.meishijie_project.bean.CategoryBeaner;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class HeadItemRecyleViewActivity extends AppCompatActivity {

    private Context mContext;
    private List<CategoryBeaner.GoodsListBean> CategoryBeanerlist = new ArrayList<>();

    @BindView(R.id.ib_mCategory_detail_back)
    ImageButton imageButton;
    @BindView(R.id.gv_mCategory_ptrgv)
    GridView mGridView_ptrgy;
    @BindView(R.id.tv_mCategory_title)
    TextView mTextView_title;
    private CategoryAdapter categoryAdapter;
    private CategoryBeaner categoryBeaner;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_item_recyle_view);
        ButterKnife.bind(this);
        mContext = this;
        intent = getIntent();
        String title = intent.getStringExtra("title");
        mTextView_title.setText(title);
        //设置Grivew
        setupGrivew();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        Map<String,String> datasList = new HashMap<>();

        datasList.put("cid1",intent.getStringExtra("cid1"));

        if(intent.getStringExtra("goodsSource")!=null) {
            datasList.put("goodsSource", intent.getStringExtra("goodsSource"));
        }
        datasList.put("cid2",intent.getStringExtra("cid2"));
        OkHttpTool.newInstance().post(datasList).start(Contans.STORE_CATEGE).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                categoryBeaner = gson.fromJson(result, CategoryBeaner.class);
                CategoryBeanerlist.addAll(categoryBeaner.getGoods_list());
                categoryAdapter.notifyDataSetChanged();
            }
        });
        mGridView_ptrgy.setAdapter(categoryAdapter);
    }

    private void setupGrivew() {
        categoryAdapter = new CategoryAdapter(mContext,CategoryBeanerlist);
        initData();
    }

}
