package ddl.dalong.com.meishijie_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.StoreBean;

/**
 * Created by Administrator on 2016/9/8.
 */
public class StorelistendAdapter extends BaseAdapter{
    private Context mContext;
    private List<StoreBean.EndListBean> end_lists;

    public StorelistendAdapter(Context mContext, List<StoreBean.EndListBean> end_lists) {
        this.mContext = mContext;
        this.end_lists = end_lists;
    }

    @Override
    public int getCount() {
        return end_lists==null?0:end_lists.size();
    }

    @Override
    public Object getItem(int position) {
        return end_lists==null?0:end_lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.store_gv_end_list_item,parent,false);
            holder = new ViewHolder(view);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //下载图片
        String url = end_lists.get(position).getImage();
        Picasso.with(mContext).load(url).into(holder.imageView_image);

        holder.mTextView_title.setText(end_lists.get(position).getTitle());
        holder.mTextView_price.setText("￥"+end_lists.get(position).getPrice());
        holder.mTextView_market_price.setText(end_lists.get(position).getMarket_price());
        return view;
    }


    class ViewHolder{
        @BindView(R.id.gv_store_end_img)
       ImageView imageView_image;
        @BindView(R.id.tv_store_title)
        TextView mTextView_title;
        @BindView(R.id.tv__store_market_price)
        TextView mTextView_market_price;
        @BindView(R.id.tv_store_price)
        TextView mTextView_price;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
