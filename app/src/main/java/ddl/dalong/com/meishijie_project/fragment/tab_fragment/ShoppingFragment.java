package ddl.dalong.com.meishijie_project.fragment.tab_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;

public class ShoppingFragment extends Fragment {

//    public static final String URL_TOP="http://m.meishij.net/html5/goods_detail.php?st=1&i=6027";
    private WebView webView;
    private List<TopdetailBean.ObjBean.ShareBean> sharelist = new ArrayList<>();
    private int position;
    private Intent intent;

    private String goodsSource,i;
    private String share_url;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    public static ShoppingFragment newInstance(String goodsSource,String i) {
        Bundle bundle = new Bundle();
        bundle.putString("goodsSource",goodsSource);
        bundle.putString("id",i);
        ShoppingFragment fragment = new ShoppingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        goodsSource = arguments.getString("goodsSource");
        i = arguments.getString("id");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
         webView = (WebView) view.findViewById(R.id.web_top_view);



        initData();
//        String share_url = sharelist.get(position).getShare_url();

//        webView.loadUrl(share_url);
//        WebViewClient webViewClient = new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //把网站给WebView
//                view.loadUrl(url);
//                return true;
//            }
//        };
//        webView.setWebViewClient(webViewClient);
        return view;
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

//                sharelist.addAll(topdetailBean.getObj().getShare());
               share_url = topdetailBean.getObj().getShare().getShare_url();
                webView.loadUrl(share_url);
                WebViewClient webViewClient = new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //把网站给WebView
                        view.loadUrl(url);
                        return true;
                    }
                };
                webView.setWebViewClient(webViewClient);
            }
        });
    }
}
