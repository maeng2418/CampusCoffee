package com.example.campuscoffee;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

public class Reservation extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initLayout();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_reservation;
    }
}

