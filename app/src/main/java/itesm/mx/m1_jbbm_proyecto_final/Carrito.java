package itesm.mx.m1_jbbm_proyecto_final;

import java.io.Serializable;

/**
 * Created by benji on 22/02/17.
 */

public class Carrito implements Serializable{
    String isbn;
    String titulo;
    int cantidadCompra;
    double precioTotal;
    int idImagen;

    public Carrito() { }

    public Carrito(String isbn, String titulo, int cantidadCompra,
                   double precioTotal, int idImagen){
        this.isbn = isbn;
        this.titulo  = titulo;
        this.cantidadCompra = cantidadCompra;
        this.precioTotal = precioTotal;
        this.idImagen = idImagen;
    }

    //region Getters & Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getCantidadCompra() { return cantidadCompra; }
    public void setCantidadCompra( int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public double getPrecioTotal() { return  precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal  = precioTotal; }

    public int getIdImagen() {return idImagen; }
    public void setIdImagen(int idImagen) { this.idImagen = idImagen; }
    //endregion
}
