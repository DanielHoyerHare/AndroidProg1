package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;

public class AddItem extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnBack;
    EditText txtAddName;
    DatePicker datePicker;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        datePicker = findViewById(R.id.datePicker);
        datePicker.setMinDate(System.currentTimeMillis());

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);


        txtAddName = findViewById(R.id.txtAddName);

        intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                setResult(RESULT_CANCELED, intent);
                finish();
            case R.id.btnAdd:
                intent.putExtra(
                        MainActivity.NAME_FROM_ADD_ITEM,
                        txtAddName.getText().toString());
                LocalDate localDate = LocalDate.of(
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth()
                );
                intent.putExtra(
                        MainActivity.DATE_FROM_ADD_ITEM,
                        localDate);
                setResult(RESULT_OK, intent);
                finish();
        }
    }
}