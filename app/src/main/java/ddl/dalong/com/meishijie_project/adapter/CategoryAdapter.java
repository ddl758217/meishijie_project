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
import ddl.dalong.com.meishijie_project.bean.CategoryBeaner;

/**
 * Created by Administrator on 2016/9/9.
 */
public class CategoryAdapter extends BaseAdapter{
    private Context mContext;
    private List<CategoryBeaner.GoodsListBean> CategoryBeanerlist;
    public CategoryAdapter(Context mContext, List<CategoryBeaner.GoodsListBean> categoryBeanerlist) {
        this.mContext = mContext;
        CategoryBeanerlist = categoryBeanerlist;
    }

    @Override
    public int getCount() {
        return CategoryBeanerlist == null?0:CategoryBeanerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return CategoryBeanerlist == null?0:CategoryBeanerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.category_items_list,parent,false);
            holder = new ViewHolder(view);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        String url = CategoryBeanerlist.get(position).getImage();
        Picasso.with(mContext).load(url).into(holder.imageView);

        holder.mTextView_mtitle.setText(CategoryBeanerlist.get(position).getTitle());
        holder.mTextView_price.setText("￥"+ CategoryBeanerlist.get(position).getPrice());
        holder.mTextView_market_price.setText("￥" +CategoryBeanerlist.get(position).getMarket_price());
        return view;
    }

    class ViewHolder{
        @BindView(R.id.iv_mCategory_image)
        ImageView imageView;
        @BindView(R.id.tv_mCategory_mtitle)
        TextView mTextView_mtitle;
        @BindView(R.id.tv_mCategory_price)
        TextView mTextView_price;
        @BindView(R.id.tv_mCategory_market_price)
        TextView mTextView_market_price;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
