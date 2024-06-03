package ar.com.lemondata.ejercicio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.lemondata.ejercicio.entity.DatosVehiculo;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;

@Named("eliminarVehiculoBean")
@ViewScoped
public class EliminarVehiculo extends GenericBean {
    private String titulo = "Eliminar Vehiculo";
    private Long idSeleccionado;
    private List<DatosVehiculo> datosCombo;

    @Autowired
    private ServicioVehiculoImpl servicio;

    @PostConstruct
    private void init() {
        setDatosCombo(servicio.obtenerDatosComboVehiculo());
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

    public List<DatosVehiculo> getDatosCombo() {
        return datosCombo;
    }

    public void setDatosCombo(List<DatosVehiculo> datosCombo) {
        this.datosCombo = datosCombo;
    }

    public void eliminarVehiculo() {
        if (idSeleccionado != null) {
            try {
                servicio.eliminarVehiculo(idSeleccionado);
                setDatosCombo(servicio.obtenerDatosComboVehiculo());
                setIdSeleccionado(null);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehículo eliminado con éxito", null));
            } catch (Exception e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el vehículo", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un vehículo", null));
        }
    }

}
