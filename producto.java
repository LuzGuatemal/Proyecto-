package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class producto extends AppCompatActivity {

    private EditText cod, nPro,stock,pEntr,pSal;
    private String ClaveHija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        cod=findViewById(R.id.cod);
        nPro=findViewById(R.id.nPro);
        stock=findViewById(R.id.stock);
        pEntr=findViewById(R.id.pEntr);
        pSal=findViewById(R.id.pSal);


    }
    public void Insertar(View view){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference dbReference = mDatabase.getReference ().child("Productos").push();
        dbReference.child("Codigo").setValue(cod.getText().toString());
        dbReference.child("Nombre_Producto").setValue(nPro.getText().toString());
        dbReference.child("Stock").setValue(stock.getText().toString());
        dbReference.child("Precio_Entrada").setValue(pEntr.getText().toString());
        dbReference.child("Precio_Salida").setValue(pSal.getText().toString());
        Toast.makeText(getApplicationContext(),"DATOS GUARDADOS",Toast.LENGTH_LONG).show();

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
                    ClaveHija=ds.getKey().toString();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Borrar(View view){
        FirebaseDatabase  mDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference dbReference = mDatabase.getReference ().child("Productos");
        dbReference.child(ClaveHija).removeValue();
        Toast.makeText(getApplicationContext(),"ELIMINADO CON EXITO",Toast.LENGTH_LONG).show();
        cod.setText("");
        nPro.setText("");
        stock.setText("");
        pEntr.setText("");
        pSal.setText("");

    }

    public void Actualizar(View view){
        FirebaseDatabase  mDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference dbReference = mDatabase.getReference ().child("Productos");
        Map<String,Object> pro = new HashMap<>();
        pro.put("Nombre_Producto",nPro.getText().toString());
        pro.put("Stock",stock.getText().toString());
        pro.put("Precio_Entrada",pEntr.getText().toString());
        pro.put("Precio_Salida",pSal.getText().toString());
        dbReference.child(ClaveHija).updateChildren(pro);
        Toast.makeText(getApplicationContext(),"DATOS GUARDADOS CON EXITO",Toast.LENGTH_LONG).show();


    }
    public void Regresar(View view) {
        Intent i = new Intent(producto.this, MainActivity.class);

        startActivity(i);
    }
}