package com.example.losowy_wybor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class RandomChoice extends AppCompatActivity {
    public static final String EXTRA_LIST = "com.example.losowy_wybor.LIST";
    public static final String EXTRA_LENGTH = "com.example.losowy_wybor.LENGTH";
    public static final String EXTRA_CHOICE = "com.example.losowy_wybor.CHOICE";

    private TextView tvRandChoice;
    private Button btnAccept, btnAgain;

    private String[] list;
    private int listLength;
    private String choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random_choice);

        Intent intent = getIntent();
        list = intent.getStringArrayExtra(EXTRA_LIST);
        listLength = intent.getIntExtra(EXTRA_LENGTH, 0);

        tvRandChoice = findViewById(R.id.tvRandChoice);
        btnAgain = findViewById(R.id.btnAgain);
        btnAccept = findViewById(R.id.btnAccept);

        btnAccept.setOnClickListener(v -> acceptResult());
        btnAgain.setOnClickListener(v -> getRandomChoice());

        getRandomChoice();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getRandomChoice(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(listLength);
        Log.d("RandomChoice", "Index wynosi: "+randomIndex+"\nDlugosc listy wynosi: "+listLength);

        choice = list[randomIndex];
        tvRandChoice.setText(getString(R.string.tv_rand_choice, choice));
    }

    private void acceptResult() {
        Intent result = new Intent();
        result.putExtra(EXTRA_CHOICE, choice);
        setResult(RESULT_OK, result);
        finish();
    }
}