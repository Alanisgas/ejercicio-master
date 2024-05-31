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
@Named("consultaPersonaBean")
@ViewScoped
public class ConsultaPersonaBean extends GenericBean {

	@Value("${consultaPersona}")
	private String titulo;

	private Long idSeleccionado;
	private List<DatosPersona> datosCombo;
	private Persona persona;
	private String nombreConsulta;
    private List<Persona> resultados;

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

    public String getNombreConsulta() {
        return nombreConsulta;
    }

    public void setNombreConsulta(String nombreConsulta) {
        this.nombreConsulta = nombreConsulta;
    }

    public List<Persona> getResultados() {
        return resultados;
    }

    public void setResultados(List<Persona> resultados) {
        this.resultados = resultados;
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
        try {
            Persona persona = servicio.buscarPersonaXId(getIdSeleccionado());
            if (persona != null) {
                setPersona(persona);
                mostrarMensaje("Persona encontrada: " + persona.getNombre());
            } else {
                mostrarMensaje("No se encontró ninguna persona con el ID proporcionado.");
            }
        } catch (Exception e) {
            mostrarMensajeError("Ocurrió un error al consultar la persona: " + e.getMessage());
            e.printStackTrace(); 
        }
    } else {
        mostrarMensaje("Debe seleccionar un ID válido para la consulta.");
    }
}

public void mostrarMensaje(String mensaje) {
    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje);
    context.addMessage(null, facesMessage);
}

private void mostrarMensajeError(String mensaje) {
    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje);
    context.addMessage(null, facesMessage);
}
}

