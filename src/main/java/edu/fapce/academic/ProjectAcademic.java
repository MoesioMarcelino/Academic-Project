package edu.fapce.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableJpaRepositories("edu.fapce.cursosoring.crud.repository")
@SpringBootApplication
public class ProjectAcademic extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProjectAcademic.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectAcademic.class, args);
	}
	
}
