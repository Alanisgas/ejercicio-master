package ar.com.lemondata.ejercicio.servicioImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ar.com.lemondata.ejercicio.entity.DatosVehiculo;

import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.repository.VehiculoRepository;
import ar.com.lemondata.ejercicio.servicio.ServicioVehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementacion del Servicio Vehiculo
 */
@SessionScope
@Service
public class ServicioVehiculoImpl implements ServicioVehiculo {
    private static final Logger logger = LoggerFactory.getLogger(ServicioVehiculoImpl.class);

    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> buscarVehiculoXPatente(String patente) {
        logger.info("Buscando vehículos en la base de datos con patente que contiene: {}", patente);
        String queryString = "SELECT v FROM VEHICULOS v WHERE v.patente LIKE :patente";
        List<Vehiculo> vehiculos = entityManager.createQuery(queryString, Vehiculo.class)
                                                .setParameter("patente", "%" + patente + "%")
                                                .getResultList();
        logger.info("Número de vehículos encontrados: {}", vehiculos.size());
        return vehiculos;
    }

    public Vehiculo buscarVehiculoXId(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    public List<DatosVehiculo> obtenerDatosComboVehiculo() {
        return vehiculoRepository.findAllProjectedBy();
    }

    @Transactional
    public void eliminarVehiculo(Long vehiculoId) {
        vehiculoRepository.deleteById(vehiculoId);
    }
     public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }
    public List<DatosVehiculo> obtenerDatosComboPersona() {
		return vehiculoRepository.findAllProjectedBy();
	}
}
