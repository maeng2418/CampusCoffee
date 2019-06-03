package com.example.campuscoffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.Button;

public class CafeActivity extends BaseActivity {

    public static int store;

    MenuFragment menuFragment;
    InfoFragment infoFragment;
    ReviewFragment reviewFragment;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        // 프래그먼트를 보여주기
        menuFragment = new MenuFragment();
        infoFragment = new InfoFragment();
        reviewFragment = new ReviewFragment();

        //프래그먼트를 매니져로 보여줌.
        getSupportFragmentManager().beginTransaction().add(R.id.container, menuFragment).commit();

        //탭기능 구성
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
                    selected = menuFragment;
                }else if(position == 1 ){
                    selected = infoFragment;
                }else if(position == 2 ){
                    selected = reviewFragment;
                }
                //선택된 프레그먼트로 바꿔줌
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }



            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    @Override
    protected int getLayoutResource() {
        switch (store){
            case 1:
                return R.layout.activity_1hak;
            case 2:
                return R.layout.activity_2hak;
            case 3:
                return R.layout.activity_libarary;
            case 4:
                return R.layout.activity_cube;
            default:
                return 0;
        }
    }
}

