package com.example.apptoderos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class Detalle extends AppCompatActivity {

    private String nombre="", telefono="", email="", descripcion="";
    private TextInputEditText tie_nombre, tie_telefono, tie_email, tie_descripcion;
    private MaterialButton b_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

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
        b_registro=findViewById(R.id.b_registrar);

        tie_nombre.setText(nombre);
        tie_telefono.setText(telefono);
        tie_email.setText(email);
        tie_descripcion.setText(descripcion);

        b_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, getResources().getString(R.string.app_name), Snackbar.LENGTH_LONG)
                        .setAction("¿Desea Editar?", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(Detalle.this, "Registrando", Toast.LENGTH_LONG).show();

                                nombre= tie_nombre.getText().toString();
                                telefono= tie_telefono.getText().toString();
                                email= tie_email.getText().toString();
                                descripcion= tie_descripcion.getText().toString();

                                Intent intent=new Intent(Detalle.this, Registro.class);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("telefono",telefono);
                                intent.putExtra("email", email);
                                intent.putExtra("descripcion", descripcion);
                                startActivity(intent);

                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(Detalle.this, R.color.white))
                        .show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Slide slide=new Slide(Gravity.BOTTOM);
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);
            slide.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });

            getWindow().setReturnTransition(new Fade());
        }

    }
}