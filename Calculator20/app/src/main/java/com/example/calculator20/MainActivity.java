package com.example.calculator20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rdg;
    Button btn;
    EditText input1, input2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdg = findViewById(R.id.rGroup);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        result = findViewById(R.id.result);

    }

    @Override
    public void onClick(View v) {
        if (!checkFields()) return;

        double a = Double.parseDouble(input1.getText().toString()),
                b = Double.parseDouble(input2.getText().toString()),
                c;
        switch (rdg.getCheckedRadioButtonId()){
            case R.id.rBtnAdd:
                c = a+b;
                break;
            case R.id.rBtnMin:
                c = a-b;
                break;
            case R.id.rBtnMul:
                c = a*b;
                break;
            case R.id.rBtnDiv:
                c = a/b;
                break;
            default:
                result.setText("err");
                return;
        }
        result.setText(Double.toString(c));

    }
    public boolean checkFields() {
        String A = input1.getText().toString(),
                B = input2.getText().toString();

        if (A.matches("") || B.matches("")) {
            result.setText("err");
            return false;
        }
        else {
            return true;
        }
    }
}