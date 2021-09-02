package de.stl.saar.internetentw1;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartMensaApplication extends SpringBootServletInitializer{

	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf");
		return servletRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartMensaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
		return application.sources(new Class[] {SmartMensaApplication.class, AppInitializer.class});
	}
}
