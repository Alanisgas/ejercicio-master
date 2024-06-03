package ar.com.lemondata.ejercicio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.lemondata.ejercicio.entity.DatosVehiculo;
import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioPersonaImpl;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;

@Named("modificaVehiculoBean")
@ViewScoped
public class ModificaVehiculoBean extends GenericBean {
    @Value("${modificaVhiculos}")
    private String titulo;

    private Long idSeleccionado;
    private List<DatosVehiculo> datosCombo;
    private List<Persona> personas;

    private Vehiculo vehiculo;

    @Autowired
    private ServicioVehiculoImpl servicioVehiculo;
    @Autowired
    private ServicioPersonaImpl servicioPersona;

    @PostConstruct
    private void init() {
        setDatosCombo(servicioVehiculo.obtenerDatosComboVehiculo());
        setIdSeleccionado(null);
        setVehiculo(null);
        personas = servicioPersona.obtenerTodasLasPersonas();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
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

    public List<DatosVehiculo> getDatosCombo() {
        return datosCombo;
    }

    public void setDatosCombo(List<DatosVehiculo> datosCombo) {
        this.datosCombo = datosCombo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void consultarVehiculo() {
        if (idSeleccionado != null) {
            vehiculo = servicioVehiculo.buscarVehiculoXId(idSeleccionado);
        }
    }

    public void modificarVehiculo() {
        if (vehiculo == null) {
            return; // No hay vehículo para modificar
        }

        servicioVehiculo.guardarVehiculo(vehiculo);

        // Actualizo para impactar la modificacion
        init();

        mostrarMensajeExito("Vehículo modificado con éxito");
    }

    private void mostrarMensajeExito(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
    }
}
