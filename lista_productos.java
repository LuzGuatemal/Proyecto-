package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.realdatabase.adaptadores.adapter;
import com.example.realdatabase.modelos.productos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class lista_productos extends AppCompatActivity {

    private ArrayList<productos> listaProductos = new  ArrayList<productos>();
    ArrayAdapter<productos> arrayAdapterProducto;

    ListView lprodu;
    private DatabaseReference mData;

    private adapter madapter;
    private RecyclerView mRecyclerView;
    private ArrayList<productos> pruductolis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        lprodu = findViewById(R.id.lprodu);
        mData = FirebaseDatabase.getInstance().getReference();
        mRecyclerView =(RecyclerView) findViewById(R.id.Lisyprodu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getmensajesformFire();

    }
    private void getmensajesformFire(){
        mData.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.exists()){
                    for( DataSnapshot ds : snapshot.getChildren()){
                        String Codigo = ds.child("Codigo").getValue().toString();
                        String Nombre = ds.child("Nombre_Producto").getValue().toString();
                        String Stock = ds.child("Stock").getValue().toString();
                        String Pe = ds.child("Precio_Entrada").getValue().toString();
                        String Ps = ds.child("Precio_Salida").getValue().toString();
                        pruductolis.add(new productos(Codigo,Nombre,Stock,Pe,Ps));
                    }
                    madapter= new adapter(pruductolis,R.layout.mensaje_view);
                    mRecyclerView.setAdapter(madapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}