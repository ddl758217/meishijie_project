package ddl.dalong.com.meishijie_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ddl.dalong.com.meishijie_project.R;
import ddl.dalong.com.meishijie_project.bean.TopdetailBean;
import ddl.dalong.com.meishijie_project.ui.TopDatailsActivity;

/**
 * Created by Administrator on 2016/9/11.
 */
public class HeaderViewAdapter extends PagerAdapter {
    private List<TopdetailBean.ObjBean.ImagesBean> obj_images;
    private Context mContext;

    public HeaderViewAdapter(List<TopdetailBean.ObjBean.ImagesBean> obj_images, Context mContext) {
        this.obj_images = obj_images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return obj_images == null?0:obj_images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vp_header_view_one,null);
        ImageView ivHeaderView = (ImageView) view.findViewById(R.id.iv_obj_header_photo_one);
        String url = obj_images.get(position).getSmall();
        Picasso.with(mContext).load(url).into(ivHeaderView);
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopDatailsActivity.class);
                String big = obj_images.get(position).getBig();
                intent.putExtra("big",big);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}
