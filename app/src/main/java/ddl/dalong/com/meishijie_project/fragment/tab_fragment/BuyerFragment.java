package ddl.dalong.com.meishijie_project.fragment.tab_fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.ui.WebBuyerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyerFragment extends Fragment {

    private TopdetailBean buyer = new TopdetailBean();
    private Context mContext;
    private ImageView imageView_logo;
    private ImageView iv_icon01;
    private TextView tv_title01_buyer;
    private ImageView iv_icon02;
    private TextView tv_title02_buyer;
    private TextView tv_url_web;
    private String goodsSource;
    private String i;
    private int position = 0;

    public BuyerFragment() {
        // Required empty public constructor
    }


    public static BuyerFragment newInstance(String goodsSource,String i) {
        Bundle bundle = new Bundle();
        bundle.putString("goodsSource",goodsSource);
        bundle.putString("i",i);
        BuyerFragment fragment = new BuyerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        Bundle arguments = getArguments();
        goodsSource = arguments.getString("goodsSource");
        i = arguments.getString("i");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        imageView_logo = (ImageView) view.findViewById(R.id.iv_logo);
         iv_icon01 = (ImageView) view.findViewById(R.id.iv_icon01);
         tv_title01_buyer = (TextView) view.findViewById(R.id.tv_title01_buyer);
         iv_icon02 = (ImageView) view.findViewById(R.id.iv_icon02);
         tv_title02_buyer = (TextView) view.findViewById(R.id.tv_title02_buyer);
         tv_url_web = (TextView) view.findViewById(R.id.tv_url_web);
        initData();
        tv_url_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebBuyerActivity.class);
                String url = buyer.getObj().getStore_infomation().getUrl();
                Log.i("ddl", "------: " +url);
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                bundle.putString("goodsSource",goodsSource);
                bundle.putString("i",i);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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
                TopdetailBean topBean = gson.fromJson(result, TopdetailBean.class);
                buyer = topBean;
                Picasso.with(mContext).load(buyer.getObj().getStore_infomation().getLogo()).into(imageView_logo);
                String str = buyer.getObj().getStore_infomation().getLogo();
                imageView_logo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Picasso.with(mContext).load(buyer.getObj().getStore_infomation().getDiscount_info().get(position).getIcon()).into(iv_icon01);
                iv_icon01.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Picasso.with(mContext).load(buyer.getObj().getStore_infomation().getDiscount_info().get(position).getIcon()).into(iv_icon02);
                iv_icon02.setScaleType(ImageView.ScaleType.CENTER_CROP);


                tv_title01_buyer.setText(buyer.getObj().getStore_infomation().getDiscount_info().get(position).getTitle());
                tv_title02_buyer.setText(buyer.getObj().getStore_infomation().getDiscount_info().get(position).getTitle());




            }
        });
    }

}
