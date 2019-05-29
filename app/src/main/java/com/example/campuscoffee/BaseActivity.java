package com.example.campuscoffee;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the ActionBar here to configure the way it behaves.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 왼쪽에 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home); //왼쪽 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        */
    }

    protected void initLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the ActionBar here to configure the way it behaves.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 왼쪽에 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home); //왼쪽 버튼을 본인이 만든 아이콘으로 하기 위해 필요
    }

    protected int getLayoutResource() {
        return R.layout.activity_base;
    }

    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(OptionDialog.cartCount != 0){
            menu.findItem(R.id.cart).setTitle("장바구니"+"      "+ Integer.toString(OptionDialog.cartCount));
        }

        if(Reservation.orderCount != 0){
            menu.findItem(R.id.order_list).setTitle("주문내역"+"      "+ Integer.toString(Reservation.orderCount));
        }

        return super.onPrepareOptionsMenu(menu);
    }


    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.point:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "포인트 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            case R.id.coupon:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "쿠폰함 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            case R.id.order_list:
                // User chose the "Settings" item, show the app settings UI...
                Intent orderList = new Intent(getApplicationContext(), OrderList.class);
                startActivity(orderList);
                Reservation.orderCount = 0;
                return true;

            case R.id.cart:
                // User chose the "Settings" item, show the app settings UI...
                Intent reservation = new Intent(getApplicationContext(), Reservation.class);
                startActivity(reservation);
                OptionDialog.cartCount = 0;
                return true;

            case R.id.my_review:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "리뷰 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            case android.R.id.home: {
                finish();
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                return true;
            }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }
}

