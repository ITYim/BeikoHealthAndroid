package com.alensic.beikohealth.http;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * RESTFUL URL
 * Created by zym on 2017/3/16.
 */
public interface HttpService {


    @FormUrlEncoded
    @POST("http://api.cn.ronghub.com/user/getToken.json")
    Observable<Map> getToken(@Field("userId") String userId,
                             @Field("name") String name,
                             @Field("portraitUri") String portraitUri,
                             @Header("App-Key") String appKey,
                             @Header("Nonce") String Nonce,
                             @Header("Timestamp") String Timestamp,
                             @Header("Signature") String Signature);

}
