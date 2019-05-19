package com.example.campuscoffee;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.activity_option;

    private Context context;

    private TextView cancel;
    private TextView ok;
    private TextView count1;
    private TextView count2;

    private String name;

    private int countVal1 = 1;
    private int countVal2 = 0;

    public OptionDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Button minus1 = (Button) findViewById(R.id.minus1);
        Button plus1 = (Button) findViewById(R.id.plus1);

        Button minus2 = (Button) findViewById(R.id.minus2);
        Button plus2 = (Button) findViewById(R.id.plus2);

        cancel = (TextView) findViewById(R.id.cancel);
        ok = (TextView) findViewById(R.id.ok);

        count1 = (TextView) findViewById(R.id.count1);
        count2 = (TextView) findViewById(R.id.count2);

        minus1.setOnClickListener(this);
        minus2.setOnClickListener(this);
        plus1.setOnClickListener(this);
        plus2.setOnClickListener(this);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.plus1:
                countVal1++;
                count1.setText(String.valueOf(countVal1));
                break;
            case R.id.plus2:
                countVal2++;
                count2.setText(String.valueOf(countVal2));
                break;
            case R.id.minus1:
                countVal1--;
                if (countVal1<1)
                    countVal1 = 1;
                count1.setText(String.valueOf(countVal1));
                break;
            case R.id.minus2:
                countVal2--;
                if (countVal2<0)
                    countVal2 = 0;
                count2.setText(String.valueOf(countVal2));
                break;
            case R.id.cancel:
                cancel();
                break;
            case R.id.ok:
                break;
        }
    }
}