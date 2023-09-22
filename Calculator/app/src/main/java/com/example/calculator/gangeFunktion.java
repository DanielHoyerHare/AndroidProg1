package com.example.calculator;

import android.view.View;

public class gangeFunktion implements View.OnClickListener {

    MainActivity main;

    public gangeFunktion(MainActivity mainActivity) {
        main = mainActivity;
    }

    @Override
    public void onClick(View v) {
        if (main.checkFields()) {
            double a = Double.parseDouble(main.tal1.getText().toString()),
                    b = Double.parseDouble(main.tal2.getText().toString()),
                    c = a*b;
            main.tal3.setText(Double.toString(c));
        }
    }
}
