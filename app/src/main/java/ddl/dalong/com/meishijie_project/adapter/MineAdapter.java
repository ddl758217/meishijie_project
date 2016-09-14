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
import ddl.dalong.com.meishijie_project.bean.MineBean;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MineAdapter extends BaseAdapter{

    private Context mContext;
    private List<MineBean.ObjBean> datas;



    public MineAdapter(Context mContext, List<MineBean.ObjBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas==null ? 0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? 0 : datas.get(position);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.mine_items_list,parent,false);
            holder = new ViewHolder(view);
        }else {
            holder = (ViewHolder) view.getTag();
        }

//        MineBean.ObjBean objBean = datas.get(position);
//        String url = objBean.getIcon();
        Picasso.with(mContext).load(datas.get(position).getIcon()).into(holder.imageView);
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        holder.tv_name.setText(datas.get(position).getName());
        holder.tv_desc.setText(datas.get(position).getDesc());
        return view;
    }


  class ViewHolder{
      @BindView(R.id.iv_mine_softs_icon)
        ImageView imageView;
        @BindView(R.id.tv_mine_softs_name)
        TextView tv_name;
        @BindView(R.id.tv_mine_softs_desc)
        TextView tv_desc;
      public ViewHolder(View view) {
          ButterKnife.bind(this,view);
          view.setTag(this);
      }
  }
}
