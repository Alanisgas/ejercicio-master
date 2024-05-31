package ar.com.lemondata.ejercicio.servicio;


import java.util.List;

import ar.com.lemondata.ejercicio.entity.Vehiculo;

public interface ServicioVehiculo {
    Vehiculo guardarVehiculo(Vehiculo vehiculo);
    List<Vehiculo> buscarVehiculoXPatente(String patente);
    Vehiculo buscarVehiculoXId(Long id);
    void eliminarVehiculo(Long id);
}