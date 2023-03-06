package com.example.td_groupe1.model.entities;

public class Calcul extends BaseEntity {
    private String calcul;
    private Integer resultat;

    public String getCalcul() {
        return calcul;
    }

    public void setCalcul(String calcul) {
        this.calcul = calcul;
    }

    public Integer getResultat() {
        return resultat;
    }

    public void setResultat(Integer resultat) {
        this.resultat = resultat;
    }

    public Calcul(String calcul, Integer resultat) {
        this.calcul = calcul;
        this.resultat = resultat;
    }
}
