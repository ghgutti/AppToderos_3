package com.example.apptoderos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apptoderos.adaptadores.AdaptadorInicioRV;
import com.example.apptoderos.modelos.PojoContactos;

import java.util.ArrayList;

public class InicioRV extends AppCompatActivity {

    private RecyclerView rv_inicio;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdaptadorInicioRV adaptadorInicioRV;
    private ArrayList<PojoContactos> pojoContactosArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciorv);

        rv_inicio= findViewById(R.id.rv_inicio);

        linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager= new GridLayoutManager(this, 2);

        rv_inicio.setLayoutManager(gridLayoutManager);

        pojoContactosArrayList= new ArrayList<PojoContactos>();
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Violeta Suarez", "3114274503","violeta@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_men, "Marlon Duarte", "3104551325","marlon@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Carmen Ladino", "3004597841","carmen@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_men, "Julio Suarez", "3195280810","julio@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Naime Romero", "3124040832","naime@gmail.com"));

        adaptadorInicioRV= new AdaptadorInicioRV(this, pojoContactosArrayList);

        rv_inicio.setAdapter(adaptadorInicioRV);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch (item.getItemId()){
            case R.id.registro:
                Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.saludo:
                Toast.makeText(this, "Saludo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.notificacion:
                Toast.makeText(this, "Notificacion", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mapa:
                intent=new Intent(this, Mapa.class);
                startActivity(intent);
                return true;
            case R.id.archivo:
                intent=new Intent(this, Archivo.class);
                startActivity(intent);
                return true;
            case R.id.configuracion:
                intent=new Intent(this, Preferencias.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}