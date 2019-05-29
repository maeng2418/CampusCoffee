package com.example.campuscoffee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderList extends BaseActivity {

    //public final String TAG = "KJH";
    private NetworkService networkService;
    //ProgressDialog dialog;
    //Vector bought = new Vector ();

    String s1 = "제1학생회관 카페\n";
    String s2 = "제2학생회관 카페\n";
    String s3 = "도서관 카페\n";
    String s4 = "테크노큐브 카페\n";

    LinearLayout itemList;
    Vector layoutList = new Vector <LinearLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        //dialog = new ProgressDialog(OrderList.this);
        NetworkTask mProcessTask = new NetworkTask();

        mProcessTask.execute();


    }

    private class NetworkTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            //dialog.setMessage("잠시만 기다려주세요");
            //dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

            String buyer = "maeng";

            /*
            for(int i=0; i<Reservation.copyObjectList.size();i++) {
                Object obj = (Object) Reservation.copyObjectList.elementAt(i);
                int store = obj.store;
            }
            */


                ApplicationController application = ApplicationController.getInstance();
                application.buildNetworkService("f42cad08.ngrok.io");
                //application.buildNetworkService("127.0.0.1", 8000);
                networkService = ApplicationController.getInstance().getNetworkService();

                Call<List<Order>> getCall = networkService.get_timer(buyer);
                getCall.enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        if( response.isSuccessful()) {
                            List<Order> orderList = response.body();

                            //TextView textView = (TextView) findViewById(R.id.textView) ;

                            for(Order orders : orderList){

                                //조리중 : 2 완료 : 3
                                if (orders.getProgress() == 1){
                                    createOrderList(orders, "조리중");
                                }else if(orders.getProgress() == 2){
                                    createOrderList(orders, "완료");
                                } else{
                                    createOrderList(orders, "접수중");
                                }
                            }


                        } else {
                            int StatusCode = response.code();
                            Log.i(ApplicationController.TAG, "Status Code : " + StatusCode + " Error Message : " + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {
                        Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
                    }
                });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //dialog.dismiss();

        }


    }

    public void createOrderList(Order orders, String state){
        switch(orders.getStore()){
            case 2:
                itemList = (LinearLayout) findViewById(R.id.stateList1);
                layoutList.add(itemList);
                break;
            case 3:
                itemList = (LinearLayout) findViewById(R.id.stateList2);
                layoutList.add(itemList);
                break;
            case 4:
                itemList = (LinearLayout) findViewById(R.id.stateList3);
                layoutList.add(itemList);
                break;
            case 5:
                itemList = (LinearLayout) findViewById(R.id.stateList4);

                layoutList.add(itemList);
                break;
        }


        LinearLayout content = new LinearLayout(this);

        TextView menu = new TextView(this);
        OrderMenu om = orders.getMenu();
        menu.setText(om.getName());
        content.addView(menu);

        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        );

        View view = new View(this);

        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        view.setLayoutParams(viewParams);
        content.addView(view);

        TextView count = new TextView(this);
        count.setText(Integer.toString(orders.getCount())+" 개"+"            ");
        content.addView(count);


        TextView price = new TextView(this);
        price.setText(orders.getPrice() + " 원");
        content.addView(price);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        param.leftMargin = 40;

        Button stateBtn = new Button(this);
        stateBtn.setText(state);
        stateBtn.setTextColor(getResources().getColor(R.color.colorAccent));
        stateBtn.setBackground(getResources().getDrawable(R.drawable.border));
        stateBtn.setLayoutParams(param);

        content.addView(stateBtn);

        itemList.addView(content);

        if(state == "완료"){
            stateBtn.setBackgroundColor(Color.parseColor("#c8c8c8"));
        }

        LinearLayout borderLine = new LinearLayout(this);

        LinearLayout.LayoutParams borderLineParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        borderLineParams.height = 4;
        borderLineParams.topMargin = 20;
        borderLineParams.bottomMargin = 20;
        borderLine.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        borderLine.setLayoutParams(borderLineParams);

        itemList.addView(borderLine);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_orderlist;
    }
}