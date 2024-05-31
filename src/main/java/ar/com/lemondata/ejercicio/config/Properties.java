package ar.com.lemondata.ejercicio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Fernando
 * Manejador de Properties de la App.
 */
@Configuration
@PropertySource("classpath:custom.properties")
public class Properties {

}
