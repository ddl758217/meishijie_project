package ddl.dalong.com.meishijie_project.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/4.
 */

/**
 * 网络请求的封装
 */
public class HttpUtils {

    public static final String BASE_URL="http://capi.douyucdn.cn";
    public static HttpService mHttpService;
    public static HttpService create(){
        if(mHttpService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService = retrofit.create(HttpService.class);
        }
        return mHttpService;
    }
}
