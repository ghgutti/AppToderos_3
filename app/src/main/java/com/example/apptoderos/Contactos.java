package com.example.apptoderos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class Contactos extends AppCompatActivity {

    private static final int RESULTADO_GALERIA = 1;
    private static final int RESULTADO_FOTO = 2;
    private String nombre="", telefono="", email="", descripcion="";
    private SwipeRefreshLayout srl_contactos;
    private ListView lv_contactos;
    private String[] contactos;
    private ImageView iv_foto;
    private FloatingActionButton fab_acciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            nombre=bundle.getString("nombre");
            telefono=bundle.getString("telefono");
            email=bundle.getString("email");
            descripcion=bundle.getString("descripcion");
        }

        srl_contactos=findViewById(R.id.srl_contactos);
        lv_contactos=findViewById(R.id.lv_contactos);
        iv_foto=findViewById(R.id.iv_foto);
        fab_acciones=findViewById(R.id.fab_acciones);

        contactos= getResources().getStringArray(R.array.listacontactos);
        lv_contactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactos));
        lv_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PopupMenu popupMenu=new PopupMenu(Contactos.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.ver:

                                Toast.makeText(Contactos.this, "Ver", Toast.LENGTH_LONG).show();

                                Intent intent= new Intent(Contactos.this, Detalle.class);
                                intent.putExtra("nombre", contactos[i]);
                                intent.putExtra("telefono", telefono);
                                intent.putExtra("email", email);
                                intent.putExtra("descripcion", descripcion);
                                startActivity(intent);

                                break;
                            case R.id.eliminar:
                                Toast.makeText(Contactos.this, "Eliminar", Toast.LENGTH_LONG).show();
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        srl_contactos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescarLista();
            }
        });

        registerForContextMenu(iv_foto);


    }

    //otros metodos
    private void refrescarLista() {
        contactos= getResources().getStringArray(R.array.listacontactos);
        lv_contactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactos));
        srl_contactos.setRefreshing(false);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.ver:
                Toast.makeText(this, "Ver", Toast.LENGTH_LONG).show();
                return true;
            case R.id.quitar:
                Toast.makeText(this, "Quitar", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonFlotante(View view) {

        CharSequence[] opciones={"Ver pagina", "Ver mapa", "Llamar", "Correo", "Mensaje", "Subir foto", "Tomar foto"};
        AlertDialog.Builder menuAcciones=new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        menuAcciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int opcion) {

                switch (opcion){
                    case 0:
                        Intent intent1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.unab.edu.co/"));
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:7.116560, -73.105490"));
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3195285117"));
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(Intent.ACTION_SENDTO);
                        intent4.setData(Uri.parse("mailto:"));
                        intent4.putExtra(Intent.EXTRA_EMAIL, "julio.suarez@o365.unab.edu.co");
                        intent4.putExtra(Intent.EXTRA_SUBJECT, "saludo");
                        intent4.putExtra(Intent.EXTRA_TEXT, "Soy un mensaje Sndroid");
                        if(intent4.resolveActivity(getPackageManager())!=null){
                            startActivity(intent4);
                        }
                        break;
                    case 4:
                        Intent intent5=new Intent(Intent.ACTION_SEND);
                        intent5.setType("text/plain");
                        intent5.putExtra(Intent.EXTRA_TEXT, "Soy un mensaje Android");
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(intent6, "Seleccionar foto"), RESULTADO_GALERIA);
                        break;
                    case 6:
                        Intent intent7=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri uriFoto=Uri.fromFile(new File(
                                Environment.getExternalStorageDirectory() + File.separator + "img_"+(System.currentTimeMillis()/1000)+".jpg")
                        );
                        intent7.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
                            startActivityForResult(intent7, RESULTADO_FOTO);
                        if(intent7.resolveActivity(getPackageManager())!=null){
                            startActivityForResult(intent7, RESULTADO_FOTO);
                        }
                        break;
                }
            }
        });
        menuAcciones.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if(requestCode==RESULTADO_GALERIA && resultCode==RESULT_OK){

                String uriData= data.getDataString();

                if(uriData!=null){
                    iv_foto.setImageURI(Uri.parse(uriData));

                }else {
                    iv_foto.setImageURI(null);
                }

            } else if(requestCode==RESULTADO_FOTO && resultCode==RESULT_OK){

                if(data!=null && data.getExtras()!=null){

                    Bitmap imagenBitMap=(Bitmap) data.getExtras().get("data");

                    iv_foto.setImageBitmap(imagenBitMap);
                }
            }

        }catch (Exception e){
            Log.e("Error de captura", "Error de seleccion de archivo", e);
        }
    }


}