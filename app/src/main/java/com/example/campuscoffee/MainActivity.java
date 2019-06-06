package com.example.campuscoffee;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
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

import java.security.MessageDigest;

public class MainActivity extends BaseActivity {

    private ViewFlipper flipper;
    //Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

        getHashKey(getApplicationContext());

        //btn1 = (Button) findViewById(R.id.no1);

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

        /*btn1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        */
    }

    // 프로젝트의 해시키를 반환

    @Nullable
    public static String getHashKey(Context context) {
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0)); //(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }

        if (keyHash != null) {
            return keyHash;
        } else {
            return null;
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.no1:
                SubActivity.store = 1;
                break;
            case R.id.no2:
                SubActivity.store = 2;
                break;
            case R.id.no3:
                SubActivity.store = 3;
                break;
            case R.id.no4:
                SubActivity.store = 4;
                break;

        }
        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}