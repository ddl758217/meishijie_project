package ddl.dalong.com.meishijie_project.contans;

/**
 * Created by Administrator on 2016/9/6.
 *
 * 数据接口地址
 */
public class Contans {

    public static final String MINE_ACTIVITY = "http://api.meishi.cc/v5/softs.php?format=json";

    public static final String STORE_SHOPPING = "http://api.meishi.cc/v5/buy_index1.php?format=json";

    public static final String STORE_CATEGE = "http://api.meishi.cc/v5/goods_list1.php?format=json&source=android&page=1";

    //    public static final String STORE_AT_TOP="http://api.meishi.cc/v5/goods_detail3.php?format=json&goodsSource=7,2533&i=2533";
    public static final String STORE_AT_TOP = "http://api.meishi.cc/v5/goods_detail3.php?format=json";


    //商城收索
    public static final String SEARH_DOWN = "http://api.meishi.cc/v5/goods_search.php?format=json&keywords=榨汁机&page=1";

    //点击商城里面更多详情
    public static final String TAB_SHOPP = "http://api.meishi.cc/v5/flashsale_location.php?format=json";

    public static final String TIME = "&time=";
    public static final String PAGER = "&page=";
//    http://api.meishi.cc/v5/flashsale_location.php?format=json&time=1&page=1
//        String url = TAB_SHOPP+TIME+"1"+PAGER+"1";

}