package com.example.campuscoffee;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface NetworkService {

    @GET("/stores/all/")
    Call<List<Stores>> get_stores();

    @FormUrlEncoded
    @POST("/stores/{store}/order/")
    Call<Menu> post_stores(@Path("store") int store, @FieldMap HashMap<String, java.lang.Object> param);

    @GET("/stores/all/orders/{buyer}/")
    Call<List<Order>> get_timer(@Path("buyer") String buyer);

}
