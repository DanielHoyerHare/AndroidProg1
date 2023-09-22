package com.example.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    RadioGroup rdg;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdg = findViewById(R.id.rGroup);

        btn = findViewById(R.id.btnCheck);
        btn.setOnClickListener(this);

        txt = findViewById(R.id.txtOut);


    }

    @Override
    public void onClick(View v) {
        RadioButton rdb = findViewById(rdg.getCheckedRadioButtonId());
        Toast.makeText(this, rdb.getText() + " valgt", Toast.LENGTH_LONG).show();
        txt.setText(rdb.getText());
    }
}