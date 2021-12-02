package com.example.apptoderos.adaptadores;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.Explode;

import com.example.apptoderos.Detalle;
import com.example.apptoderos.R;
import com.example.apptoderos.modelos.PojoContactos;

import java.util.ArrayList;

public class AdaptadorInicioRV extends RecyclerView.Adapter<AdaptadorInicioRV.InicioRVViewHolder> {

    private Activity contexto;
    private ArrayList<PojoContactos> pojoContactosArrayList;

    public AdaptadorInicioRV(Activity contexto, ArrayList<PojoContactos> pojoContactosArrayList) {
        this.contexto = contexto;
        this.pojoContactosArrayList = pojoContactosArrayList;
    }

    @NonNull
    @Override
    public InicioRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_iniciorv, parent, false);

        InicioRVViewHolder inicioRVViewHolder= new InicioRVViewHolder(itemView);

        return inicioRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InicioRVViewHolder holder, int position) {

        final PojoContactos pojoContactos= pojoContactosArrayList.get(position);

        holder.iv_foto.setImageResource(pojoContactos.getFoto());

        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(contexto, "enviar nombre", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(contexto, Detalle.class);
                intent.putExtra("nombre", pojoContactos.getNombre());

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    contexto.getWindow().setExitTransition(explode);
                    contexto.startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(contexto, view, "animacion").toBundle());

                }else {
                    contexto.startActivity(intent);
                }

            }
        });

        holder.tv_nombre.setText(pojoContactos.getNombre());
        holder.tv_telefono.setText(pojoContactos.getTelefono());
    }

    @Override
    public int getItemCount() {
        return pojoContactosArrayList.size();
    }

    public class InicioRVViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_foto;
        private TextView tv_nombre, tv_telefono;

        public InicioRVViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_foto= itemView.findViewById(R.id.iv_foto);
            tv_nombre= itemView.findViewById(R.id.tv_nombre);
            tv_telefono= itemView.findViewById(R.id.tv_telefono);
        }
    }
}
