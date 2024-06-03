package ar.com.lemondata.ejercicio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "VEHICULOS")
@Table(name = "VEHICULOS")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 5836967339724076670L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "patente", nullable = false, length = 10)
    private String patente;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "tipo_uso", nullable = false, length = 50)
    private String tipoUso;

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Persona propietario;

    public Vehiculo() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

}
