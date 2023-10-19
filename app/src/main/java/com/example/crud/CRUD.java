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
    public long insertarUsuario(String username, String email, String password, String nombre, String apellido, String dni) {
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

    private int returnId (int idUsuario, String tabla, String campo) {
        SQLiteDatabase db = super.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tabla + " WHERE " + campo + " = ?", new String[]{String.valueOf(idUsuario)});
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

        return rowCount;
    }

    public long insertarSocio(String nombre, String apellido, String dni, String email, String telefono) {
        SQLiteDatabase db = super.getWritableDatabase();

        ContentValues valuesUser = new ContentValues();
        valuesUser.put("nombre", nombre); //NULL
        valuesUser.put("apellido", apellido); //NULL
        valuesUser.put("dni", dni); //NULL
        valuesUser.put("email", email); //NOT NULL
        valuesUser.put("tel", telefono); //NOT NULL
        valuesUser.put("id_rol", 2); //NOT NULL

        long idUsuario = db.insert("Usuario", null, valuesUser);

        if (idUsuario != -1) {
            ContentValues valuesSocio = new ContentValues();
            valuesSocio.put("id_usuario", idUsuario);
            long idSocio = db.insert("Socio", null, valuesSocio);

            db.close();

            return idSocio;
        } else {
            db.close();
            return -1;
        }
    }

//--------------------NO PRIORIDAD--------------------------------

    //UPDATE
//    public int actualizar(int idUsuario, String nuevoNombreRol) {
//        SQLiteDatabase db = super.getWritableDatabase();
//        int rowCount = returnId(idUsuario, "Usuario", "id_usuario");
//
//        if (rowCount > 0) {
//
//
//            db.close();
//            return 1;
//        } else {
//
//
//            return 0; // O un valor que indique que no se realizó la actualización
//        }
//    }

    //DELETE
//    public int eliminarUsuario(int idUsuario) {
//        SQLiteDatabase db = super.getWritableDatabase();
//        int rowCount = returnId(idUsuario, "Usuario", "id_usuario");
//
//        if (rowCount > 0) {
//            // El usuario con el ID proporcionado existe, procede con la eliminación
//            int usuarioEliminado = db.delete("Usuario", "id_usuario = ?", new String[]{String.valueOf(idUsuario)});
//
//            // Cierra la base de datos después de la operación
//            db.close();
//
//            return usuarioEliminado;
//        } else {
//            // El rol con el ID proporcionado no existe, no se puede eliminar
//            db.close();
//            return 0; // O un valor que indique que no se realizó la eliminación
//        }
//    }
}
