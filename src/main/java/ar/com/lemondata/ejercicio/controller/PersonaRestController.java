package ar.com.lemondata.ejercicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.lemondata.ejercicio.entity.Persona;
import ar.com.lemondata.ejercicio.servicio.ServicioPersona;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
    @GetMapping("/buscarPorNombre")
    public List<Persona> getMethodName(@RequestParam String param) {
        return servicioPersona.buscarPersonaXNombre(param);
    }
    @GetMapping("/buscarTodas")
    public List<Persona> buscarTodasLasPersonas() {
        return servicioPersona.buscarTodasLasPersonas();
    }

        @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        Persona persona = servicioPersona.buscarPersonaXId(id);
        if (persona != null) {
            servicioPersona.eliminarPersona(id);
            return new ResponseEntity<>("Persona eliminada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Persona no encontrada", HttpStatus.NOT_FOUND);
        }
    }

        @PutMapping("/actualizar")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody Persona persona) {
        try {
            Persona personaActualizada = servicioPersona.actualizarPersona(persona);
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}