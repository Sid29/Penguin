package fr.esiea.penguin.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import fr.esiea.penguin.Entity.ArticleEntity;

public class ArticleDAO {

	private Session currentSession;	
	private Transaction currentTransaction;
	
	private String allArticles = "from ArticleEntity";
	private String getLastArticle = "from ArticleEntity where dateCreation in (select Max(dateCreation) from ArticleEntity)";

	public ArticleDAO() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(ArticleEntity entity) {
		getCurrentSession().save(entity);
	}

	public void update(ArticleEntity entity) {
		getCurrentSession().update(entity);
	}

	public ArticleEntity findById(String id) {
		ArticleEntity article = (ArticleEntity) getCurrentSession().get(ArticleEntity.class, id);
		return article; 
	}

	public void delete(ArticleEntity entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAll() {
		List<ArticleEntity> articles = (List<ArticleEntity>) getCurrentSession().createQuery(allArticles).list();
		return articles;
	}
	
	public ArticleEntity getLastArticle() {
		ArticleEntity lastArticle = (ArticleEntity) getCurrentSession().createQuery(getLastArticle);
		return lastArticle;
	}


	public void deleteAll() {
		List<ArticleEntity> entityList = findAll();
		for (ArticleEntity entity : entityList) {
			delete(entity);
		}
	}
}