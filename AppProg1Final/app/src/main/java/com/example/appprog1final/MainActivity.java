package com.example.appprog1final;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnName, btnColor;
    TextView txtName;

    Intent intent;

//    Variabler til sendelse af variabler til ChooseName
    public static final String TYPE_TO_CHOOSE_NAME = "TypeToChooseName";
    public static final String TYPE_ID_TO_CHOOSE_NAME = "TypeIdToChooseName";
    public static final String NAME_TO_CHOOSE_NAME = "NameToChooseName";

//    Variabler til modtagelse af variabler fra ChooseName
    public static final String TYPE_FROM_CHOOSE_NAME = "TypeFromChooseName";
    public static final String TYPE_ID_FROM_CHOOSE_NAME = "TypeIdFromChooseName";
    public static final String NAME_FROM_CHOOSE_NAME = "NameFromChooseName";

//    Variabler fra ChooseName
    String type, name;
    Integer typeId;

    ActivityResultLauncher<Intent> chooseNameActivityLauncher;

//    Variabler til sendelse af variabler til ChooseColor
    public static final String TYPE_TO_CHOOSE_COLOR = "TypeToChooseColor";
    public static final String R_TO_CHOOSE_COLOR = "RToChooseColor";
    public static final String G_TO_CHOOSE_COLOR = "GToChooseColor";
    public static final String B_TO_CHOOSE_COLOR = "BToChooseColor";

//    Variabler til modtagelse af variabler fra ChooseColor
    public static final String R_FROM_CHOOSE_COLOR = "RFromChooseColor";
    public static final String G_FROM_CHOOSE_COLOR = "GFromChooseColor";
    public static final String B_FROM_CHOOSE_COLOR = "BFromChooseColor";

//    Variabler fra ChooseColor med standard værdi
    String r = "FF", g = "FF", b = "FF";

    ActivityResultLauncher<Intent> chooseColorActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Views initialisering
        btnName = findViewById(R.id.btnName);
        btnColor = findViewById(R.id.btnColor);

        btnName.setOnClickListener(this);
        btnColor.setOnClickListener(this);

        txtName = findViewById(R.id.txtName);

//        Activity Launchernes initialisering
        chooseNameActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
//                            Gemmer type samt id og navn og opdatere teksten
                            type = intent.getStringExtra(TYPE_FROM_CHOOSE_NAME);
                            typeId = intent.getIntExtra(TYPE_ID_FROM_CHOOSE_NAME, 0);
                            name = intent.getStringExtra(NAME_FROM_CHOOSE_NAME);
                            txtName.setText(type + "'s name: " + name);
                        }
                    }
                });
        chooseColorActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
//                            Gemmer rgb værdier og sætter baggrunds farven med HEXtoColor
                            r = intent.getStringExtra(R_FROM_CHOOSE_COLOR);
                            g = intent.getStringExtra(G_FROM_CHOOSE_COLOR);
                            b = intent.getStringExtra(B_FROM_CHOOSE_COLOR);
                            txtName.setBackgroundColor(HEXtoColor.RGB(r,g,b));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnName:
                intent = new Intent(this, ChooseNameActivity.class);
//                Hvis type indeholder data bliver gemt data sendt med til ChooseName
                if (type != null) {
                    intent.putExtra(TYPE_TO_CHOOSE_NAME, type);
                    intent.putExtra(TYPE_ID_TO_CHOOSE_NAME, typeId);
                    intent.putExtra(NAME_TO_CHOOSE_NAME, name);
                }
                chooseNameActivityLauncher.launch(intent);
                break;
            case R.id.btnColor:
                intent = new Intent(this, ChooseColorActivity.class);
//                Hvis man ikke har valgt type og navn i ChooseName stopper den her
//                Ellers bliver nuværende rgb værdi og type sendt og launchet
                if (type == null) {
                    Toast.makeText(this, "Vælg navn først!", Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra(TYPE_TO_CHOOSE_COLOR, type);
                intent.putExtra(R_TO_CHOOSE_COLOR, r);
                intent.putExtra(G_TO_CHOOSE_COLOR, g);
                intent.putExtra(B_TO_CHOOSE_COLOR, b);

                chooseColorActivityLauncher.launch(intent);
                break;
        }

    }
}