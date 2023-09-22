package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtChosen;
    Spinner spinner;

    Elev[] elever2 = new Elev[6];
    String[] elever = {"Vælg elev","Nisha", "Nicklas", "Marco", "Niyazi",
            "Leonard", "Magnus", "Ahmed", "Frederik", "Christian",
            "Daniel", "Marcus", "Wiktor", "Kevin", "Jan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtChosen = findViewById(R.id.txtChosen);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> abdidapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                elever);

        abdidapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(abdidapter);

        spinner.setOnItemSelectedListener(this);


//        elever2[0] = new Elev("Magnus", "Spise mad", 22);
//        elever2[1] = new Elev("Marco", "Spille guitar", 23);
//        elever2[2] = new Elev("Marcus", "Spille smart", 20);
//        elever2[3] = new Elev("Niyazi", "Null", 24);
//        elever2[4] = new Elev("Ahmed", "ChatGPT", 28);
//        elever2[5] = new Elev("Jan", "Huske navne på nær Andreas", 47);
//
//        ArrayAdapter<Elev> adapter = new ArrayAdapter<Elev>(
//                this,
//                R.layout.customerspinnerlayout,
//                elever2);
//
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtChosen.setText(elever[position]);
        txtChosen.setText((String)spinner.getSelectedItem());
//        Elev e = (Elev)spinner.getSelectedItem();
//        String name = e.getName(),
//                skill = e.getSkill(),
//                alder = e.getAlder().toString();
//
//        txtChosen.setText("Valgt elev: " + name + "\nSkill: " + skill + "\nalder: " + alder);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        txtChosen.setText("Ingen elev valgt!");
    }
}