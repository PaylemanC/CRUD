package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        db.close();
        return id;
    }

    //UPDATE
    public int actualizar(int idRol, String nuevoNombreRol) {
        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        // Verificar si el rol con el ID proporcionado existe
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Rol WHERE id_rol = ?", new String[]{String.valueOf(idRol)});
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

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
        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        // Verificar si el rol con el ID proporcionado existe antes de eliminarlo
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Rol WHERE id_rol = ?", new String[]{String.valueOf(idRol)});
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

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

//    METODO UPDATE SIN VALIDACIONES
//    public int actualizar(int idRol, String nuevoNombre) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("nombre_rol", nuevoNombre);
//
//        // Define la cláusula WHERE para identificar el registro a actualizar
//        String whereClause = "id_rol = ?";
//        String[] whereArgs = {String.valueOf(idRol)};
//
//        int registrosActualizados = db.update("Rol", values, whereClause, whereArgs);
//
//        db.close();
//        return registrosActualizados;
//    }

}