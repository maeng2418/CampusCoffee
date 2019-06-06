package com.example.campuscoffee;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1  extends Fragment {

    private NetworkService networkService;
    Button btn;
    LinearLayout ll;
    boolean complete = false;
    Vector listId = new Vector<Integer>();
    Vector listName = new Vector<String>();
    Vector listObject = new Vector<Object>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // xml 로 만들어준 프레그먼트를 자바 단에서 만들어줌
        ViewGroup rootGroup =(ViewGroup)inflater.inflate(R.layout.activity_menu, container,false);
        ll = rootGroup.findViewById(R.id.menuLayout);

        if (complete == false){
            NetworkTask mProcessTask = new NetworkTask();
            mProcessTask.execute();
        }else{
            for (int i = 0; i<listId.size() ;i++){
                btn = new Button(getActivity());
                btn.setId(i);
                btn.setText((String)listName.elementAt(i));
                btn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu_item));
                ll.addView(btn);
                btn.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        show((Object) listObject.elementAt(view.getId()));
                    }
                });
            }
        }
        return  rootGroup;

    }

    private class NetworkTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

                ApplicationController application = ApplicationController.getInstance();
                //application.buildNetworkService("382b99e8.ngrok.io");
                application.buildNetworkService("13.125.246.124", 8000);
                networkService = ApplicationController.getInstance().getNetworkService();

                Call<List<Stores>> getCall = networkService.get_stores();
                getCall.enqueue(new Callback<List<Stores>>() {
                    @Override
                    public void onResponse(Call<List<Stores>> call, Response<List<Stores>> response) {
                        if( response.isSuccessful()) {
                            List<Stores> storesList = response.body();

                            for(Stores stores : storesList){
                                if(stores.getCreator()==SubActivity.store+1){

                                    listId.add(stores.getId());
                                    listName.add(stores.getName());
                                    Object object = new Object(stores.getCreator(), stores.getName(), stores.getId(), Integer.parseInt(stores.getPrice()), 0,"");
                                    listObject.add(object);
                                }
                            }

                            for (int i = 0; i<listId.size() ;i++){
                                btn = new Button(getActivity());
                                btn.setId(i);
                                btn.setText((String)listName.elementAt(i));
                                btn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu_item));
                                ll.addView(btn);

                                btn.setOnClickListener(new Button.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        show((Object) listObject.elementAt(view.getId()));
                                    }
                                });
                            }
                            complete = true;
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }


    }


    public void show(Object object) {
        OptionDialog customDialog = new OptionDialog(getActivity(), object);
        customDialog.show();
    }
}