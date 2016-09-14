package com.zyx.group14.android1603.meishilibrary;

/**
 * Created by yixin on 2016/9/6.
 */
public class Time {
    public static String timeInMillisSwitch(long time){
        time /= 1000;
        if(time<60){
            return "刚刚";
        }else if(time>=60&&time<3600){
            return (int)time/60 + "分钟之前";
        }else if(time>=3600&&time<3600*24){
            return (int)time/3600 + "小时之前";
        }else{
            return (int)time/(3600*24) + "天之前";
        }
    }
}
