package ddl.dalong.com.meishijie_project.ui;

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
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class WebBuyerActivity extends AppCompatActivity {

    private WebView mWebView_buyer;
    private ImageButton mImagerButton_buyer;
    private String url;
    private String i;
    private String goodsSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_buyer);
        Bundle bundle = this.getIntent().getExtras();
         url = bundle.getString("url");
         i = bundle.getString("i");
         goodsSource = bundle.getString("goodsSource");

        mWebView_buyer = (WebView) findViewById(R.id.web__buyer_view);
        mImagerButton_buyer = (ImageButton) findViewById(R.id.detail__buyer_back_ib);
        initData();
        mImagerButton_buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                mWebView_buyer.loadUrl(url);
                WebViewClient webViewClient = new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //把网站给WebView
                        view.loadUrl(url);
                        return true;
                    }
                };
                mWebView_buyer.setWebViewClient(webViewClient);
            }
        });
    }
}
