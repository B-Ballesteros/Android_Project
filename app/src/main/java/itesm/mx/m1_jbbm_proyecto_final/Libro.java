package itesm.mx.m1_jbbm_proyecto_final;

/**
 * Created by benji on 22/02/17.
 */

public class Libro {

    String isbn;
    String titulo;
    int cantidadExistencia;
    int idImagen;
    double precioUnitario;

    public Libro() { }

    public Libro(String titulo, String isbn, int cantidadExistencia,
                 int idImagen, double precioUnitario){
        this.titulo = titulo;
        this.isbn = isbn;
        this.cantidadExistencia = cantidadExistencia;
        this.precioUnitario = precioUnitario;
        this.idImagen = idImagen;
    }

    //region Getters & Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getCantidadExistencia() { return cantidadExistencia; }
    public void setCantidadExistencia( int cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public double getPrecioUnitario() { return  precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario  = precioUnitario; }

    public int getIdImagen() {return idImagen; }
    public void setIdImagen(int idImagen) { this.idImagen = idImagen; }
    //endregion
}
