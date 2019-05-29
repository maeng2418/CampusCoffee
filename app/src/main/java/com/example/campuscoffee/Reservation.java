package com.example.campuscoffee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FieldMap;

class Object {
    int store;
    String menu;
    int menuId;
    int price;
    int count;

    String option;



    public Object(int store, String menu, int menuId, int price, int count, String option){
        this.store = store;
        this.menu = menu;
        this.menuId = menuId;
        this.price = price;
        this.option = option;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

public class Reservation extends BaseActivity {

    private NetworkService networkService;
    int total = 0;
    LinearLayout itemList;

    Vector layoutList = new Vector <LinearLayout>();

    static Vector ObjectList = new Vector <Object>();

    static Vector copyObjectList = new Vector <Object>();

    static int orderCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();


        if (ObjectList.capacity() != 0) {
            for (int i = 0; i<ObjectList.size(); i++){
                Object object = (Object) ObjectList.elementAt(i);
                createReservation(object);
                total += object.price*object.count;
            }
        }

        TextView totalView = (TextView) findViewById(R.id.total);
        totalView.setText(Integer.toString(total)+"원");

        Button buy = (Button) findViewById(R.id.buy);

        buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(total!=0)
                {
                    Toast.makeText(getApplicationContext(),"주문이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    for (int i = 0; i<ObjectList.size();i++){
                        Object object = (Object) ObjectList.elementAt(i);
                        payment(object.store, object.menuId, object.count, Integer.toString(object.price));
                    }

                    for(int i =0; i<layoutList.size(); i++){
                        LinearLayout ll = (LinearLayout)layoutList.elementAt(i);
                        ll.removeAllViews();
                    }
                    copyObjectList = (Vector)ObjectList.clone();
                    ObjectList.removeAllElements();
                    total = 0;
                    TextView resetTotal = (TextView) findViewById(R.id.total);
                    resetTotal.setText("0원");
                    orderCount ++;




                }
                else
                    Toast.makeText(getApplicationContext(),"장바구니가 비어있습니다..",Toast.LENGTH_SHORT).show();
            }
        });


    }
//
    public void createReservation (Object obj){
        final Object  element = obj;
        //LinearLayout itemList;
        switch (obj.store){
            case 2:
                itemList = (LinearLayout) findViewById(R.id.itemList1);
                layoutList.add(itemList);
                break;
            case 3:
                itemList = (LinearLayout) findViewById(R.id.itemList2);
                layoutList.add(itemList);
                break;
            case 4:
                itemList = (LinearLayout) findViewById(R.id.itemList3);
                layoutList.add(itemList);
                break;
            case 5:
                itemList = (LinearLayout) findViewById(R.id.itemList4);
                layoutList.add(itemList);
                break;
            default:
                itemList = null;
        }

        final LinearLayout content = new LinearLayout(this);
        //content.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams contentParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView menu = new TextView(this);
        menu.setText(obj.menu);
        content.addView(menu);

        View view = new View(this);

        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        );

        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        view.setLayoutParams(viewParams);
        content.addView(view);

        TextView price = new TextView(this);
        price.setText(Integer.toString(obj.price*obj.count) + " 원");
        content.addView(price);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        param.leftMargin = 40;

        Button delete = new Button(this);
        delete.setText("삭제");
        delete.setId(obj.menuId);
        delete.setTextColor(getResources().getColor(R.color.colorAccent));
        delete.setBackground(getResources().getDrawable(R.drawable.border));
        delete.setLayoutParams(param);



        content.addView(delete);

        itemList.addView(content);

        final LinearLayout optionList = new LinearLayout(this);
        optionList.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams optionParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        optionParam.topMargin = 40;

        TextView option = new TextView(this);
        option.setText("옵션 : " + obj.option);
        option.setLayoutParams(optionParam);
        optionList.addView(option);

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

        optionList.addView(borderLine);

        itemList.addView(optionList);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.removeAllViews();
                optionList.removeAllViews();
                total -= element.price*element.count;
                ObjectList.remove(element);
                TextView reTotal = (TextView) findViewById(R.id.total);
                reTotal.setText(Integer.toString(total) +"원");
            }
        });

    }

    public void payment(int store, int menu, int count, String price){
        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService("574dcfdb.ngrok.io");
        //application.buildNetworkService("127.0.0.1", 8000);
        networkService = ApplicationController.getInstance().getNetworkService();

        HashMap<String, java.lang.Object> input = new HashMap<>();
        input.put("buyer", "maeng");
        input.put("menu", menu);
        input.put("count", count);
        input.put("price", Integer.toString(Integer.parseInt(price)*count));
        //input.put("timer", "doing");
        networkService.post_stores(store, input).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if (response.isSuccessful()) {
                    Menu menuList = response.body();
                    if (menuList != null) {
                        Log.d("data.getId()", menuList.getId() + "");
                        Log.d("data.getCount()", menuList.getCount() + "");
                        Log.d("data.getOption()", menuList.getOption() + "");
                        Log.d("data.getPrice()", menuList.getPrice() + "");
                        Log.d("data.getCreator()", menuList.getCreator() + "");
                        Log.d("data.getStore()", menuList.getStore() + "");
                        Log.d("data.getMenu()", menuList.getMenu() + "");
                        Log.e("postData end", "======================================");

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Menu> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_reservation;
    }

}
