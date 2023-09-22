package com.example.appprog1final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseNameActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

//    Views oprettes
    RadioGroup rdg;
    TextView txtType;
    EditText txtNameInput;
    Button btnNameSave;

//    Variabler fra main
    String resultType;
    Integer resultTypeId;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_name);

//        Text initialiseres
        txtType = findViewById(R.id.txtType);

//        RadioGroup initialiseres
        rdg = findViewById(R.id.rdg);
        rdg.setOnCheckedChangeListener(this);

//        EditText initialiseres
        txtNameInput = findViewById(R.id.txtNameInput);

//        Knap initialiseres
        btnNameSave = findViewById(R.id.btnNameSave);
        btnNameSave.setOnClickListener(this);

//        Intent initialiseres
        intent = getIntent();
//        Forsøger at hente data
        try{
            Toast.makeText(this, "Tidligere data hentet", Toast.LENGTH_LONG).show();
            rdg.check(intent.getIntExtra(MainActivity.TYPE_ID_TO_CHOOSE_NAME, 0));
            txtNameInput.setText(intent.getStringExtra(MainActivity.NAME_TO_CHOOSE_NAME));
        }
        catch(Exception e) {Toast.makeText(this, "Kunne ikke finde noget gemt data", Toast.LENGTH_LONG).show();}
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        På check opdateres variabler til main
        RadioButton rdb = findViewById(rdg.getCheckedRadioButtonId());
        resultTypeId = rdb.getId();
        resultType = (String)rdb.getText();
        Toast.makeText(this, resultType + " valgt", Toast.LENGTH_LONG).show();
//        tekst opdateres
        txtType.setText(resultType + "'s name:");
    }

    @Override
    public void onClick(View v) {
//        Tjekker efter at data er valid
        if (txtNameInput.getText().toString().matches("") || resultType == null) {
            Toast.makeText(this, "Nogen værdier er ikke valgt!", Toast.LENGTH_LONG).show();
            return;
        }
//        type, id og name bliver gemt i hver deres variabel og activityen slutter
        intent.putExtra(
                MainActivity.TYPE_FROM_CHOOSE_NAME,
                resultType);
        intent.putExtra(
                MainActivity.TYPE_ID_FROM_CHOOSE_NAME,
                resultTypeId);
        intent.putExtra(
                MainActivity.NAME_FROM_CHOOSE_NAME,
                txtNameInput.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}