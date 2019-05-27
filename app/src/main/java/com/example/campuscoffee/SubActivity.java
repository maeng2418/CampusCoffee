package com.example.campuscoffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

public class SubActivity extends BaseActivity {

    public static int store;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        // 프래그먼트를 보여주기
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //프래그먼트를 매니져로 보여줌.
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        //3탭기능 구성
        TabLayout tabs=(TabLayout)findViewById(R.id.tabs1);
        tabs.addTab(tabs.newTab().setText("메뉴"));
        tabs.addTab(tabs.newTab().setText("정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

        //탭버튼을 클릭했을 때 프레그먼트 동작
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //선택된 탭 번호 반환
                int position =tab.getPosition();

                Fragment selected = null;

                if(position == 0 ){

                    selected = fragment1;

                }else if(position == 1 ){

                    selected =fragment2;

                }else if(position == 2 ){

                    selected =fragment3;
                }

                //선택된 프레그먼트로 바꿔줌
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }



            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int getLayoutResource() {

        switch (store){
            case 1:
                return R.layout.activity_sub1;
            case 2:
                return R.layout.activity_sub2;
            case 3:
                return R.layout.activity_sub3;
            case 4:
                return R.layout.activity_sub4;
            default:
                return 0;
        }

    }
}

