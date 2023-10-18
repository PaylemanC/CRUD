package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CRUD extends MRSQLiteHelper {
    Context context;

    public CRUD(Context contexto) {
        super(contexto);
        this.context = contexto;
    }

//---------------------------------------------------------------
    //"TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(45) , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(45), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";

    //CREATE USUARIO.
    public long insertarUsuario(String username, String email, String password, String nombre, String apellido, Integer dni) {
        SQLiteDatabase db = super.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username); //NOT NULL
        values.put("email", email); //NOT NULL
        values.put("pass", password); //NOT NULL
        values.put("nombre", nombre); //NULL
        values.put("apellido", apellido); //NULL
        values.put("dni", dni); //NULL
        values.put("id_rol", 1); //NOT NULL

        long idUsuario = db.insert("Usuario", null, values);

        db.close();

        return idUsuario;
    }

    // "TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";

    private int returnId (int idRol, String tabla, String campo) {
        SQLiteDatabase db = super.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tabla + " WHERE " + campo + " = ?", new String[]{String.valueOf(idRol)});
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

        return rowCount;
    }

    //UPDATE
    public int actualizar(int idRol, String nuevoNombreRol) {
        SQLiteDatabase db = super.getWritableDatabase();
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
        SQLiteDatabase db = super.getWritableDatabase();
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
