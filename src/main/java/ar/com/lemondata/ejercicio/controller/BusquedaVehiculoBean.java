package ar.com.lemondata.ejercicio.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("busquedaVehiculoBean")
@ViewScoped
public class BusquedaVehiculoBean extends GenericBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BusquedaVehiculoBean.class);

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
       
    }
}