package com.example.apptoderos.vistas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptoderos.R;
import com.example.apptoderos.adaptadores.AdaptadorInicioRV;
import com.example.apptoderos.modelos.PojoContactos;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment {

    View view;

    private RecyclerView rv_inicio;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdaptadorInicioRV adaptadorInicioRV;
    private ArrayList<PojoContactos> pojoContactosArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tab1, container, false);

        rv_inicio= view.findViewById(R.id.rv_inicio);

        linearLayoutManager= new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager= new GridLayoutManager(getActivity(), 2);

        rv_inicio.setLayoutManager(gridLayoutManager);

        pojoContactosArrayList= new ArrayList<PojoContactos>();
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Violeta Suarez", "3114274503","violeta@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_men, "Marlon Duarte", "3104551325","marlon@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Carmen Ladino", "3004597841","carmen@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_men, "Julio Suarez", "3195280810","julio@gmail.com"));
        pojoContactosArrayList.add(new PojoContactos(R.drawable.ic_woman, "Naime Romero", "3124040832","naime@gmail.com"));

        adaptadorInicioRV= new AdaptadorInicioRV(getActivity(), pojoContactosArrayList);

        rv_inicio.setAdapter(adaptadorInicioRV);



        return view;
    }
}
