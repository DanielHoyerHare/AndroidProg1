package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    EditText txtSecInput, txtSecOutput;
    Button btnBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        txtSecInput = findViewById(R.id.txtSecInput);
        txtSecOutput = findViewById(R.id.txtSecOutput);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        intent = getIntent();
        txtSecOutput.setText(intent.getStringExtra(MainActivity.TEXT_FROM_MAIN));


    }

    @Override
    public void onClick(View v) {
        intent.putExtra(
                MainActivity.TEXT_FROM_SEC,
                txtSecInput.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}