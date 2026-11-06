package org.example.proyectohibernate.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "libro", schema = "catalogos")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "anio_publicacion", nullable = false)
    private int anio_publicacion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    public Libro(String titulo, String autor, int anio, BigDecimal precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio_publicacion = anio;
        this.precio = precio;
    }

    public Libro() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
