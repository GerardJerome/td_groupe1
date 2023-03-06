package com.example.td_groupe1.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.td_groupe1.model.entities.Calcul;


public class CalculDao extends BaseDao<Calcul> {
    public static String TABLE_NAME = "CALCULS";
    public static String COLUMN_CALCUL = "CALCUL";
    public static String COLUMN_RESULTAT = "RESULTAT";

    public CalculDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void putValues(ContentValues values, Calcul entity) {
        values.put(COLUMN_CALCUL, entity.getCalcul());
        values.put(COLUMN_RESULTAT, entity.getResultat());

    }

    @Override
    protected Calcul getEntity(Cursor cursor) {
        Integer indexColumnCalcul = cursor.getColumnIndex(COLUMN_CALCUL);
        Integer indexColumnResultat = cursor.getColumnIndex(COLUMN_RESULTAT);
        if( cursor.getCount()>0){
            Calcul monCalcul = new Calcul(cursor.getString(indexColumnCalcul), cursor.getInt(indexColumnResultat));
            return monCalcul;
        }
        return null;
    }
}
