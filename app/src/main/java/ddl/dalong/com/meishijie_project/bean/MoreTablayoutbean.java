package ddl.dalong.com.meishijie_project.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class MoreTablayoutbean {


    /**
     * code : 1
     * msg : 成功
     * time_shop : [{"time":"08:00","time_start":"1473724800","time_end":"1473739200","des":"已结束","id":"1","title":"已结束 记得下次再来哦","isnow":"0"},{"time":"12:00","time_start":"1473739200","time_end":"1473753600","des":"已结束","id":"2","title":"已结束 记得下次再来哦","isnow":"0"},{"time":"16:00","time_start":"1473753600","time_end":"1473768000","des":"抢购中","id":"3","title":"抢购中 先下单先得哦","isnow":"0"},{"time":"20:00","time_start":"1473768000","time_end":"1473811200","des":"即将开始","id":"4","title":"即将开始 先下单先得哦","isnow":"1"}]
     * products_next : [{"photo":"http://site.meishij.net/adm/additive/2016-04-19/5715df41755b8.jpg","click_type":4,"click_obj":"闪购顶部横幅八点-生鲜;http://i.meishi.cc/g/transit.php?ename=1549&from=mobile_mobile","pv_trackingURL":"","click_trackingURL":"","is_recipe":"0","sft":"0","aid":"121","jump":"{\"type\":\"4\",\"class_name\":\"MSJWebAdvViewController\",\"property\":{\"urlString\":\"http:\\/\\/i.meishi.cc\\/g\\/transit.php?ename=1549&from=mobile_mobile\",\"goodsSource\":\"10,121\"}}"}]
     * data : [{"id":"5002","title":"红心才更甜 | 田木飞狐海南木瓜8斤","image":"http://site.meishij.net/shop/uploadfile/20160831/20160831171710.jpg","price":"32","start_time":"1473768000","end_time":"1473811200","market_price":"58","goods_type":"1","store":"78","goodsSource":"12,5002"},{"id":"1268","title":"吃一包还想囤100| 厨领地外婆菜 250g/包","image":"http://site.meishij.net/shop/uploadfile/20160222/20160222164935_966.jpg","price":"16","start_time":"1473768000","end_time":"1473811200","market_price":"26","goods_type":"1","store":"18","goodsSource":"12,1268"},{"id":"1076","title":"不怕吃的太油腻 | 好好喝茶 断油脂重焙黑乌龙","image":"http://site.meishij.net/shop/uploadfile/20160122/20160122135635_384.jpg","price":"39","start_time":"1473768000","end_time":"1473811200","market_price":"69","goods_type":"1","store":"671","goodsSource":"12,1076"}]
     */

    private String code;
    private String msg;
    /**
     * time : 08:00
     * time_start : 1473724800
     * time_end : 1473739200
     * des : 已结束
     * id : 1
     * title : 已结束 记得下次再来哦
     * isnow : 0
     */

    private List<TimeShopBean> time_shop;
    /**
     * photo : http://site.meishij.net/adm/additive/2016-04-19/5715df41755b8.jpg
     * click_type : 4
     * click_obj : 闪购顶部横幅八点-生鲜;http://i.meishi.cc/g/transit.php?ename=1549&from=mobile_mobile
     * pv_trackingURL :
     * click_trackingURL :
     * is_recipe : 0
     * sft : 0
     * aid : 121
     * jump : {"type":"4","class_name":"MSJWebAdvViewController","property":{"urlString":"http:\/\/i.meishi.cc\/g\/transit.php?ename=1549&from=mobile_mobile","goodsSource":"10,121"}}
     */

    private List<ProductsNextBean> products_next;
    /**
     * id : 5002
     * title : 红心才更甜 | 田木飞狐海南木瓜8斤
     * image : http://site.meishij.net/shop/uploadfile/20160831/20160831171710.jpg
     * price : 32
     * start_time : 1473768000
     * end_time : 1473811200
     * market_price : 58
     * goods_type : 1
     * store : 78
     * goodsSource : 12,5002
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TimeShopBean> getTime_shop() {
        return time_shop;
    }

    public void setTime_shop(List<TimeShopBean> time_shop) {
        this.time_shop = time_shop;
    }

    public List<ProductsNextBean> getProducts_next() {
        return products_next;
    }

    public void setProducts_next(List<ProductsNextBean> products_next) {
        this.products_next = products_next;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class TimeShopBean {
        private String time;
        private String time_start;
        private String time_end;
        private String des;
        private String id;
        private String title;
        private String isnow;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsnow() {
            return isnow;
        }

        public void setIsnow(String isnow) {
            this.isnow = isnow;
        }
    }

    public static class ProductsNextBean {
        private String photo;
        private int click_type;
        private String click_obj;
        private String pv_trackingURL;
        private String click_trackingURL;
        private String is_recipe;
        private String sft;
        private String aid;
        private String jump;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getClick_type() {
            return click_type;
        }

        public void setClick_type(int click_type) {
            this.click_type = click_type;
        }

        public String getClick_obj() {
            return click_obj;
        }

        public void setClick_obj(String click_obj) {
            this.click_obj = click_obj;
        }

        public String getPv_trackingURL() {
            return pv_trackingURL;
        }

        public void setPv_trackingURL(String pv_trackingURL) {
            this.pv_trackingURL = pv_trackingURL;
        }

        public String getClick_trackingURL() {
            return click_trackingURL;
        }

        public void setClick_trackingURL(String click_trackingURL) {
            this.click_trackingURL = click_trackingURL;
        }

        public String getIs_recipe() {
            return is_recipe;
        }

        public void setIs_recipe(String is_recipe) {
            this.is_recipe = is_recipe;
        }

        public String getSft() {
            return sft;
        }

        public void setSft(String sft) {
            this.sft = sft;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getJump() {
            return jump;
        }

        public void setJump(String jump) {
            this.jump = jump;
        }
    }

    public static class DataBean {
        private String id;
        private String title;
        private String image;
        private String price;
        private String start_time;
        private String end_time;
        private String market_price;
        private String goods_type;
        private String store;
        private String goodsSource;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getGoodsSource() {
            return goodsSource;
        }

        public void setGoodsSource(String goodsSource) {
            this.goodsSource = goodsSource;
        }
    }
}
