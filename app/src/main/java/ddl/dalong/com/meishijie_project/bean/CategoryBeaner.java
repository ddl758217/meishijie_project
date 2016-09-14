package ddl.dalong.com.meishijie_project.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class CategoryBeaner {


    /**
     * code : 1
     * msg : 成功
     * category : [{"cid1":"1","cid2":"0"}]
     * goods_list : [{"id":"2069","title":"更安全的天然材料丨食品级硅胶方形冰格","image":"http://site.meishij.net/shop/uploadfile/20160425/20160425192159.jpg","price":"19.9","market_price":"87","type":"0","goodsSource":"6,1"},{"id":"875","title":"耐热颜值于一身丨OOU 养生陶瓷汤锅 2.5L","image":"http://site.meishij.net/shop/uploadfile/20160401/20160401150736_493.jpg","price":"89","market_price":"138","type":"0","goodsSource":"6,1"},{"id":"1441","title":"纯净星空摆上桌 | 剑林8英寸月光盘","image":"http://site.meishij.net/shop/uploadfile/20160826/20160826170437.jpg","price":"11.9","market_price":"29.9","type":"0","goodsSource":"6,1"},{"id":"5788","title":"每种心情都有不同的颜色|印象无把杯子 多款可选","image":"http://site.meishij.net/shop/uploadfile/20160725/20160725123720.jpg","price":"10.9","market_price":"29.9","type":"0","goodsSource":"6,1"},{"id":"1410","title":"遇见你的少女心|卡通动物点心盘蛋糕盘 3款可选","image":"http://site.meishij.net/shop/uploadfile/20160311/20160311151610_248.jpg","price":"15","market_price":"48","type":"0","goodsSource":"6,1"},{"id":"1404","title":"造型软萌宝宝更爱吃|DIY 小熊猫饭团模具","image":"http://site.meishij.net/shop/uploadfile/20160311/20160311172228_153.jpg","price":"32","market_price":"49","type":"0","goodsSource":"6,1"},{"id":"4899","title":"还原食物天然风味 | 光合生活家用纯手工竹制蒸格","image":"http://site.meishij.net/shop/uploadfile/20160825/20160825093606.jpg","price":"19","market_price":"59","type":"0","goodsSource":"6,1"},{"id":"4649","title":"元气早餐CP | 摩登主妇早安杯","image":"http://site.meishij.net/shop/uploadfile/20160630/20160630093718.jpg","price":"15","market_price":"15","type":"0","goodsSource":"6,1"},{"id":"2322","title":"光合生活 无铅玻璃密封罐 多款可选","image":"http://site.meishij.net/shop/uploadfile/20160427/20160427155251.jpg","price":"29.6","market_price":"35","type":"0","goodsSource":"6,1"},{"id":"5990","title":"摩登主妇 心形碗盘碟","image":"http://site.meishij.net/shop/uploadfile/20160824/20160824154030.jpg","price":"8","market_price":"8","type":"0","goodsSource":"6,1"}]
     */

    private String code;
    private String msg;
    /**
     * cid1 : 1
     * cid2 : 0
     */

    private List<CategoryBean> category;
    /**
     * id : 2069
     * title : 更安全的天然材料丨食品级硅胶方形冰格
     * image : http://site.meishij.net/shop/uploadfile/20160425/20160425192159.jpg
     * price : 19.9
     * market_price : 87
     * type : 0
     * goodsSource : 6,1
     */

    private List<GoodsListBean> goods_list;

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

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class CategoryBean {
        private String cid1;
        private String cid2;

        public String getCid1() {
            return cid1;
        }

        public void setCid1(String cid1) {
            this.cid1 = cid1;
        }

        public String getCid2() {
            return cid2;
        }

        public void setCid2(String cid2) {
            this.cid2 = cid2;
        }
    }

    public static class GoodsListBean {
        private String id;
        private String title;
        private String image;
        private String price;
        private String market_price;
        private String type;
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

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGoodsSource() {
            return goodsSource;
        }

        public void setGoodsSource(String goodsSource) {
            this.goodsSource = goodsSource;
        }
    }
}
