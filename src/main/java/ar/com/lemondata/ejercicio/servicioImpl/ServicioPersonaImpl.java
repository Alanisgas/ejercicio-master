package ar.com.lemondata.ejercicio.servicioImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import ar.com.lemondata.ejercicio.entity.DatosPersona;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.repository.PersonaRepository;
import ar.com.lemondata.ejercicio.repository.VehiculoRepository;
import ar.com.lemondata.ejercicio.servicio.ServicioPersona;

/**
 * @author Fernando
 *         Implementacion del Servicio Persona
 */
@SessionScope
@Service
public class ServicioPersonaImpl implements ServicioPersona {

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;

	public Persona guardarPersona(Persona persona) {
		return personaRepository.saveAndFlush(persona);
	}

	public List<Persona> buscarPersonaXNombre(String nombre) {
		return personaRepository.findByNombreContainingIgnoreCase(nombre);
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

	@Override
    public List<Persona> buscarTodasLasPersonas() {
        return personaRepository.findAll();
    }

	@Transactional
    @Override
    public Persona actualizarPersona(Persona persona) {
        if (!personaRepository.existsById(persona.getId())) {
            throw new RuntimeException("Persona no encontrada");
        }
        return personaRepository.save(persona);
    }
}
