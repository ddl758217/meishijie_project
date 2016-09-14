package ddl.dalong.com.meishijie_project.fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zyx.group14.android1603.meishilibrary.MeiShiListView;
import com.zyx.group14.android1603.meishilibrary.MeiShiListViewCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.adapter.HeadRVAdapter;
import ddl.dalong.com.meishijie_project.adapter.HeaderdataAdapter;
import ddl.dalong.com.meishijie_project.adapter.StoreAdapter;
import ddl.dalong.com.meishijie_project.adapter.StorelistendAdapter;
import ddl.dalong.com.meishijie_project.bean.StoreBean;
import ddl.dalong.com.meishijie_project.contans.Contans;
import ddl.dalong.com.meishijie_project.ui.BinnerDetailsActivity;
import ddl.dalong.com.meishijie_project.ui.MoreActivity;

/**
 * 商城Fragment
 */
public class StoreFragment extends Fragment implements MeiShiListViewCallBack {
    private Context mContext;
    private List<StoreBean.ZcBean> ZcBeans = new ArrayList<>();
    //头部广告轮播集合
    private List<StoreBean.BuyIndexTopBean> binnerLists = new ArrayList<>();
    //广告下的recycleView
    private List<StoreBean.CategoryInfosBean> CategoryInfos = new ArrayList<>();
    //
    private List<StoreBean.AdTopBean.DataBean> Datas = new ArrayList<>();
    private List<StoreBean.EndListBean> end_lists = new ArrayList<>();
    private StoreAdapter storeAdapter;
    private HeaderViewHolder headerViewHolder;
    private LinearLayoutManager linearLayoutManager;
    private HeadRVAdapter headRVAdapter;
    private HeaderdataAdapter headerdataAdapter;
    private FooterViewHolder footerViewHolder;
    private StorelistendAdapter storelistendAdapter;

    public static StoreFragment newInstance() {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        }

     @BindView(R.id.pullList_category)
     MeiShiListView mPullListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this,view);
        mPullListView.setCallBack(this);
        //设置listView
        setupExpandableListView();
        //初始化头部的视图
        setupHeaderView();
        //初始化底部的视图
        setupFooterView();
        return view;
    }

    private void setupFooterView() {
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.store_footview_items,null);
         footerViewHolder = new FooterViewHolder(footerView);
      //设置横向 GridView视图，数据源
        stupGrideViewfooter(footerViewHolder);
        mPullListView.addFooterView(footerView);
    }

    private void stupGrideViewfooter(FooterViewHolder footerViewHolder) {
         storelistendAdapter = new StorelistendAdapter(mContext,end_lists);
        footerViewHolder.mGridView_end.setAdapter(storelistendAdapter);
        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
                end_lists.addAll(storeBean.getEnd_list());
               storelistendAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupHeaderView() {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.store_header_items,null);
        headerViewHolder = new HeaderViewHolder(headerView);
        //设置 ConvenientBanner的数据源
        setCBimgData();
        //设置横向recyclerView视图，数据源
        setuprecyclerView(headerViewHolder);
        //设置横向recyclerView视图1，数据源
        setuprecyclerViewData(headerViewHolder);
        mPullListView.addHeaderView(headerView);
    }

    private void setuprecyclerViewData(HeaderViewHolder headerViewHolder) {
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        headerViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        headerdataAdapter = new HeaderdataAdapter(mContext,Datas);
        headerViewHolder.mRecyclerView.setAdapter(headerdataAdapter);
        headerViewHolder.tv_genduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MoreActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void setuprecyclerView(HeaderViewHolder headerViewHolder) {
       linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        headerViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        headRVAdapter = new HeadRVAdapter(mContext,CategoryInfos);
        headerViewHolder.recyclerView.setAdapter(headRVAdapter);
    }

    private void setCBimgData() {
        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean binner = gson.fromJson(result, StoreBean.class);
                binnerLists.addAll(binner.getBuy_index_top());
                //头部的视图的复用
                setupBanner(headerViewHolder);
                headerViewHolder.convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        headerViewHolder.convenientBanner.startTurning(2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        headerViewHolder.convenientBanner.stopTurning();
    }

    private void setupBanner(final HeaderViewHolder headerViewHolder) {
        headerViewHolder.convenientBanner.setPageIndicator(new int[]{R.drawable.feature_point,R.drawable.feature_point_cur}).setPages(new CBViewHolderCreator<HeaderBannerHolder>() {
            @Override
            public HeaderBannerHolder createHolder() {
                return new HeaderBannerHolder();
            }
        },binnerLists)
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        headerViewHolder.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext.getApplicationContext(), BinnerDetailsActivity.class);
                String s = binnerLists.get(position).getClick_obj();
                String a[] = s.split(";");
                intent.putExtra("url",a[1]);
                mContext.startActivity(intent);
            }
        });
    }

    private void setupExpandableListView() {
        //创建适配器
         storeAdapter = new StoreAdapter(mContext,ZcBeans);
         initData();
    }
private String TAG = "ddl";
    private void initData() {
        /**
         *
         * 下载列表图片处理
         *
         */
        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
                Log.i(TAG, "success: "+storeBean.getZc().size());
                ZcBeans.addAll(storeBean.getZc());
                storeAdapter.notifyDataSetChanged();
            }
        });
        mPullListView.setAdapter(storeAdapter);
    }

    @Override
        public void startRefresh() {
//        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
//            @Override
//            public void success(String result) {
//                Gson gson = new Gson();
//                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
//                ZcBeans.addAll(storeBean.getZc());
//                storeAdapter.notifyDataSetChanged();
//                mPullListView.refreshComplete();
//            }
//        });

        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
                end_lists.addAll(storeBean.getEnd_list());
                storelistendAdapter.notifyDataSetChanged();
                mPullListView.refreshComplete();
            }
        });
    }


    static class HeaderBannerHolder implements Holder<StoreBean.BuyIndexTopBean> {
        ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, StoreBean.BuyIndexTopBean data) {
            String url = data.getPhoto();
//            ImageLoader.init(context).load(url,imageView);
            Picasso.with(context).load(url).into(imageView);
        }
    }


    //头部的VieHolder复用
  class HeaderViewHolder {
        @BindView(R.id.tv_gengduode)
        TextView tv_genduo;
        @BindView(R.id.recycle_data)
        RecyclerView mRecyclerView;
        @BindView(R.id.cb_header_store_view)
        ConvenientBanner convenientBanner;
        @BindView(R.id.rv_store_header_view)
        RecyclerView recyclerView;
        public HeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    class FooterViewHolder{
        @BindView(R.id.gv_end_list)
        GridView mGridView_end;
        public FooterViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
