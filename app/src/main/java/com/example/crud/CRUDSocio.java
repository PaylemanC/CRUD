package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CRUDSocio extends MRSQLiteHelper {
    Context context;

    public CRUDSocio(Context contexto) {
        super(contexto);
        this.context = contexto;
    }

//---------------------------------------------------------------

    private SQLiteDatabase returnDb() {
        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        return db;
    }

    //CREATE.
    public long insertar(String nombreRol) {
        SQLiteDatabase db = returnDb();

        ContentValues values = new ContentValues();
        values.put("nombre_rol", nombreRol);

        long id = db.insert("Rol", null, values);
        db.close();
        return id;
    }

    private int returnId (int idRol, String tabla, String campo) {
        SQLiteDatabase db = returnDb();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tabla + " WHERE " + campo + " = ?", new String[]{String.valueOf(idRol)});
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

        return rowCount;
    }

    //UPDATE
    public int actualizar(int idRol, String nuevoNombreRol) {
        SQLiteDatabase db = returnDb();
        int rowCount = returnId(idRol, "Rol", "id_rol");

        if (rowCount > 0) {
            // El rol con el ID proporcionado existe, procede con la actualización
            ContentValues values = new ContentValues();
            values.put("nombre_rol", nuevoNombreRol);

            int registrosActualizados = db.update("Rol", values, "id_rol = ?", new String[]{String.valueOf(idRol)});

            // Cierra la base de datos después de la operación
            db.close();

            return registrosActualizados;
        } else {
            // El rol con el ID proporcionado no existe, no se puede actualizar
            db.close();
            return 0; // O un valor que indique que no se realizó la actualización
        }
    }

    //DELETE
    public int eliminar(int idRol) {
        SQLiteDatabase db = returnDb();
        int rowCount = returnId(idRol, "Rol", "id_rol");

        if (rowCount > 0) {
            // El rol con el ID proporcionado existe, procede con la eliminación
            int registrosEliminados = db.delete("Rol", "id_rol = ?", new String[]{String.valueOf(idRol)});

            // Cierra la base de datos después de la operación
            db.close();

            return registrosEliminados;
        } else {
            // El rol con el ID proporcionado no existe, no se puede eliminar
            db.close();
            return 0; // O un valor que indique que no se realizó la eliminación
        }
    }
}
