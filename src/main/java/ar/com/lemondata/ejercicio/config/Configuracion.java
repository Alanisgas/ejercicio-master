package ar.com.lemondata.ejercicio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Fernando
 * Configurador de la página de inicio.
 */
@Configuration
public class Configuracion implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/home.xhtml");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
