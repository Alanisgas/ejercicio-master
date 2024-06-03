package ar.com.lemondata.ejercicio.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;

@Named("busquedaPersonaBean")
@ViewScoped
public class BusquedaPersonaBean extends GenericBean implements Serializable {

    private String nombre;
    private List<Persona> resultados;

    @Autowired
    private ServicioPersonaImpl servicio;

    @PostConstruct
    private void init() {
        nombre = "";
        resultados = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getResultados() {
        return resultados;
    }

    public void setResultados(List<Persona> resultados) {
        this.resultados = resultados;
    }

    public void consultarPersona() {
        resultados = servicio.buscarPersonaXNombre(nombre);
        if (resultados.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Persona no encontrada",
                            "No se encontró ninguna persona con el nombre proporcionado."));
        }
    }

}