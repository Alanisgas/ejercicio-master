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
import ar.com.lemondata.ejercicio.entity.Vehiculo;
import ar.com.lemondata.ejercicio.servicioImpl.ServicioVehiculoImpl;

/**
 * @author Fernando
 *
 */
@Named("consultaVehiculoBean")
@ViewScoped
public class ConsultaVehiculoBean extends GenericBean {

    @Value("${consultaVehiculo}")
    private String titulo;

    private Long idSeleccionado;
    private List<DatosVehiculo> datosCombo;
    private Vehiculo vehiculo;
    private String patenteConsulta;
    private List<Vehiculo> resultados;

    @Autowired
    private ServicioVehiculoImpl servicio;

    @PostConstruct
    private void init() {
        setDatosCombo(servicio.obtenerDatosComboVehiculo());
        setIdSeleccionado(null);
        setVehiculo(null);

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

    public String getPatenteConsulta() {
        return patenteConsulta;
    }

    public void setPatenteConsulta(String patenteConsulta) {
        this.patenteConsulta = patenteConsulta;
    }

    public List<Vehiculo> getResultados() {
        return resultados;
    }

    public void setResultados(List<Vehiculo> resultados) {
        this.resultados = resultados;
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
            try {
                Vehiculo vehiculo = servicio.buscarVehiculoXId(getIdSeleccionado());
                if (vehiculo != null) {
                    setVehiculo(vehiculo);
                    mostrarMensaje("Vehículo encontrado: " + vehiculo.getPatente());
                } else {
                    mostrarMensaje("No se encontró ningún vehículo con el ID proporcionado.");
                }
            } catch (Exception e) {
                mostrarMensajeError("Ocurrió un error al consultar el vehículo: " + e.getMessage());
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
