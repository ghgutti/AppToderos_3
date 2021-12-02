package com.example.apptoderos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Archivo extends AppCompatActivity {

    private EditText et_info;
    private Button b_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_info=findViewById(R.id.et_info);
        b_guardar=findViewById(R.id.b_guardar);

        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String info= et_info.getText().toString();

                    FileOutputStream fileOutputStream= openFileOutput("archivoinfo.txt", Context.MODE_PRIVATE);
                    fileOutputStream.write(info.getBytes());
                    fileOutputStream.close();

                    et_info.setText("");

                }catch (Exception e){
                    Toast.makeText(Archivo.this, "Error al guardar archivo", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}