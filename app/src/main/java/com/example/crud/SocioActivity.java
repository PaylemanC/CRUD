package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SocioActivity extends AppCompatActivity {

    private CRUD crud;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;
    private EditText textPhone;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;

    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio);

        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        textoResultado = findViewById(R.id.textoResultado);

        crud = new CRUD(this);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textNombre.getText().toString();
                String apellido = textApellido.getText().toString();
                String dni = textDNI.getText().toString();
                String email = textEmail.getText().toString();
                String telefono = textPhone.getText().toString();

                long id = crud.insertarSocio(nombre, apellido, dni, email, telefono);

                if (id != -1) {
                    textoResultado.setText("Registro insertado con ID: " + id);
                } else {
                    textoResultado.setText("Error al insertar el registro.");
                }
            }
        });
    }
}