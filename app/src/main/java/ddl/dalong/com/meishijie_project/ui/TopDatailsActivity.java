package ddl.dalong.com.meishijie_project.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class TopDatailsActivity extends AppCompatActivity {
    private static final String TAG = "TopDatailsActivity";
    private TopdetailBean topdetail = new TopdetailBean();

    private ImageView mbig;
    private String big;
    private String goodsSource;
    private String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_datails);
        mbig = (ImageView) findViewById(R.id.iv_big);
        Intent intent = getIntent();
        big = intent.getStringExtra("big");
//        Log.i(TAG, "----:1 "+big);
//        goodsSource = intent.getStringExtra("goodsSource");
//        Log.i(TAG, "----:2 "+goodsSource);
//        i = intent.getStringExtra("id");
//        Log.i(TAG, "----:3 "+i);
        initSharedPreference();
        initData();

    }

    private void initSharedPreference() {
        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        i = sp.getString("id","id" );
        goodsSource = sp.getString("goodsSource", "goodsSource");
    }

    private void initData() {
        Map<String,String> list_top = new HashMap<>();
        list_top.put("goodsSource",goodsSource);
        list_top.put("i",i);
        OkHttpTool.newInstance().post(list_top).start(Contans.STORE_AT_TOP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopdetailBean topdetailBean = gson.fromJson(result, TopdetailBean.class);
                topdetail = topdetailBean;
                Picasso.with(TopDatailsActivity.this).load(big).into(mbig);
                mbig.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        });
    }
}
