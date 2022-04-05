package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrarUS extends AppCompatActivity {

    private EditText corr,contra;
    private ProgressDialog mProgressBar;
    FirebaseAuth miFire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_u_s);

        corr=(EditText) findViewById(R.id.corr);
        contra=(EditText) findViewById(R.id.contra);
        miFire=FirebaseAuth.getInstance();
        mProgressBar = new ProgressDialog(this);


    }

    public void registrar (View view){
        miFire
                .createUserWithEmailAndPassword
                        (corr.getText().toString(),contra.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent Log = new Intent(registrarUS.this,MainActivity.class);
                    startActivity(Log);
                    Toast.makeText(getApplicationContext(), "Exito", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}