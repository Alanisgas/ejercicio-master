package ar.com.lemondata.ejercicio.entity;


/**
 * @author Fernando
 * Objeto del tipo DTO para el uso de Combos de Personas.
 */
public interface DatosPersona {
	
	Long getId();
	
	String getNombre();
	
	String getApellido();
	
	default String getFormatoCombo() {
		return getNombre() + " " + getApellido();
	}

}
