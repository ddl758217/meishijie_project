package ddl.dalong.com.meishijie_project.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;

public class BinnerDetailsActivity extends AppCompatActivity {
    private String url;
    private WebView wb;

    @BindView(R.id.detail_back_ib)
    ImageButton mImageButton_web_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binner_details);
        ButterKnife.bind(this);
         wb = (WebView) findViewById(R.id.web_view);
         url = getIntent().getStringExtra("url");
         wb.loadUrl(url);
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //把网站给WebView
                view.loadUrl(url);
                return true;
            }
        };
        wb.setWebViewClient(webViewClient);
        mImageButton_web_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
