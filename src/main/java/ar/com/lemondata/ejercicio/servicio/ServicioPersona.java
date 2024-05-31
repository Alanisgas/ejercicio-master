package ar.com.lemondata.ejercicio.servicio;

import java.util.List;

import ar.com.lemondata.ejercicio.entity.DatosPersona;
import ar.com.lemondata.ejercicio.entity.Persona;

/**
 * @author Fernando
 * Servicios de Persona
 */
public interface ServicioPersona {

	public Persona guardarPersona(Persona persona);

	public List<Persona> buscarPersonaXNombre(String nombre);

	public Persona buscarPersonaXId(Long id);

	public List<DatosPersona> obtenerDatosComboPersona();

	public void eliminarPersona(Long id);
	

}
