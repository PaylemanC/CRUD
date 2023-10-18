package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity {

    private CRUD crud;

    private EditText textUserName;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;

    private EditText textPassword;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;

    private TextView textoResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        textUserName = findViewById(R.id.textUserName);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        textoResultado = findViewById(R.id.textoResultado);

        crud = new CRUD(this);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textUserName.getText().toString();
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

                long id = crud.insertarUsuario(username, email, password, null, null, null);

                if (id != -1) {
                    textoResultado.setText("Registro insertado con ID: " + id);
                } else {
                    textoResultado.setText("Error al insertar el registro.");
                }
            }
        });
    }
}