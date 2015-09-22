package fr.esiea.penguin;

import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import fr.esiea.penguin.Entity.UserEntity;
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
		List<UserEntity> users = userService.findAll();
		return "Hello " + users.get(0).getFirstname() +"!";
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