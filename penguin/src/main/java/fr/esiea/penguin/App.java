package fr.esiea.penguin;

import java.lang.reflect.Array;
import java.util.List;

import org.h2.server.web.WebServlet;
import org.json.JSONObject;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.esiea.penguin.Entity.ArticleEntity;
import fr.esiea.penguin.Entity.CommentEntity;
import fr.esiea.penguin.Entity.UserEntity;
import fr.esiea.penguin.Service.ArticleService;
import fr.esiea.penguin.Service.CommentService;
import fr.esiea.penguin.Service.UserService;

@Controller
@EnableAutoConfiguration
public class App 
{

	ArticleService articleService = new ArticleService();
	CommentService commentService = new CommentService();
	UserService userService  = new UserService();

	@RequestMapping("/articles")
	@ResponseBody
	List<ArticleEntity> sendArticles(){
		List<ArticleEntity> allArticles = articleService.findAll();
		return allArticles;

	}

	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	@ResponseBody
	boolean addUser(
			@RequestParam("pseudoInscription") String pseudo, 
			@RequestParam("lastnameInscription") String lastname, 
			@RequestParam("firstnameInscription") String firstname, 
			@RequestParam("emailInscription") String email, 
			@RequestParam("passwordInscription") String password
			) {
		boolean result = false;
		try {
			UserEntity newUser = new UserEntity(pseudo, lastname, firstname, email, password);
			userService.persist(newUser);
			result = true;
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	@ResponseBody
	String toLogin(
			@RequestParam("login") String login, 
			@RequestParam("password") String password
			) 
	{
		JSONObject userConnectedJSON = new JSONObject();
		UserEntity userConnected = null;
		try{
			userConnected = userService.toLogin(login, password);
			userConnectedJSON.put("firstname", userConnected.getFirstname());
			System.out.println("L'utilisateur n°" + userConnected.getId() + " est connecté.");
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		return userConnected.getFirstname();
	}

	@RequestMapping("/lastArticleWithComments")
	@ResponseBody
	Object[] sendLastArticlesWithComments(){
		Object[] result = new Array[2];
		ArticleEntity lastArticle = articleService.getLastArticle();
		List<CommentEntity> commentsLastArticle = commentService.getCommentsByArticleId(lastArticle.getId());
		result[0] = lastArticle;
		result[1] = commentsLastArticle;
		return result;		
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