package com.example.apptoderos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.example.apptoderos.adaptadores.AdaptadorFragment;
import com.example.apptoderos.vistas.Tab1Fragment;
import com.example.apptoderos.vistas.Tab2Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);


        int[] tituloTab= new int[]{R.string.tab1, R.string.tab2};

        viewPager.setAdapter(new AdaptadorFragment(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            this, arrayFragment(),tituloTab));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mas);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_persona);
    }

    private ArrayList<Fragment> arrayFragment() {

        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        fragmentArrayList.add(new Tab1Fragment());
        fragmentArrayList.add(new Tab2Fragment());

        return fragmentArrayList;
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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"start", Toast.LENGTH_LONG).show();
        Log.i("info", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"resume", Toast.LENGTH_LONG).show();
        Log.i("info", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"restart", Toast.LENGTH_LONG).show();
        Log.i("info", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"pause", Toast.LENGTH_LONG).show();
        Log.i("info", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"stop", Toast.LENGTH_LONG).show();
        Log.i("info", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"destroy", Toast.LENGTH_LONG).show();
        Log.i("info", "onDestroy");
    }
}