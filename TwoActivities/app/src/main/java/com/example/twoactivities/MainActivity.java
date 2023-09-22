package com.example.twoactivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtMainInput, txtMainOutput;
    Button btnMainToSecond;

    public static final String TEXT_FROM_MAIN = "TextFromMain";
    public static final String TEXT_FROM_SEC = "TextFromSecond";

    ActivityResultLauncher<Intent> secondActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMainInput = findViewById(R.id.txtMainInput);
        txtMainOutput = findViewById(R.id.txtMainOutput);

        btnMainToSecond = findViewById(R.id.btnMainToSecond);

        btnMainToSecond.setOnClickListener(this);

        secondActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        txtMainInput.setText(null);
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            txtMainOutput.setText(intent.getStringExtra(TEXT_FROM_SEC));
                        }
//                        txtMainOutput.setText(result.getData())
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(TEXT_FROM_MAIN, txtMainInput.getText().toString());
        secondActivityLauncher.launch(intent);
    }
}