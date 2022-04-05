package com.example.realdatabase.adaptadores;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realdatabase.R;
import com.example.realdatabase.modelos.productos;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private  int resourse;
    private  ArrayList<productos> listamensa;



    public adapter(ArrayList<productos> listamensa, int resourse) {
        this.resourse = resourse;
        this.listamensa = listamensa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( resourse, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productos productos = listamensa.get(position);
        holder.Rcod.setText(productos.getCodigo());
        holder.Rn_pro.setText(productos.getNombre_Producto());
        holder.R_stock.setText(productos.getStock());
        holder.Rp_ent.setText(productos.getPrecio_Entrada());
        holder.Rp_sal.setText(productos.getPrecio_Salida());


    }

    @Override
    public int getItemCount() {
        return listamensa.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView Rcod;
        private TextView Rn_pro;
        private  TextView R_stock;
        private  TextView Rp_ent;
        private TextView Rp_sal;
        public View view;
        public ViewHolder (View view){
            super (view);
            this.view = view;
            this.Rcod = (TextView)  view.findViewById(R.id.Rcod);
            this.Rn_pro = (TextView)  view.findViewById(R.id.Rn_pro);
            this.R_stock = (TextView)  view.findViewById(R.id.R_stock);
            this.Rp_ent = (TextView)  view.findViewById(R.id.Rp_ent);
            this.Rp_sal = (TextView)  view.findViewById(R.id.Rp_sal);

        }

    }
}