package com.example.realdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class usuario extends AppCompatActivity {

    public EditText nomb,prov,cedu;
    public TextView correo;
    public RadioButton Rh,Rm;
    public Spinner lista;
    private String Radio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        cedu = (EditText) findViewById(R.id.cedu) ;
        nomb = (EditText) findViewById(R.id.nomb) ;

        correo = (TextView) findViewById(R.id.correo) ;

        prov = (EditText) findViewById(R.id.prov) ;
        Rh=(RadioButton) findViewById(R.id.Rh);
        Rm=(RadioButton) findViewById(R.id.Rm);

        //set correo
        correo.setText(getIntent().getStringExtra("correo"));
        //fin setCorreo
        lista =(Spinner) findViewById(R.id.lista);

        ArrayList<String> paises = new ArrayList<>();
        paises.add("ECUADOR");
        paises.add("PERU");
        paises.add("CHILE");
        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, paises));

    }
    public void Insertar(View view){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance ();
        DatabaseReference dbReference = mDatabase.getReference ().child("Personas").push();
        dbReference.child("Cedula").setValue(cedu.getText().toString());
        dbReference.child("Nombres").setValue(nomb.getText().toString());
        dbReference.child("Correo").setValue(correo.getText().toString());
        dbReference.child("Provincia").setValue(prov.getText().toString());
        if (Rh.isChecked()){
            dbReference.child("Genero").setValue("Hombre");
        }else{
            dbReference.child("Genero").setValue("Mujer");
        }
        dbReference.child("Pais").setValue(lista.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(),"USUARIO REGISTRADO EXITOSAMENTE",Toast.LENGTH_LONG).show();
    }
}