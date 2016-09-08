package ddl.dalong.com.meishijie_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.StoreBean;
import ddl.dalong.com.meishijie_project.ui.WebActivity;

/**
 * Created by Administrator on 2016/9/7.
 */
public class StoreAdapter extends BaseAdapter{

    private Context mContext;
    private List<StoreBean.ZcBean> ZcBeans;

    public StoreAdapter(Context mContext, List<StoreBean.ZcBean> zcBeans) {
        this.mContext = mContext;
        ZcBeans = zcBeans;
    }

    @Override
    public int getCount() {
        return ZcBeans==null ? 0:ZcBeans.size();
//        return 6;
    }

    @Override
    public Object getItem(int position) {
        return ZcBeans == null ? 0 : ZcBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.store_zc_items,parent,false);
            holder = new ViewHolder(view);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //下载图片
        String url = ZcBeans.get(position).getImage();
        Picasso.with(mContext).load(url).into(holder.imageView);
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), WebActivity.class);
                intent.putExtra("url",ZcBeans.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder{
        @BindView(R.id.iv_buy_index1_image)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

}
