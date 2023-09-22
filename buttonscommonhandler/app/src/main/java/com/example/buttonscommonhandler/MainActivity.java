package com.example.buttonscommonhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.knap1);
        btn2 = findViewById(R.id.knap2);

        txt = findViewById(R.id.text);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.knap1:
                btn1Handler();
                break;
            case R.id.knap2:
                btn2Handler();
                break;
        }
//        if (v.getId() == R.id.knap1) {
//            btn1Handler();
//        }
//        else if (v == btn2) {
//            btn2Handler();
//        }

    }

    private void btn1Handler() {
        txt.setText("1");
    }

    private void btn2Handler() {
        txt.setText("2");

    }
}