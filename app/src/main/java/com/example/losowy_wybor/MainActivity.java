package com.example.losowy_wybor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd, btnChoice;
    private TextView tvList;
    private EditText etAdd;
    private String[] list = new String[10];
    private int nextItem = 0;

    private final ActivityResultLauncher<Intent> choiceLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
               if (result.getResultCode() == RESULT_OK){
                   return;
               }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        tvList = findViewById(R.id.tvList);
        etAdd = findViewById(R.id.etAdd);
        btnChoice =findViewById(R.id.btnChoice);


        tvList.setText(getString(R.string.tv_list, ""));
        btnAdd.setOnClickListener(v -> addItem());
        btnChoice.setOnClickListener(v -> setChoice());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setChoice(){
        return;
    }

    private void addItem(){
        if (nextItem >= list.length){
            Toast.makeText(this, R.string.error_too_many, Toast.LENGTH_SHORT).show();
            return;
        }
        String item = etAdd.getText().toString();
        list[nextItem] = item;
        nextItem += 1;

        tvList.setText(getString(R.string.tv_list, createListString()));
        etAdd.setText("");
    }

    private String createListString(){

        StringBuilder text = new StringBuilder();
        for(String item : list){
            if(item == null){
                break;
            }
            text.append("- ").append(item).append("\n");
        }

        return text.toString();
    }

}