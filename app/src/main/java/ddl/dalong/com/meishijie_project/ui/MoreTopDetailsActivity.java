package ddl.dalong.com.meishijie_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import ddl.dalong.com.meishijie_project.R;

public class MoreTopDetailsActivity extends AppCompatActivity {

    private WebView more_Webview;
    private String url;
    private ImageButton m_detail__more_back_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_top_details);
        Intent intent = getIntent();
         url = intent.getStringExtra("url");
        m_detail__more_back_ib = (ImageButton) findViewById(R.id.detail__more_back_ib);
        m_detail__more_back_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        more_Webview = (WebView) findViewById(R.id.webView_more);
        more_Webview.loadUrl(url);
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        more_Webview.setWebViewClient(webViewClient);

    }



}
