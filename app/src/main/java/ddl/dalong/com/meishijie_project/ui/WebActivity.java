package ddl.dalong.com.meishijie_project.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;

public class WebActivity extends AppCompatActivity {
    @BindView(R.id.detail_back_ib)
    ImageButton mImageButton_web_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        WebView wb = (WebView) findViewById(R.id.web_view);
        String url = getIntent().getStringExtra("url");
        //把网站给WebView
        wb.loadUrl(url);

        mImageButton_web_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
