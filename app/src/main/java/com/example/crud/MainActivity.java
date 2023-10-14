package com.example.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MRSQLiteHelper usdbh = new MRSQLiteHelper(this);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos...
        if(db != null) {
            for(int i=1; i<=3; i++) {
                String username = "Username " + i;

                ContentValues usuario = new ContentValues();
                usuario.put("username", username);
                usuario.put("id_rol", 1);
                db.insert("Usuario", null, usuario);
            }

            ContentValues socio = new ContentValues();
            socio.put("nombre_rol", "socio");
            db.insert("Rol", null, socio);

            ContentValues trainer = new ContentValues();
            trainer.put("nombre_rol", "entrenador");
            db.insert("Rol", null, trainer);

            ContentValues usuarioSocio = new ContentValues();
            usuarioSocio.put("username", "Usuario Socio");
            usuarioSocio.put("id_rol", 1);
            db.insert("Usuario", null, usuarioSocio);

            ContentValues usuarioEntrenador = new ContentValues();
            usuarioEntrenador.put("username", "Usuario Entrenador");
            usuarioEntrenador.put("id_rol", 2);
            db.insert("Usuario", null, usuarioEntrenador);

            ContentValues nuevoSocio = new ContentValues();
            nuevoSocio.put("id_usuario", 4);
            db.insert("Socio", null, nuevoSocio);

            ContentValues nuevoEntrenador = new ContentValues();
            nuevoEntrenador.put("id_usuario", 5);
            db.insert("Entrenador", null, nuevoEntrenador);

            //Cerramos la base de datos
            db.close();
        }
    }
}