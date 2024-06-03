package ar.com.lemondata.ejercicio.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;

/**
 * @author Fernando
 *
 */
@Named("altaPersonaBean")
@ViewScoped
public class AltaPersonaBean extends GenericBean {

    @Value("${altaPersona}")
    private String titulo;

    private Persona persona;

    @Autowired
    private ServicioPersonaImpl servicio;

    @PostConstruct
    public void init() {
        setPersona(new Persona());

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    // seteo la fecha
    public void guardarPersona() {
        try {
            // Aseguro que la fecha de nacimiento esté en el formato correcto
            java.util.Date utilDate = persona.getFechaNacimiento();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            persona.setFechaNacimiento(sqlDate);

            // Guarda la persona y obtiene el resultado
            Persona resultado = servicio.guardarPersona(getPersona());

            // Muestra mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se creó la Persona: " + resultado.getNombre() + " " + resultado.getApellido() +
                                    " con el ID: " + resultado.getId() +
                                    " con Fecha de nacimiento " + resultado.getFechaNacimiento() +
                                    " DNI: " + resultado.getDni() +
                                    " Sexo: " + resultado.getSexo() +
                                    " Domicilio: " + resultado.getDomicilio(),
                            null));

            // Inicializa el estado
            init();
        } catch (Exception e) {
            // Muestra mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al guardar la persona", null));
        }
    }

}
