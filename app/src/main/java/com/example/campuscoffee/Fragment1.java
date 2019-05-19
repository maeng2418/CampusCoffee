package com.example.campuscoffee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1  extends Fragment {

    private NetworkService networkService;
    Button btn;
    LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // xml 로 만들어준 프레그먼트를 자바 단에서 만들어줌
        ViewGroup rootGroup =(ViewGroup)inflater.inflate(R.layout.activity_menu, container,false);
        ll = rootGroup.findViewById(R.id.menuLayout);

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

                    String restaurant_txt = "";
                    for(Stores stores : storesList){
                        if(stores.getCreator()==2){
                            btn = new Button(getActivity());
                            btn.setId(stores.getId());
                            btn.setText(stores.getName());
                            ll.addView(btn);

                            btn.setOnClickListener(new Button.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    show();
                                }
                            });
                        }

                    }
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



        return  rootGroup;

    }


    public void show() {

        OptionDialog customDialog = new OptionDialog(getActivity());
        customDialog.show();
    }
}