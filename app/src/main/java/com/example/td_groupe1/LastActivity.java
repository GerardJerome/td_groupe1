package com.example.td_groupe1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.td_groupe1.DAO.CalculBaseHelper;
import com.example.td_groupe1.DAO.CalculDao;
import com.example.td_groupe1.model.entities.Calcul;

public class LastActivity extends AppCompatActivity {
    private CalculDao calculDao;
    private TextView textViewDernierCalcul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        calculDao = new CalculDao(new CalculBaseHelper(this,"BDD",1));
        Calcul monCalcul = calculDao.lastOrNull();
        textViewDernierCalcul= findViewById(R.id.textViewDernierCalcul);
        if(monCalcul!=null){
            textViewDernierCalcul.setText(monCalcul.getCalcul() + " = "+monCalcul.getResultat());
        }else{
            textViewDernierCalcul.setText("AUCUN CALCUL");
        }
    }
}