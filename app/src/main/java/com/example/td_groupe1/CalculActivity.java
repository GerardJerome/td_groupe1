package com.example.td_groupe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.td_groupe1.DAO.CalculBaseHelper;
import com.example.td_groupe1.DAO.CalculDao;
import com.example.td_groupe1.enums.TypeOperationEnum;
import com.example.td_groupe1.model.entities.Calcul;

import java.util.regex.Pattern;

public class CalculActivity extends AppCompatActivity {

    private String calcul = "";

    private Integer compteurTaille = 0;
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

    private CalculDao calculDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        textViewCalcul = findViewById(R.id.textViewCalcul);
        buttonOne = findViewById(R.id.button_1);
        buttonOne.setOnClickListener(view -> ajouterNombre("1"));
        buttonTwo = findViewById(R.id.button_2);
        buttonTwo.setOnClickListener(view -> ajouterNombre("2"));
        buttonThree = findViewById(R.id.button_3);
        buttonThree.setOnClickListener(view -> ajouterNombre("3"));
        buttonFour = findViewById(R.id.button_4);
        buttonFour.setOnClickListener(view -> ajouterNombre("4"));
        buttonFive = findViewById(R.id.button_5);
        buttonFive.setOnClickListener(view -> ajouterNombre("5"));
        buttonSix = findViewById(R.id.button_6);
        buttonSix.setOnClickListener(view -> ajouterNombre("6"));
        buttonSeven = findViewById(R.id.button_7);
        buttonSeven.setOnClickListener(view -> ajouterNombre("7"));
        buttonEight = findViewById(R.id.button_8);
        buttonEight.setOnClickListener(view -> ajouterNombre("8"));
        buttonNine = findViewById(R.id.button_9);
        buttonNine.setOnClickListener(view -> ajouterNombre("9"));
        buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(view -> ajouterSymbole(TypeOperationEnum.ADD.getSymbole()));
        buttonSubstract = findViewById(R.id.button_substract);
        buttonSubstract.setOnClickListener(view -> ajouterSymbole(TypeOperationEnum.SUBSTRACT.getSymbole()));
        buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(view -> ajouterSymbole(TypeOperationEnum.DIVIDE.getSymbole()));
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(view -> ajouterSymbole(TypeOperationEnum.MULTIPLY.getSymbole()));
        buttonZero = findViewById(R.id.button_0);
        buttonZero.setOnClickListener(view -> ajouterCharactere("0"));
        calculDao = new CalculDao(new CalculBaseHelper(this,"BDD",1));


    }

    private boolean ajouterSymbole(String symbole) {
        if (TypeOperationEnum.isSymboleAlreadyPresent(calcul)) {
            Toast.makeText(this, getString(R.string.ERROR_SYMBOL), Toast.LENGTH_LONG).show();
        } else if (calcul.isEmpty()) {
            Toast.makeText(this, getString(R.string.ERROR_EMPTY_CALCUL), Toast.LENGTH_LONG).show();
        } else {
            compteurTaille = 0;
            ajouterCharactere(symbole);
        }
        return true;
    }

    private boolean ajouterCharactere(String charactereAAjouter) {
        calcul += charactereAAjouter;
        textViewCalcul.setText(calcul);
        return true;
    }

    private boolean ajouterNombre(String nombre) {
        if (compteurTaille >= 4) {
            Toast.makeText(this, getString(R.string.ERROR_TOO_LONG), Toast.LENGTH_LONG).show();
        } else {
            compteurTaille++;
            ajouterCharactere(nombre);
        }
        return true;
    }

    private boolean verifieLeCalcul(String calcul) {
        return !calcul.isEmpty()
                && !TypeOperationEnum.isSymboleAlreadyPresent
                (String.valueOf(calcul.trim().charAt(calcul.trim().length() - 1)));
    }

    private boolean faisLeCalcul(String calcul) {
        Integer result = 0;
        String[] termeCalcul;
        if (verifieLeCalcul(calcul)) {
            switch (TypeOperationEnum.wichOneInTheString(calcul)) {
                case ADD:
                    termeCalcul = calcul.split(Pattern.quote(TypeOperationEnum.ADD.getSymbole()));
                    result = Integer.parseInt(termeCalcul[0]) + Integer.parseInt(termeCalcul[1]);
                    break;
                case DIVIDE:
                    termeCalcul = calcul.split(TypeOperationEnum.DIVIDE.getSymbole());
                    if (!(Integer.parseInt(termeCalcul[1]) == 0)) {
                        result = Integer.parseInt(termeCalcul[0]) / Integer.parseInt(termeCalcul[1]);
                    } else {
                        Toast.makeText(this, getString(R.string.DIVIDE_ZERO), Toast.LENGTH_LONG).show();
                        return true;
                    }
                    break;
                case MULTIPLY:
                    termeCalcul = calcul.split(TypeOperationEnum.MULTIPLY.getSymbole());
                    result = Integer.parseInt(termeCalcul[0]) * Integer.parseInt(termeCalcul[1]);
                    break;
                case SUBSTRACT:
                    termeCalcul = calcul.split(TypeOperationEnum.SUBSTRACT.getSymbole());
                    result = Integer.parseInt(termeCalcul[0]) - Integer.parseInt(termeCalcul[1]);
                    break;
                case ERROR:
                    result=Integer.parseInt(calcul);
                default:
                    break;
            }
        } else {
            Toast.makeText(this,getString(R.string.BAD_CALCUL),Toast.LENGTH_LONG).show();
            return true;
        }

        Intent intent = new Intent(this, ResultatActivity.class);
        intent.putExtra("CALCUL",calcul);
        intent.putExtra("RESULTAT",result);
        enregistreLeCalcul(result);
        startActivity(intent);
        return true;
    }


    private void enregistreLeCalcul(Integer resultat){
        Calcul monCalcul = new Calcul(calcul,resultat);
        calculDao.create(monCalcul);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        MenuItem boutonNetoyer = menu.findItem(R.id.toolbar_nettoyer);
        boutonNetoyer.setOnMenuItemClickListener(view -> {
            calcul = "";
            textViewCalcul.setText("");
            compteurTaille = 0;
            return true;
        });

        MenuItem boutonCalcul = menu.findItem(R.id.toolbar_calcul);
        boutonCalcul.setOnMenuItemClickListener(view -> faisLeCalcul(calcul));
        return super.onCreateOptionsMenu(menu);
    }
}