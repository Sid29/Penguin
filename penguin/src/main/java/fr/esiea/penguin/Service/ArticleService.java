package fr.esiea.penguin.Service;

import java.util.List;

import javax.validation.constraints.Past;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.esiea.penguin.DAO.ArticleDAO;
import fr.esiea.penguin.Entity.ArticleEntity;

@Service("ArticleService")
public class ArticleService {

	private static ArticleDAO articleDao;

	public ArticleService() {
		articleDao = new ArticleDAO();
	}

	public void persist(ArticleEntity entity) {
		articleDao.openCurrentSessionwithTransaction();
		articleDao.persist(entity);
		articleDao.closeCurrentSessionwithTransaction();
	}

	public void update(ArticleEntity entity) {
		articleDao.openCurrentSessionwithTransaction();
		articleDao.update(entity);
		articleDao.closeCurrentSessionwithTransaction();
	}

	public ArticleEntity findById(String id) {
		articleDao.openCurrentSession();
		ArticleEntity article = articleDao.findById(id);
		articleDao.closeCurrentSession();
		return article;
	}

	public void delete(String id) {
		articleDao.openCurrentSessionwithTransaction();
		ArticleEntity article = articleDao.findById(id);
		articleDao.delete(article);
		articleDao.closeCurrentSessionwithTransaction();
	}

	public List<ArticleEntity> findAll() {
		articleDao.openCurrentSession();
		List<ArticleEntity> articles = articleDao.findAll();
		articleDao.closeCurrentSession();
		return articles;
	}

	public void deleteAll() {
		articleDao.openCurrentSessionwithTransaction();
		articleDao.deleteAll();
		articleDao.closeCurrentSessionwithTransaction();
	}
	
	public ArticleDAO articleDao() {
		return articleDao;
	}

	public ArticleEntity getLastArticle() {
		articleDao.openCurrentSession();
		ArticleEntity article = articleDao.getLastArticle();
		articleDao.closeCurrentSession();
		return article;
	}
}