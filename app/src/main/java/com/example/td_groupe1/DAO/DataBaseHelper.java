package com.example.td_groupe1.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DataBaseHelper extends SQLiteOpenHelper {
    private final int dataBaseVersion;
    private final String dataBaseName;

    public DataBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, null, dataBaseVersion);

        this.dataBaseName = dataBaseName;
        this.dataBaseVersion = dataBaseVersion;
    }

    protected abstract String getCreationSql();
    protected abstract String getDeleteSql();

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreationSql());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(getDeleteSql());
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
