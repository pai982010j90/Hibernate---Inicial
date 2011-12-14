/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate1;

import java.util.Date;

public class Libro {

 

    private int id;
    private String titulo;
    private Date fechaPublicacion;
    private float precio;

    public Libro() {
    }

    public Libro(String titulo, Date fechaPublicacion, float precio) {
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;

    }
}
