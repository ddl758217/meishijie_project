package ddl.dalong.com.meishijie_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class GradviewdetailActivity extends AppCompatActivity {
    public static final String OO="http://m.meishij.net/html5/goods_detail.php?st=1&i=1017";

    private String goodsSource,i;
    private WebView m_webView_kk;
    private String share_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradviewdetail);
        m_webView_kk = (WebView) findViewById(R.id.webView_ll);
        Intent intent = getIntent();
         goodsSource = intent.getStringExtra("goodsSource");
         i = intent.getStringExtra("i");
        initData();

    }

    private void initData() {
        Map<String,String> kk = new HashMap<>();
        kk.put("goodsSource",goodsSource);
        kk.put("i",i);
        OkHttpTool.newInstance().post(kk).start(Contans.STORE_AT_TOP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
//                TopdetailBean topdetailBean = gson.fromJson(result, TopdetailBean.class);
//                share_url = topdetailBean.getObj().getShare().getShare_url();
                m_webView_kk.loadUrl(OO);
                WebViewClient webViewClient = new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                };
                m_webView_kk.setWebViewClient(webViewClient);
            }
        });
    }
}
