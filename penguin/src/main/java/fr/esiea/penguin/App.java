package fr.esiea.penguin;

import java.util.Date;
import java.util.List;

import org.h2.server.web.WebServlet;
import org.json.JSONArray;
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

	/**********************************************************
	 * 
	 * DECLARATION VARIABLES GLOBALES
	 *
	 **********************************************************/
	private UserEntity userConnected = null;
	private boolean connected = false;


	/**********************************************************
	 * 
	 * PART ABOUT USERS
	 *
	 **********************************************************/
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
	boolean toLogin(@RequestParam("login") String login, 
			@RequestParam("password") String password) 
	{
		try
		{
			userConnected = userService.toLogin(login, password);
			connected = true;
			System.out.println("L'utilisateur n°" + userConnected.getId() + " est connecté.");
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace().toString());
		}
		return connected;
	}
	
	@RequestMapping(value="/userConnected", method=RequestMethod.GET)
	@ResponseBody
	JSONArray returnUserConnected(){
		JSONArray userConnectedJSON = new JSONArray();
		try
		{
			userConnectedJSON.put(0, userConnected);
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace().toString());
		}
		return userConnectedJSON;
	}



	/**********************************************************
	 * 
	 * PART ABOUT ARTICLES
	 *
	 **********************************************************/
	@RequestMapping(value="/addArticle", method=RequestMethod.GET)
	@ResponseBody
	boolean addArticle(
			@RequestParam("title") String title, 
			@RequestParam("body") String body
			) {
		boolean result = false;
		try {
			Date dateCreation = new Date();
			Date dateLastUpdate = new Date();
			ArticleEntity newArticle = new ArticleEntity(title, userConnected, dateCreation, dateLastUpdate, body);
			articleService.persist(newArticle);
			result = true;
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}


	@RequestMapping(value="/addComment", method=RequestMethod.GET)
	@ResponseBody
	boolean addComment(@RequestParam("articleId") int articleId,
			@RequestParam("bodyComment") String bodyComment) {
		boolean result = false;
		try {
			System.out.println("body comment = " +bodyComment);
			Date dateCreation = new Date();
			Date dateLastUpdate = new Date();
			CommentEntity newComment = new CommentEntity(userConnected, dateCreation, dateLastUpdate, bodyComment, articleService.findById(articleId));
			commentService.persist(newComment);
			result = true;
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}


	@RequestMapping("/articles")
	@ResponseBody
	List<ArticleEntity> allArticles(){
		List<ArticleEntity> allArticles = articleService.findAll();
		return allArticles;

	}

	@RequestMapping("/comments")
	@ResponseBody
	List<CommentEntity> allComments(){
		List<CommentEntity> allComments = commentService.findAll();
		return allComments;

	}

	@RequestMapping(value="/lastArticleWithComments",  method=RequestMethod.GET)
	@ResponseBody
	JSONArray sendLastArticlesWithComments(){
		JSONArray result = new JSONArray();
		ArticleEntity lastArticle = articleService.getLastArticle();
		if(lastArticle != null){
			result.put(0, lastArticle);
		}
		else
		{
			result.put(0, "No article yet.");
		}

		List<CommentEntity> commentsLastArticle = commentService.getCommentsByArticleId(lastArticle.getId());
		if(commentsLastArticle != null)
		{
			result.put(1, commentsLastArticle);
		}
		else
		{
			result.put(1, "No comments yet.");
		}			
		System.out.println("result =" +result);
		return result;		
	}



	/**********************************************************
	 * 
	 * MAIN AND CONSOLE H2
	 *
	 **********************************************************/
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