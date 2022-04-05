package com.example.realdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.actionvar, menu);
        return true;
    }

    //funciones dentro dek menu

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.vent){
            Intent ventas = new Intent(this,vender.class);
            startActivity(ventas);
        }else if(id==R.id.comp){
            Intent Compras = new Intent(this,compras.class);
            startActivity(Compras);
        }else if(id==R.id.Lp){
            Intent Lproductos = new Intent(this,lista_productos.class);
            startActivity(Lproductos);
        }else if(id==R.id.CerrS){
            Intent MainActivity = new Intent(this,MainActivity.class);
            startActivity(MainActivity);
        }
        return super.onOptionsItemSelected(item);

    }
}