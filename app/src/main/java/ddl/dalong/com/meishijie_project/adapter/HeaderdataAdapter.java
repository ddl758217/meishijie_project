package ddl.dalong.com.meishijie_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.StoreBean;
import ddl.dalong.com.meishijie_project.contans.Contans;

/**
 * Created by Administrator on 2016/9/8.
 */
public class HeaderdataAdapter extends RecyclerView.Adapter<MyDatasViewHolder>{
    private Context mContext;
    private List<StoreBean.AdTopBean.DataBean> Datas;

    public HeaderdataAdapter(Context mContext, List<StoreBean.AdTopBean.DataBean> datas) {
        this.mContext = mContext;
        Datas = datas;
    }

    @Override
    public MyDatasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.stroe_recyleviewdata_item,null);
        return new MyDatasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyDatasViewHolder holder, final int position) {
        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
                Datas.addAll(storeBean.getAd_top().getData());
                //解析数据
                String url = Datas.get(position).getImage();
                Picasso.with(mContext).load(url).into(holder.imageView_image);
                holder.imageView_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                holder.mTextView_title.setText(Datas.get(position).getTitle());
                holder.mTextView_price.setText("￥"+Datas.get(position).getMarket_price());
                holder.mTextView_store.setText("剩余 "+Datas.get(position).getTotal_store()+ "件");
            }
        });
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}


class MyDatasViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.iv_data_image)
    ImageView imageView_image;
    @BindView(R.id.tv_data_title)
    TextView mTextView_title;
    @BindView(R.id.tv_datas_price)
    TextView mTextView_price;
    @BindView(R.id.tv_data_store)
    TextView mTextView_store;
    public MyDatasViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}