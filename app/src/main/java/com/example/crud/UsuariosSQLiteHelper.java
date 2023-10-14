package com.example.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreateRol ="CREATE TABLE Rol (id_rol INTEGER PRIMARY KEY AUTOINCREMENT, nombre_rol CHAR(10) NOT NULL)";

    public UsuariosSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreateRol);
        db.execSQL(sqlCreateRol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
          db.execSQL("DROP TABLE IF EXISTS Rol");
          db.execSQL(sqlCreateRol);
    }
}
