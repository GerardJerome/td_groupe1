package com.example.td_groupe1.enums;

public enum TypeOperationEnum {
    ADD(" + "),
    SUBSTRACT(" - "),
    MULTIPLY(" x "),
    DIVIDE(" / "),
    ERROR("ERROR")
    ;

    private String symbole;


    TypeOperationEnum(String symbole) {
        this.symbole = symbole;
    }


    public String getSymbole() {
        return symbole;
    }

    public static  Boolean isSymboleAlreadyPresent(String calcul){
        for(TypeOperationEnum key : values()){
            if(calcul.trim().contains(key.getSymbole().trim())){
                return true;
            }
        }
        return false;
    }

    public static TypeOperationEnum wichOneInTheString(String calcul){
        for(TypeOperationEnum key : values()){
            if(calcul.trim().contains(key.getSymbole().trim())){
                return key;
            }
        }
        return TypeOperationEnum.ERROR;
    }

}
