package ar.com.lemondata.ejercicio.controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;

import java.util.List;

/**
 * @author 
 *
 */
@Named("altaVehiculoBean")
@ViewScoped
public class AltaVehiculoBean extends GenericBean {

    @Value("${altaVehiculo}")
    private String titulo;

    private Vehiculo vehiculo;
    private List<Persona> personas;
    private Long propietarioId;

    @Autowired
    private ServicioVehiculoImpl servicioVehiculo;
    
    @Autowired
    private ServicioPersonaImpl servicioPersona;

    @PostConstruct
    public void init() {
        vehiculo = new Vehiculo();
        personas = servicioPersona.obtenerTodasLasPersonas();
       
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
  

    public Long getPropietarioId() {
        return propietarioId;
    }


    public void guardarVehiculo() {
        try {
            vehiculo.getColor();
            if (vehiculo.getPropietario() != null) {
                servicioVehiculo.guardarVehiculo(vehiculo);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehículo guardado con éxito", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un propietario", null));
            }
        } catch (Exception e) {
          
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el vehículo", null));
            
        }
    }
    
}
