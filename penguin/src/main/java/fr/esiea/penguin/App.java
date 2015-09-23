package fr.esiea.penguin;

import java.lang.reflect.Array;
import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import fr.esiea.penguin.Entity.ArticleEntity;
import fr.esiea.penguin.Entity.CommentEntity;
import fr.esiea.penguin.Service.ArticleService;
import fr.esiea.penguin.Service.CommentService;

@Controller
@EnableAutoConfiguration
public class App 
{
	
	ArticleService articleService = new ArticleService();
	CommentService commentService = new CommentService();
	
	@RequestMapping("/articles")
	@ResponseBody
	List<ArticleEntity> sendArticles(){
		articleService = new ArticleService();
		List<ArticleEntity> allArticles = articleService.findAll();
		return allArticles;
		
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