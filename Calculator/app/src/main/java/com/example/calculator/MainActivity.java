package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3;
    EditText tal1, tal2, tal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        tal1 = findViewById(R.id.tal1);
        tal2 = findViewById(R.id.tal2);
        tal3 = findViewById(R.id.tal3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    double a = Double.parseDouble(tal1.getText().toString()),
                            b = Double.parseDouble(tal2.getText().toString()),
                            c = a+b;
                    tal3.setText(Double.toString(c));
                }
            }
        });

        btn2.setOnClickListener(this);

        gangeFunktion gF = new gangeFunktion(this);
        btn3.setOnClickListener(gF);


    }

    @Override
    public void onClick(View v) {
        if (checkFields()) {
            double a = Double.parseDouble(tal1.getText().toString()),
                    b = Double.parseDouble(tal2.getText().toString()),
                    c = a-b;
            tal3.setText(Double.toString(c));
        }
    }

    public void onClickBtn4(View v) {
        if (checkFields()) {
            double a = Double.parseDouble(tal1.getText().toString()),
                    b = Double.parseDouble(tal2.getText().toString()),
                    c = a/b;
            tal3.setText(Double.toString(c));
        }
    }

    public boolean checkFields() {
        String A = tal1.getText().toString(),
                B = tal2.getText().toString();

        if (A.matches("") || B.matches("")) {
            tal3.setText("err");
            return false;
        }
        else {
            return true;
        }
    }
}