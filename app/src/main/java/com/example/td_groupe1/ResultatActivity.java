package com.example.td_groupe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultatActivity extends AppCompatActivity {
    TextView textViewResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        Intent intent = getIntent();
        String calcul = intent.getStringExtra("CALCUL");
        Integer resultat = intent.getIntExtra("RESULTAT",0);
        textViewResultat = findViewById(R.id.textviewResultat);
        textViewResultat.setText(calcul+" = "+resultat);

    }

}