package com.example.campuscoffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderList extends BaseActivity {

    //public final String TAG = "KJH";
    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        setContentView(getLayoutResource());
        initLayout();



        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService("6d091e3c.ngrok.io");
        //application.buildNetworkService("127.0.0.1", 8000);
        networkService = ApplicationController.getInstance().getNetworkService();

        Call<List<Stores>> getCall = networkService.get_stores();
        getCall.enqueue(new Callback<List<Stores>>() {
            @Override
            public void onResponse(Call<List<Stores>> call, Response<List<Stores>> response) {
                if( response.isSuccessful()) {
                    List<Stores> storesList = response.body();
                    TextView textView = (TextView) findViewById(R.id.textView) ;

                    String restaurant_txt = "";
                    for(Stores stores : storesList){
                        restaurant_txt +=
                                stores.getId() + "\n"+
                                stores.getCreated_at() + "\n"+
                                stores.getUpdated_at() + "\n"+
                                stores.getFile() + "\n"+
                                stores.getName() + "\n"+
                                stores.getTemperature() + "\n"+
                                stores.getPrice() +
                                "\n";
                    }
                    textView.setText(restaurant_txt);
                } else {
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode + " Error Message : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Stores>> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_orderlist;
    }
}