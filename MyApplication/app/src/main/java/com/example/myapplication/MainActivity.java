package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn, btn2;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtLabel);
        btn2 = findViewById(R.id.btn2);
        btnHandler1 bh = new btnHandler1(this);
        btn2.setOnClickListener(bh);

        btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Der blev trykket på knap 1 pt2");
            }
        });
    }

    @Override
    public void onClick(View v) {
        txt.setText("Der blev trykket på knap 1");
        Log.d("Dan", "Knap blev trykket");
    }
}