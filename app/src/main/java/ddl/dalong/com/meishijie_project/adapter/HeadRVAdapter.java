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
 * Created by Administrator on 2016/9/7.
 */
public class HeadRVAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context mContext;
    private List<StoreBean.CategoryInfosBean> CategoryInfos;

    public HeadRVAdapter(Context mContext, List<StoreBean.CategoryInfosBean> categoryInfos) {
        this.mContext = mContext;
        CategoryInfos = categoryInfos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.stroe_recyleview_item,null);
        return new MyViewHolder(view);
    }


    //更新item布局
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        OkHttpTool.newInstance().start(Contans.STORE_SHOPPING).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(result, StoreBean.class);
                CategoryInfos.addAll(storeBean.getCategory_infos());
                //解析数据
                String url = CategoryInfos.get(position).getIcon();
                Picasso.with(mContext).load(url).into(holder.imageView_icon);
                holder.imageView_icon.setScaleType(ImageView.ScaleType.CENTER_CROP);

                holder.mTextView_name.setText(CategoryInfos.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_recyleview_icon)
    ImageView imageView_icon;
    @BindView(R.id.tv_recyleview_name)
    TextView mTextView_name;
    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}