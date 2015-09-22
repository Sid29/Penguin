package fr.esiea.penguin;

import org.h2.server.web.WebServlet;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import fr.esiea.penguin.Service.UserService;

@Controller
@EnableAutoConfiguration
public class App 
{
	UserService userService;
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		userService = new UserService();
		int user = userService.countAll();
		

		return "Hello " + user +"!";
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