package com.example.td_groupe1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class CalculActivity extends AppCompatActivity {

    private String calcul ="";
    private TextView textViewCalcul;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonPlus;
    private Button buttonSubstract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonZero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        textViewCalcul = findViewById(R.id.textViewCalcul);
        buttonOne = findViewById(R.id.button_1);
        buttonOne.setOnClickListener(view -> ajouterCharactere("1"));
        buttonTwo = findViewById(R.id.button_2);
        buttonTwo.setOnClickListener(view -> ajouterCharactere("2"));
        buttonThree = findViewById(R.id.button_3);
        buttonThree.setOnClickListener(view -> ajouterCharactere("3"));
        buttonFour = findViewById(R.id.button_4);
        buttonFour.setOnClickListener(view -> ajouterCharactere("4"));
        buttonFive = findViewById(R.id.button_5);
        buttonFive.setOnClickListener(view -> ajouterCharactere("5"));
        buttonSix = findViewById(R.id.button_6);
        buttonSix.setOnClickListener(view -> ajouterCharactere("6"));
        buttonSeven = findViewById(R.id.button_7);
        buttonSeven.setOnClickListener(view -> ajouterCharactere("7"));
        buttonEight = findViewById(R.id.button_8);
        buttonEight.setOnClickListener(view -> ajouterCharactere("8"));
        buttonNine = findViewById(R.id.button_9);
        buttonNine.setOnClickListener(view -> ajouterCharactere("9"));
        buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(view -> ajouterCharactere(" + "));
        buttonSubstract = findViewById(R.id.button_substract);
        buttonSubstract.setOnClickListener(view -> ajouterCharactere(" - "));
        buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(view -> ajouterCharactere(" / "));
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(view -> ajouterCharactere(" x "));
        buttonZero = findViewById(R.id.button_0);
        buttonZero.setOnClickListener(view -> ajouterCharactere("0"));



    }

    private boolean ajouterCharactere(String charactereAAjouter){
        calcul+=charactereAAjouter;
        textViewCalcul.setText(calcul);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        MenuItem boutonNetoyer = menu.findItem(R.id.toolbar_nettoyer);
        boutonNetoyer.setOnMenuItemClickListener(view -> {
            calcul = "";
            textViewCalcul.setText("");
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }
}