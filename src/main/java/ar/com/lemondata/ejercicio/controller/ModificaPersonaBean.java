package ar.com.lemondata.ejercicio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.lemondata.ejercicio.entity.DatosPersona;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;

/**
 * @author Fernando
 *
 */
@Named("modificaPersonaBean")
@ViewScoped
public class ModificaPersonaBean extends GenericBean {
	@Value("${modificaPersona}")
	private String titulo;

	private Long idSeleccionado;
	private List<DatosPersona> datosCombo;
	private Persona persona;

	@Autowired
	private ServicioPersonaImpl servicio;

	@PostConstruct
	private void init() {
		setDatosCombo(servicio.obtenerDatosComboPersona());
		setIdSeleccionado(null);
		setPersona(null);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdSeleccionado() {
		return idSeleccionado;
	}

	public void setIdSeleccionado(Long idSeleccionado) {
		this.idSeleccionado = idSeleccionado;
	}

	public List<DatosPersona> getDatosCombo() {
		return datosCombo;
	}

	public void setDatosCombo(List<DatosPersona> datosCombo) {
		this.datosCombo = datosCombo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void consultarPersona() {
		if (idSeleccionado != null) {
			setPersona(servicio.buscarPersonaXId(getIdSeleccionado()));
		}
	}

	public void modificarPersona() {
		if (persona == null) {
			return;
		}
		if (persona.getFechaNacimiento() != null && persona.getFechaNacimiento() instanceof java.util.Date) {
			java.util.Date utilDate = (java.util.Date) persona.getFechaNacimiento();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			persona.setFechaNacimiento(sqlDate);
		}

		// Guardo la persona
		servicio.guardarPersona(persona);

		// Refresco la interfaz de usuario
		init();

		mostrarMensajeExito("Persona modificada con éxito");
	}

	private void mostrarMensajeExito(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
	}

}
