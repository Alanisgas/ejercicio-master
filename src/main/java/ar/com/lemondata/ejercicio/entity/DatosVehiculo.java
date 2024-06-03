package ar.com.lemondata.ejercicio.entity;

public interface DatosVehiculo {
    Long getId();

    String getPatente();

    default String getFormatoCombo() {

        return getPatente();
    }

}
