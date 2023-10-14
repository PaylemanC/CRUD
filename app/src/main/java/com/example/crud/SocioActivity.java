package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class SocioActivity extends AppCompatActivity {

    private CRUDSocio crudSocio;

    private EditText textUserName;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textFechaNac;
    private EditText textEmail;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio);

        textUserName = findViewById(R.id.textUserName);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textFechaNac = findViewById(R.id.textFechaNac);
        textEmail = findViewById(R.id.textEmail);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        crudSocio = new CRUDSocio(this);
    }
}