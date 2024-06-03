package ar.com.lemondata.ejercicio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.lemondata.ejercicio.entity.DatosPersona;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;

@Named("eliminarPersonaBean")
@ViewScoped
public class EliminarPersonaBean extends GenericBean {

    private String titulo = "Eliminar Persona";

    private Long idSeleccionado;
    private List<DatosPersona> datosCombo;

    @Autowired
    private ServicioPersonaImpl servicio;

    @PostConstruct
    private void init() {
        setDatosCombo(servicio.obtenerDatosComboPersona());
        setIdSeleccionado(null);
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

    public void eliminarPersona() {
        if (idSeleccionado != null) {
            try {
                servicio.eliminarPersona(idSeleccionado);
                setDatosCombo(servicio.obtenerDatosComboPersona());
                setIdSeleccionado(null);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Persona eliminada con éxito", null));
            } catch (Exception e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la persona", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione una persona", null));
        }
    }

}