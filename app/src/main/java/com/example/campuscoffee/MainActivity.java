package com.example.campuscoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends BaseActivity {

    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

        //cafe event view flipper
        flipper=(ViewFlipper)findViewById(R.id.viewFlipper);

        ImageView linear[]=new ImageView[3];
        for(int i=0; i<3; i++){
            linear[i]=new ImageView(getApplicationContext());
        }

        linear[0].setImageResource(R.drawable.ad1);
        flipper.addView(linear[0]);
        linear[1].setImageResource(R.drawable.ad2);
        flipper.addView(linear[1]);
        linear[2].setImageResource(R.drawable.ad3);
        flipper.addView(linear[2]);

        flipper.setFlipInterval(3000);
        flipper.startFlipping();

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.no1:
                CafeActivity.store = 1;
                break;
            case R.id.no2:
                CafeActivity.store = 2;
                break;
            case R.id.no3:
                CafeActivity.store = 3;
                break;
            case R.id.no4:
                CafeActivity.store = 4;
                break;

        }
        Intent intent = new Intent(getApplicationContext(), CafeActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}