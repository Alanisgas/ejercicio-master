package ar.com.lemondata.ejercicio.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicio.ServicioPersona;

@SuppressWarnings("rawtypes")
@FacesConverter(value = "personaConverter", forClass = Persona.class)
public class PersonaConverter implements Converter {

    private ServicioPersona getServicioPersona(FacesContext context) {
        WebApplicationContext ctx = FacesContextUtils.getWebApplicationContext(context);
        return ctx.getBean(ServicioPersona.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                Long id = Long.valueOf(value);
                return getServicioPersona(context).buscarPersonaXId(id);
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error en la interfaz
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversión Error",
                        "No es un ID válido");
                FacesContext.getCurrentInstance().addMessage(component.getClientId(), msg);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Persona) {
            Persona persona = (Persona) value;
            return persona.getId() != null ? persona.getId().toString() : "";
        } else {
            return "";
        }
    }
}