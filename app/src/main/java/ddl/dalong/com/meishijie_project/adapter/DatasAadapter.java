package ddl.dalong.com.meishijie_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.MoreTablayoutbean;
import ddl.dalong.com.meishijie_project.ui.MoreDetailActivity;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DatasAadapter extends BaseAdapter {
    private final String des;
    private List<MoreTablayoutbean.DataBean> datas;
    private Context mContext;


    public DatasAadapter(List<MoreTablayoutbean.DataBean> datas, Context mContext,String des) {
        this.datas = datas;
        this.mContext = mContext;
        this.des = des;
    }


    @Override
    public int getCount() {
        return 3;
//        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas==null?0:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHoilder hoilder = null;
        if(view ==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.more_list_tab_items,parent,false);
            hoilder = new ViewHoilder(view);
        }else {
            hoilder = (ViewHoilder) view.getTag();
        }

        String url = datas.get(position).getImage();
        Picasso.with(mContext).load(url).into(hoilder.iv_iv_more_image);
        hoilder.iv_iv_more_image.setScaleType(ImageView.ScaleType.CENTER_CROP);


        hoilder.tv_tv_more_title.setText(datas.get(position).getTitle());
        hoilder.tv_tv_more_price.setText("￥"+datas.get(position).getPrice());
        hoilder.tv_tv_more_market_price.setText("￥"+datas.get(position).getMarket_price());
        hoilder.btn_btn_more_des.setText(des);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MoreDetailActivity.class);
                String id = datas.get(position).getId();
                String goodsSource = datas.get(position).getGoodsSource();
                intent.putExtra("id",id);
                intent.putExtra("goodsSource",goodsSource);
                mContext.startActivity(intent);
            }
        });
        return view;
    }


    class ViewHoilder{
        @BindView(R.id.iv_more_image)
        ImageView iv_iv_more_image;
        @BindView(R.id.tv_more_title)
        TextView tv_tv_more_title;
        @BindView(R.id.tv_more_price)
        TextView tv_tv_more_price;
        @BindView(R.id.tv_more_market_price)
        TextView tv_tv_more_market_price;
        @BindView(R.id.btn_more_des)
        Button btn_btn_more_des;
        public ViewHoilder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

}
