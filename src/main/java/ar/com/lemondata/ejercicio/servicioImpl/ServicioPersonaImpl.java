package ar.com.lemondata.ejercicio.servicioImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import ar.com.lemondata.ejercicio.entity.DatosPersona;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.repository.PersonaRepository;
import ar.com.lemondata.ejercicio.repository.VehiculoRepository;
import ar.com.lemondata.ejercicio.servicio.ServicioPersona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Fernando
 * Implementacion del Servicio Persona
 */
@SessionScope
@Service
public class ServicioPersonaImpl implements ServicioPersona {
	private static final Logger logger = LoggerFactory.getLogger(ServicioPersonaImpl.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
 @PersistenceContext
    private EntityManager entityManager;

	public Persona guardarPersona(Persona persona) {
		return personaRepository.saveAndFlush(persona);
	}

	public List<Persona> buscarPersonaXNombre(String nombre) {
		logger.info("Buscando personas en la base de datos con nombre que contiene: {}", nombre);
		String queryString = "SELECT p FROM PERSONAS p WHERE p.nombre LIKE :nombre";
		List<Persona> personas = entityManager.createQuery(queryString, Persona.class)
											   .setParameter("nombre", "%" + nombre + "%")
											   .getResultList();
		return personas;
	}
	public Persona buscarPersonaXId(Long id) {
		return personaRepository.findById(id).get();
	}
	
	public List<DatosPersona> obtenerDatosComboPersona() {
		return personaRepository.findAllProjectedBy();
	}

	@Transactional
    public void eliminarPersona(Long personaId) {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        vehiculoRepository.deleteByPropietario(persona);
        personaRepository.delete(persona);
    }

	 public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

}
