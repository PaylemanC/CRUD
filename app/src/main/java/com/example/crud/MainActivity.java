package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private EditText txtReg;
    private EditText txtVal;
    private Button btnInsertar;
    private Button btnActualizar;
    private TextView txtResultado;
    private CrudRol crudRol;


    public void btnMainSocio(View view){
        Intent intent=new Intent(MainActivity.this, SocioActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtReg = findViewById(R.id.txtReg);
        txtVal = findViewById(R.id.txtVal);
        btnInsertar = findViewById(R.id.btnInsertar);
        txtResultado = findViewById(R.id.txtResultado);


        crudRol = new CrudRol(this);

//        MRSQLiteHelper usdbh = new MRSQLiteHelper(this);
//        SQLiteDatabase db = usdbh.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = txtVal.getText().toString();
                long id = crudRol.insertar(nombreRol);

                if (id != -1) {
                    txtResultado.setText("Registro insertado con ID: " + id);
                } else {
                    txtResultado.setText("Error al insertar el registro.");
                }
            }
        });


        btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = txtVal.getText().toString(); // Obtén el nuevo valor del rol
                int idRol = Integer.parseInt(txtReg.getText().toString()); // Obtén el ID del rol desde el campo "txtIdRol"

                int registrosActualizados = crudRol.actualizar(idRol, nombreRol); // Llama al método de actualización

                if (registrosActualizados > 0) {
                    txtResultado.setText("Se actualizaron " + registrosActualizados + " registro(s).");
                } else {
                    txtResultado.setText("Ningún registro se actualizó.");
                }
            }
        });

        Button btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = txtVal.getText().toString(); // Obtén el nuevo valor del rol
                int idRol = Integer.parseInt(txtReg.getText().toString()); // Obtén el ID del rol desde el campo "txtIdRol"

                //aca llamar al  método de eliminación
                int registrosEliminados = crudRol.eliminar(idRol); // Llama al método de eliminación

                if (registrosEliminados > 0) {
                    txtResultado.setText("Se eliminaron " + registrosEliminados + " registro(s).");
                } else {
                    txtResultado.setText("Ningún registro se eliminó.");
                }
            }
        });






        }
    }
