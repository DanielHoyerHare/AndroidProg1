package com.example.appprog1final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseColorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Intent intent;

//    Liste med HEX værdier
    String[] HEXvalsS = {"00","10", "20", "30", "40", "50",
                    "60", "70", "80", "90", "A0", "B0",
                    "C0", "D0", "E0", "F0", "FF"};

//    Laver string array til en arraylist for at kunne søge på værdierne nemt
    ArrayList<String> HEXvals = new ArrayList<>(Arrays.asList(HEXvalsS));

//    Views oprettet
    TextView txtTypeColor;
    Spinner spinnerRed, spinnerGreen, spinnerBlue;
    Button btnColorSave;
    LinearLayout color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);

//        Henter intent
        intent = getIntent();

//        Sætter overskrift
        txtTypeColor = findViewById(R.id.txtTypeColor);
        txtTypeColor.setText(intent.getStringExtra(MainActivity.TYPE_TO_CHOOSE_COLOR) + "'s color");

//        Farvefælt initialiseres
        color = findViewById(R.id.color);

//        Spinnere initialiseres
        spinnerRed = findViewById(R.id.spinnerRed);
        spinnerGreen = findViewById(R.id.spinnerGreen);
        spinnerBlue = findViewById(R.id.spinnerBlue);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.customspinnerlayout,
                HEXvals);

        spinnerRed.setAdapter(adapter);
        spinnerGreen.setAdapter(adapter);
        spinnerBlue.setAdapter(adapter);

//        Sætter værdien på spinnerne til tidligere gemte værdier
        spinnerRed.setSelection(
                HEXvals.indexOf(
                    intent.getStringExtra(MainActivity.R_TO_CHOOSE_COLOR)));
        spinnerGreen.setSelection(
                HEXvals.indexOf(
                        intent.getStringExtra(MainActivity.G_TO_CHOOSE_COLOR)));
        spinnerBlue.setSelection(
                HEXvals.indexOf(
                        intent.getStringExtra(MainActivity.B_TO_CHOOSE_COLOR)));

        spinnerRed.setOnItemSelectedListener(this);
        spinnerGreen.setOnItemSelectedListener(this);
        spinnerBlue.setOnItemSelectedListener(this);

//        Knap initialiseres
        btnColorSave = findViewById(R.id.btnColorSave);
        btnColorSave.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Sætter baggrundsfarve til valgt værdi hver gang en værdi opdateres
        color.setBackgroundColor(HEXtoColor.RGB(
                (String)spinnerRed.getSelectedItem(),
                (String)spinnerGreen.getSelectedItem(),
                (String)spinnerBlue.getSelectedItem()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
//        RGB værdi bliver gemt i hver deres variabel og activityen slutter
        intent.putExtra(
                MainActivity.R_FROM_CHOOSE_COLOR,
                (String)spinnerRed.getSelectedItem());
        intent.putExtra(
                MainActivity.G_FROM_CHOOSE_COLOR,
                (String)spinnerGreen.getSelectedItem());
        intent.putExtra(
                MainActivity.B_FROM_CHOOSE_COLOR,
                (String)spinnerBlue.getSelectedItem());
        setResult(RESULT_OK, intent);
        finish();
    }
}