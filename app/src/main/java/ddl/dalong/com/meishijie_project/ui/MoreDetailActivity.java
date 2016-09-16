package ddl.dalong.com.meishijie_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.MoreDetailBean;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class MoreDetailActivity extends AppCompatActivity {

    private static final String TAG = "MoreDetailActivity";
//    private List<MoreDetailBean.ObjBean.ShareBean> shareLists = new ArrayList<>();
    private MoreDetailBean bean = new MoreDetailBean();
    private String goodsSource;
    private WebView mWeb_more_detail;
    private String i;

    public static final String URL_PP="http://m.meishij.net/html5/goods_detail.php?st=1&i=5980";
    private ImageButton mib_refresh_web_oo;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detail);
        Intent intent = getIntent();
         i = intent.getStringExtra("id");
         goodsSource = intent.getStringExtra("goodsSource");
        mWeb_more_detail = (WebView) findViewById(R.id.web_view_more_detail);
        mib_refresh_web_oo = (ImageButton) findViewById(R.id.ib_refresh_web_oo);
        mib_refresh_web_oo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initData();

    }

    private void initData() {
        Map<String,String> maplist = new HashMap<>();
        maplist.put("goodsSource",goodsSource);
        maplist.put("id",i);
        //
        OkHttpTool.newInstance().post(maplist).start(Contans.STORE_AT_TOP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopdetailBean topdetailBean = gson.fromJson(result, TopdetailBean.class);
//                String  share_url = moreDetailBean.getObj().getShare().getShare_url();
//                url = topdetailBean.getObj().getShare().getShare_url();
                mWeb_more_detail.loadUrl(URL_PP);
                WebViewClient viewClient = new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                };
                mWeb_more_detail.setWebViewClient(viewClient);
            }
        });
    }
}
