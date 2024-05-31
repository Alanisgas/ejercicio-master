package ar.com.lemondata.ejercicio.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicio.ServicioPersona;

@RestController
public class PersonaRestController {

    @Autowired
    private ServicioPersona servicioPersona;

    @PostMapping("/altaPersona")
    public ResponseEntity<String> altaPersona(@RequestBody Persona persona) {
        try {
            servicioPersona.guardarPersona(persona);
            return ResponseEntity.ok("Persona creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear la persona: " + e.getMessage());
        }
    }
}