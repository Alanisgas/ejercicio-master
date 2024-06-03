package ar.com.lemondata.ejercicio.repository;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ar.com.lemondata.ejercicio.entity.DatosVehiculo;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
     @PersistenceContext
    public Vehiculo save(Vehiculo vehiculo);
    public List<DatosVehiculo> findAllProjectedBy();
    void deleteByPropietario(Persona propietario);

    List<Vehiculo> findByPatenteContainingIgnoreCase(String patente);


}
