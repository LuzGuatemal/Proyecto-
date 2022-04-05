package com.example.realdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class compras extends AppCompatActivity {

    private TextView nPro,stock,pEntr,pSal,tot;
    private EditText cod,cant;
    private String ClaveHija;
    private Integer valor;
    private Integer PrecioE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        cod= findViewById(R.id.cod);
        nPro=findViewById(R.id.nPro);
        stock=findViewById(R.id.stock);
        pEntr=findViewById(R.id.pEntr);
        pSal=findViewById(R.id.pSal);
        cant=findViewById(R.id.cant);
        tot=findViewById(R.id.tot);
    }
    public  void Buscar(View view){
        Query query = FirebaseDatabase.getInstance().getReference("Productos").orderByChild("Codigo").equalTo(cod.getText().toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for( DataSnapshot ds : snapshot.getChildren()){
                    nPro.setText(ds.child("Nombre_Producto").getValue().toString());
                    stock.setText(ds.child("Stock").getValue().toString());
                    pEntr.setText(ds.child("Precio_Entrada").getValue().toString());
                    pSal.setText(ds.child("Precio_Salida").getValue().toString());
                    ClaveHija = ds.getKey().toString();
                    valor = Integer.parseInt(ds.child("Stock").getValue().toString());
                    PrecioE=Integer.parseInt(ds.child("Precio_Entrada").getValue().toString());
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void comprar (View view){
        FirebaseDatabase  mDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference dbReference = mDatabase.getReference ().child("Productos");

        int acumula = valor + Integer.parseInt(cant.getText().toString());
        Map<String,Object> pro = new HashMap<>();
        pro.put("Stock",String.valueOf(acumula));
        dbReference.child(ClaveHija).updateChildren(pro);
        Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_LONG).show();
    }
    public void calcular(View view){
        int total = PrecioE * Integer.parseInt(cant.getText().toString());
        tot.setText(""+total);

    }

}