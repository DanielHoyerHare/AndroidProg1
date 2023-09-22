package com.example.todo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    LinearLayout itemView;
    LinearLayout.LayoutParams params;
    Integer itemViewHeight;

    TextView txtName, txtDate;
    CheckBox chkBoxDone;
    Button btnSave, btnAddItem;
    Spinner spinner;

    ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
    ArrayAdapter<ToDoItem> adapter;
    ToDoItem selectedItem;

    ActivityResultLauncher<Intent> addItemLauncher;

    public static final String NAME_FROM_ADD_ITEM = "NameFromAddItem";
    public static final String DATE_FROM_ADD_ITEM = "DateFromAddItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemView = findViewById(R.id.itemView);
        params = (LinearLayout.LayoutParams) itemView.getLayoutParams();
        itemViewHeight = params.height;

        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        chkBoxDone = findViewById(R.id.chkBoxDone);

        spinner = findViewById(R.id.spinner);
        ToDoItem toDoItemDefault = new ToDoItem("Vælg aktivitet");
        ToDoItem toDoItem1 = new ToDoItem("Pille bussemænd", LocalDate.of(2023, 10, 23));
        ToDoItem toDoItem2 = new ToDoItem("Spille tismand", LocalDate.of(2023, 12, 1));
        ToDoItem toDoItem3 = new ToDoItem("Lig en mot", LocalDate.of(2023, 11, 16));
        ToDoItem toDoItem4 = new ToDoItem("Lave opgaven færdig", LocalDate.of(2023, 9, 21));
        toDoList.add(0, toDoItemDefault);
        toDoList.add(toDoItem1);
        toDoList.add(toDoItem2);
        toDoList.add(toDoItem3);
        toDoList.add(toDoItem4);

        updateAdapter();
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(this);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);
        addItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            ToDoItem tdi = new ToDoItem(intent.getStringExtra(NAME_FROM_ADD_ITEM));
                            tdi.setDate((LocalDate)intent.getSerializableExtra(DATE_FROM_ADD_ITEM));
                            toDoList.add(tdi);
                            updateAdapter();
                            adapter.notifyDataSetChanged();
                            spinner.setAdapter(adapter);
                        }
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            params.height = itemViewHeight;
            itemView.setLayoutParams(params);
            return;
        }
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;

        selectedItem = (ToDoItem)spinner.getSelectedItem();
        txtName.setText(selectedItem.getName());
        txtDate.setText((String)selectedItem.getDate());
        chkBoxDone.setChecked(selectedItem.getDone());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:
                selectedItem.setDone(chkBoxDone.isChecked());
                toDoList.set(spinner.getSelectedItemPosition(), selectedItem);
                adapter.notifyDataSetChanged();

                Toast.makeText(this, "Gemt", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnAddItem:
                Intent intent = new Intent(this, AddItem.class);
                addItemLauncher.launch(intent);
        }
    }

    public void updateAdapter(){
        adapter = new ArrayAdapter<>(
                this,
                R.layout.customerspinnerlayout,
                toDoList.toArray(new ToDoItem[toDoList.size()]));
        spinner.setAdapter(adapter);
    }
}