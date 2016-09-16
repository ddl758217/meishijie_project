package ddl.dalong.com.meishijie_project.fragment.tab_fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.adapter.DatasAadapter;
import ddl.dalong.com.meishijie_project.bean.MoreTablayoutbean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.ui.MoreTopDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {
    private List<MoreTablayoutbean.DataBean> datas = new ArrayList<>();
    private List<MoreTablayoutbean.ProductsNextBean> Products = new ArrayList<>();
    private List<MoreTablayoutbean.TimeShopBean> TimeShop = new ArrayList<>();
    private Context mContext;
    private String des;
    private DatasAadapter datasAadapter;
    private String count;
    private View headerView;
    private HeaderViewHolder headerViewHolder;
    private int position;

    public MoreFragment() {
        // Required empty public constructor
    }


    public static MoreFragment newInstance(String des,String i) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString("des", des);
        args.putString("count",i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        des = getArguments().getString("des");
        count = getArguments().getString("count");
    }

    @BindView(R.id.listView_more)
    ListView mListView_more;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        initHeader();
        setupListView();
        return view;

    }

    private void initHeader() {
         headerView = LayoutInflater.from(mContext).inflate(R.layout.more_header_view,null);
         headerViewHolder = new HeaderViewHolder(headerView);
         initProducts();

    }

    private void initProducts() {
        Map<String,String> Tab_layout = new HashMap<>();
        Tab_layout.put("time", count);
        Tab_layout.put("pager",""+1);
        OkHttpTool.newInstance().post(Tab_layout).start(Contans.TAB_SHOPP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                MoreTablayoutbean moreTablayoutbean = gson.fromJson(result, MoreTablayoutbean.class);
                Products.addAll(moreTablayoutbean.getProducts_next());
                TimeShop.addAll(moreTablayoutbean.getTime_shop());
                String url = Products.get(position).getPhoto();
                Picasso.with(mContext).load(url).into(headerViewHolder.imageView__more_postos);
                headerViewHolder.imageView__more_postos.setScaleType(ImageView.ScaleType.FIT_XY);
                headerViewHolder.textView_title.setText(TimeShop.get(position).getTitle());
                headerViewHolder.textView_yijieshu.setText(des);
                headerViewHolder.imageView__more_postos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, MoreTopDetailsActivity.class);
                        String click_obj = Products.get(position).getClick_obj();
                        String s[] = click_obj.split(";");
                        intent.putExtra("url",s[1]);
                        mContext.startActivity(intent);
                    }
                });
            }
        });
    }

    class HeaderViewHolder{
        @BindView(R.id.iv_more_posto)
        ImageView imageView__more_postos;
        @BindView(R.id.tv_title_more)
        TextView textView_title;
        @BindView(R.id.tv_des_jieshu)
        TextView textView_yijieshu;
        //时间
        @BindView(R.id.tv_timesKK_more)
        TextView tv_times;
        public HeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }


    private void setupListView() {
        if (datas!= null) {
            initData();
        }
//        initData();
        datasAadapter = new DatasAadapter(datas, mContext, des);
        mListView_more.addHeaderView(headerView);
    }

    private void initData() {
         Map<String,String> Tab_layout = new HashMap<>();
        Tab_layout.put("time", count);
        Tab_layout.put("pager",""+1);
        OkHttpTool.newInstance().post(Tab_layout).start(Contans.TAB_SHOPP).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                MoreTablayoutbean moreTablayoutbean = gson.fromJson(result, MoreTablayoutbean.class);
                datas.addAll(moreTablayoutbean.getData());
                datasAadapter.notifyDataSetChanged();
            }
        });
        mListView_more.setAdapter(datasAadapter);
    }
}

