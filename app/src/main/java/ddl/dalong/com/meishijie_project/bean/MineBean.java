package ddl.dalong.com.meishijie_project.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MineBean {


    /**
     * code : 1
     * msg : 成功
     * obj : [{"id":10020,"name":"食材组菜","icon":"http://static.meishij.net/images/get/softs_cr.png","desc":"把手头的食材变成美味","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=165&fc=msjsots","size":"3500"},{"id":10019,"name":"吃出健康","icon":"http://static.meishij.net/n/images/ccjkicon_72.png","desc":"吃容易，会吃不容易，让我们吃的更健康吧","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=162&fc=msjsots","size":"3500"},{"id":10010,"name":"伊特-美食管家","icon":"http://static.meishij.net/images/get/softs_guanjia.png","desc":"智能可学习型机器人,解决您烹饪的一切问题!","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=154&fc=msjsots","size":"3500"},{"id":10007,"name":"健康时钟","icon":"http://static.meishij.net/images/get/softs_healthclock.png","desc":"一款帮助您培养健康习惯的小软件，它通过中医理论给出每个时辰最简单合理的健康养生方法","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=151","size":"3500"},{"id":10011,"name":"悦动浏览器","icon":"http://static.meishij.net/images/get/softs_ydbrowser.png","desc":"0.2M的悦动浏览器就给你飞一般的感觉！","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=157","size":"3500"},{"id":10003,"name":"360手机卫士","icon":"http://static.meishij.net/images/get/softs_360safe.png","desc":"360手机卫士是一款免费的手机安全软件，集防垃圾短信，防骚扰电话，防隐私泄漏，对手机进行安全扫描，联网云查杀恶意软件，软件安装实时检测，流量使用全掌握，系统清理手机加速，归属地显示及查询等功能于一身。为您带来全方位的手机安全及隐私保护，是您手机的必备软件。","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=146","size":"3500"},{"id":10004,"name":"ZCOM杂志","icon":"http://static.meishij.net/images/get/softs_zcom.png","desc":"数千本知名杂志免费奉送，给您带来舒适、便捷的阅读体验！","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=148","size":"3500"},{"id":10006,"name":"欧朋浏览器","icon":"http://static.meishij.net/images/get/softs_Opera.png","desc":"欧朋浏览器流行全球，搭载自主强劲内核，速度快、节省流量最高达94%。","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=150","size":"3500"},{"id":10005,"name":"快手看片","icon":"http://static.meishij.net/images/get/softs_ikuaishou.png","desc":"万部高清，一键免费点播。机不离手，随时随地观看。","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=149","size":"3500"},{"id":10002,"name":"ZAKER","icon":"http://static.meishij.net/images/get/softs_zaker.png","desc":"最酷最实用的个性化阅读软件","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=145","size":"3500"},{"id":10000,"name":"安卓市场","icon":"http://static.meishij.net/images/get/softs_anzhuo.png","desc":"国内最早最大的安卓软件和游戏下载平台，提供\u201c手机客户端\u201d、\u201c平板电脑客户端\u201d和\u201c网页端\u201d等多种下载渠道...","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=142","size":"3500"},{"id":10012,"name":"91助手","icon":"http://static.meishij.net/images/get/softs_91_as.png","desc":"国内最早最大的安卓软件和游戏下载平台，提供\u201c手机客户端\u201d、\u201c平板电脑客户端\u201d和\u201c网页端\u201d等多种下载渠道...","apk":"http://adapi.meishij.net/e/public/ClickAd/?adid=160","size":"3500"}]
     */

    private String code;
    private String msg;
    /**
     * id : 10020
     * name : 食材组菜
     * icon : http://static.meishij.net/images/get/softs_cr.png
     * desc : 把手头的食材变成美味
     * apk : http://adapi.meishij.net/e/public/ClickAd/?adid=165&fc=msjsots
     * size : 3500
     */

    private List<ObjBean> obj;

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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private int id;
        private String name;
        private String icon;
        private String desc;
        private String apk;
        private String size;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getApk() {
            return apk;
        }

        public void setApk(String apk) {
            this.apk = apk;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }
}
