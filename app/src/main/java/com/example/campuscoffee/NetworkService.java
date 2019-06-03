package com.example.campuscoffee;

import com.example.campuscoffee.DTOs.Menu;
import com.example.campuscoffee.DTOs.Order;
import com.example.campuscoffee.DTOs.Stores;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface NetworkService {
    // get menu list request
    @GET("/stores/all/")
    Call<List<Stores>> get_stores();

    //post order request
    @FormUrlEncoded
    @POST("/stores/{store}/order/")
    Call<Menu> post_stores(@Path("store") int store, @FieldMap HashMap<String, java.lang.Object> param);

    //get {buyer}'s order list
    @GET("/stores/all/orders/{buyer}/")
    Call<List<Order>> get_timer(@Path("buyer") String buyer);

}
