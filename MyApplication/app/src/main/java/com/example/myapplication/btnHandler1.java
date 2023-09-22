package com.example.myapplication;

import android.view.View;

public class btnHandler1 implements View.OnClickListener {

    MainActivity main;

    public btnHandler1(MainActivity mainActivity) {
        main = mainActivity;
    }

    @Override
    public void onClick(View v) {
        main.txt.setText("Fuck yea");
    }
}
