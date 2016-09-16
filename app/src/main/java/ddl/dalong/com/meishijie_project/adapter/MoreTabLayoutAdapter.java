package ddl.dalong.com.meishijie_project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import ddl.dalong.com.meishijie_project.bean.MoreTablayoutbean;


/**
 * Created by Administrator on 2016/9/14.
 */
public class MoreTabLayoutAdapter extends FragmentStatePagerAdapter{
    private List<MoreTablayoutbean.TimeShopBean> Tab_more;
//    private MoreTablayoutbean moreTablayoutbean;
    public List<Fragment> listfragment;

    public MoreTabLayoutAdapter(FragmentManager fm, List<MoreTablayoutbean.TimeShopBean> tab_more, List<Fragment> listfragment) {
        super(fm);
        Tab_more = tab_more;
        this.listfragment = listfragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment!=null?listfragment.get(position):null;
    }

    @Override
    public int getCount() {
        return listfragment!=null?listfragment.size():0;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
////        String des = Tab_more.get(position).getDes();
//        return null;
//    }
}
