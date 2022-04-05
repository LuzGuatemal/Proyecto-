package com.example.realdatabase.modelos;

public class productos {
    private String Codigo;
    private String Nombre_Producto;
    private String Stock;
    private String Precio_Entrada;
    private String Precio_Salida;




    public productos() {
    }

    public productos(String Codigo,String Nombre_Producto,String Stock,String Precio_Entrada, String Precio_Salida ){
        this.Codigo=Codigo ;
        this.Nombre_Producto=Nombre_Producto ;
        this.Stock=Stock ;
        this.Precio_Entrada=Precio_Entrada ;
        this.Precio_Salida=Precio_Salida ;

    }


    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getPrecio_Entrada() {
        return Precio_Entrada;
    }

    public void setPrecio_Entrada(String precio_Entrada) {
        Precio_Entrada = precio_Entrada;
    }

    public String getPrecio_Salida() {
        return Precio_Salida;
    }

    public void setPrecio_Salida(String precio_Salida) {
        Precio_Salida = precio_Salida;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        Nombre_Producto = nombre_Producto;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

}
