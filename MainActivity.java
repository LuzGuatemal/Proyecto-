package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText correo,contraseña;
    private Spinner lista;
    private ProgressDialog mProgressBar;
    FirebaseAuth miFire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = (EditText) findViewById(R.id.correo);
        contraseña = (EditText) findViewById(R.id.contraseña);


        miFire= FirebaseAuth.getInstance();
        mProgressBar = new ProgressDialog(this);

        lista =(Spinner) findViewById(R.id.lista);

        ArrayList<String> Ativi = new ArrayList<>();
        Ativi.add("Usuario");
        Ativi.add("Producto");
        Ativi.add("Inventario");
        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Ativi));



    }

    public void boton(View view){
        Intent pantalla2 = new Intent(this,registrarUS.class);
        startActivity(pantalla2);

    }
    public void Inicia(View view){
        mProgressBar.setTitle("Login");
        mProgressBar.setMessage("Iniciando sesión, espere un momento..");
        mProgressBar.setCanceledOnTouchOutside(false);
        mProgressBar.show();
        miFire.signInWithEmailAndPassword
                (correo.getText().toString()
                        ,contraseña.getText().toString()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                            if(lista.getSelectedItem().toString()=="Usuario"){
                                Intent Persona = new Intent(MainActivity.this,usuario.class);
                                Persona.putExtra("correo",correo.getText().toString());
                                startActivity(Persona);
                                mProgressBar.dismiss();
                            }else if(lista.getSelectedItem().toString()=="Producto"){
                                Intent Producto = new Intent(MainActivity.this,producto.class);
                                startActivity(Producto);
                                mProgressBar.dismiss();
                            }else if(lista.getSelectedItem().toString()=="Inventario") {
                                Intent Inventario = new Intent(MainActivity.this,inventario.class);
                               startActivity(Inventario);
                                mProgressBar.dismiss();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "USUARIO O CONTRASEÑA INVALIDOS", Toast.LENGTH_SHORT).show();
                            mProgressBar.dismiss();
                        }
                    }
                });



    }
}