package com.example.apptoderos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Registro extends AppCompatActivity {

    private String nombre="", telefono="", email="", descripcion="";
    private TextInputEditText tie_nombre, tie_telefono, tie_email, tie_descripcion;
    private MaterialButton b_registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            nombre=bundle.getString("nombre");
            telefono=bundle.getString("telefono");
            email=bundle.getString("email");
            descripcion=bundle.getString("descripcion");
        }

        tie_nombre=findViewById(R.id.tie_nombre);
        tie_telefono=findViewById(R.id.tie_telefono);
        tie_email=findViewById(R.id.tie_email);
        tie_descripcion=findViewById(R.id.tie_descripcion);
        b_registro=findViewById(R.id.b_guardar);

        tie_nombre.setText(nombre);
        tie_telefono.setText(telefono);
        tie_email.setText(email);
        tie_descripcion.setText(descripcion);

        b_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre= tie_nombre.getText().toString();
                telefono= tie_telefono.getText().toString();
                email= tie_email.getText().toString();
                descripcion= tie_descripcion.getText().toString();

                Intent intent=new Intent(Registro.this, InicioRV.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("telefono",telefono);
                intent.putExtra("email", email);
                intent.putExtra("descripcion", descripcion);
                startActivity(intent);
            }
        });
    }
}