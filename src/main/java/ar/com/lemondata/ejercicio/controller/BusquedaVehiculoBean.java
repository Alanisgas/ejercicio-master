package ar.com.lemondata.ejercicio.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;

@Named("busquedaVehiculoBean")
@ViewScoped
public class BusquedaVehiculoBean extends GenericBean implements Serializable {

    private String patente;
    private List<Vehiculo> resultados;

    @Autowired
    private ServicioVehiculoImpl servicio;

    @PostConstruct
    private void init() {
        patente = "";
        resultados = null;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public List<Vehiculo> getResultados() {
        return resultados;
    }

    public void setResultados(List<Vehiculo> resultados) {
        this.resultados = resultados;
    }

    public void consultarVehiculo() {
        resultados = servicio.buscarVehiculoXPatente(patente);
        if (resultados.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Patente no encontrada",
                            "No se encontró ninguna patente con el nombre proporcionado."));
        }

    }
}