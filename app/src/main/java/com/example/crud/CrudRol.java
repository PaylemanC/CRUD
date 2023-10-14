package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CrudRol extends MRSQLiteHelper {
    Context context;

    public CrudRol(Context contexto) {
        super(contexto);
        this.context = contexto;
    }

    //CREATE.
    public long insertar(String nombreRol) {
        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre_rol", nombreRol);

        long id = db.insert("Rol", null, values);

        return id;
    }
}