package fr.esiea.penguin;

//import javax.ejb.EJB;

import org.h2.server.web.WebServlet;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

//import fr.esiea.penguin.Entity.UserEntity;

@Controller
@EnableAutoConfiguration
public class App 
{
	//@EJB
	//UserEntity currentUser;
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
	//	String toto = currentUser.getPseudo();
		return "Hello " + "toto" +"!";
	}
	
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}