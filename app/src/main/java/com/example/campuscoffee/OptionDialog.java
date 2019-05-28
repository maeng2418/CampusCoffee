package com.example.campuscoffee;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OptionDialog extends Dialog implements View.OnClickListener {
    private static final int LAYOUT = R.layout.activity_option;

    private Context context;
    private Object object;

    private TextView cancel;
    private TextView ok;
    private TextView quantity;
    private TextView shot;
    private CheckBox hazelnutSyrup;
    private CheckBox vanillaSyrup;
    private CheckBox chocoSyrup;
    private RadioGroup selectTemperature;
    private Button plusShot;
    private Button minusShot;
    private Button plusQuantity;
    private Button minusQuantity;


    private String name;

    private int quantityValue = 1;
    private int shotValue = 0;

    public OptionDialog(@NonNull Context context, Object object) {
        super(context);
        this.context = context;
        this.object = object;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        mappingViews();
        setListeners();
    }

    private void mappingViews(){
        shot = (TextView) findViewById(R.id.shot);
        plusShot = (Button) findViewById(R.id.plusShot);
        minusShot = (Button) findViewById(R.id.minusShot);

        selectTemperature = (RadioGroup)findViewById(R.id.selectTemperature);

        quantity = (TextView) findViewById(R.id.quantity);
        plusQuantity = (Button) findViewById(R.id.plusQuantity);
        minusQuantity = (Button) findViewById(R.id.minusQuantity);

        hazelnutSyrup = (CheckBox)findViewById(R.id.hazelnutSyrup);
        vanillaSyrup = (CheckBox)findViewById(R.id.vanillaSyrup);
        chocoSyrup = (CheckBox)findViewById(R.id.chocoSyrup);

        cancel = (TextView) findViewById(R.id.cancel);
        ok = (TextView) findViewById(R.id.ok);
    }

    private void setListeners(){
        minusQuantity.setOnClickListener(this);
        minusShot.setOnClickListener(this);
        plusQuantity.setOnClickListener(this);
        plusShot.setOnClickListener(this);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.plusQuantity:
                quantityValue++;
                quantity.setText(String.valueOf(quantityValue));
                break;
            case R.id.plusShot:
                shotValue++;
                shot.setText(String.valueOf(shotValue));
                break;
            case R.id.minusQuantity:
                quantityValue--;
                if (quantityValue <1)
                    quantityValue = 1;
                quantity.setText(String.valueOf(quantityValue));
                break;
            case R.id.minusShot:
                shotValue--;
                if (shotValue <0)
                    shotValue = 0;
                shot.setText(String.valueOf(shotValue));
                break;
            case R.id.cancel:
                cancel();
                break;
            case R.id.ok:
                String syrups = "";
                String temperature;
                syrups = hazelnutSyrup.isChecked()? syrups+"헤이즐넛 ":syrups;
                syrups = vanillaSyrup.isChecked()? syrups+"바닐라 ":syrups;
                syrups = chocoSyrup.isChecked()? syrups+"초코":syrups;
                temperature = selectTemperature.getCheckedRadioButtonId() == R.id.hot? "HOT": "ICE";
                Toast.makeText(getContext(),
                        Integer.toString(quantityValue)+"개, "+
                        temperature+", 샷 추가: "+Integer.toString(shotValue)+"회, "
                        +"시럽 추가: "+syrups
                        ,Toast.LENGTH_LONG).show();
                dismiss();
                Reservation.ObjectList.add(object);
                break;
        }
    }
}