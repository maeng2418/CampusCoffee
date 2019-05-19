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

public class MainActivity extends BaseActivity {

    private ViewFlipper flipper;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();
        btn1 = (Button) findViewById(R.id.no1);

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

        btn1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}