package com.example.campuscoffee;

import android.graphics.Color;
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

import com.example.campuscoffee.DTOs.Stores;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    private NetworkService networkService;
    Button menuButton;
    LinearLayout menuLayout;
    boolean complete = false;
    Vector menuId = new Vector<Integer>();
    Vector menuName = new Vector<String>();
    Vector menuObject = new Vector<com.example.campuscoffee.DTOs.Object>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // xml 로 만들어준 프레그먼트를 자바 단에서 만들어줌
        ViewGroup rootGroup =(ViewGroup)inflater.inflate(R.layout.fragment_menu, container,false);
        menuLayout = rootGroup.findViewById(R.id.menuLayout);

        if (complete == false){
            NetworkTask mProcessTask = new NetworkTask();
            mProcessTask.execute();
        }else{
            for (int i = 0; i< menuId.size() ; i++){
                //using list already exist
                menuButton = new Button(getActivity());
                menuButton.setId(i);
                menuButton.setText((String) menuName.elementAt(i));
                menuButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu_item));
                menuLayout.addView(menuButton);
                menuButton.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        show((com.example.campuscoffee.DTOs.Object) menuObject.elementAt(view.getId()));
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
        //create menu view by using list from server in background
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

                ApplicationController application = ApplicationController.getInstance();
                application.buildNetworkService("13.125.246.124", 8000);
                networkService = ApplicationController.getInstance().getNetworkService();

                Call<List<Stores>> getCall = networkService.get_stores();
                getCall.enqueue(new Callback<List<Stores>>() {
                    @Override
                    public void onResponse(Call<List<Stores>> call, Response<List<Stores>> response) {
                        if( response.isSuccessful()) {
                            List<Stores> storesList = response.body();

                            for(Stores stores : storesList){
                                if(stores.getCreator()== CafeActivity.store+1){
                                    //parse list and create menuObject
                                    menuId.add(stores.getId());
                                    menuName.add(stores.getName());
                                    com.example.campuscoffee.DTOs.Object object = new com.example.campuscoffee.DTOs.Object(stores.getCreator(), stores.getName(), stores.getId(), Integer.parseInt(stores.getPrice()), 0,"");
                                    menuObject.add(object);
                                }
                            }

                            for (int i = 0; i< menuId.size() ; i++){
                                //create menu view button
                                menuButton = new Button(getActivity());
                                menuButton.setId(i);
                                menuButton.setText((String) menuName.elementAt(i));
                                menuButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menu_item));
                                menuLayout.addView(menuButton);
                                menuButton.setOnClickListener(new Button.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        show((com.example.campuscoffee.DTOs.Object) menuObject.elementAt(view.getId()));
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

    public void show(com.example.campuscoffee.DTOs.Object object) {
        OptionDialog customDialog = new OptionDialog(getActivity(), object);
        customDialog.show();
    }
}