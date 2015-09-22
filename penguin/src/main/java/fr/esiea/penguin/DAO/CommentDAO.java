package fr.esiea.penguin.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import fr.esiea.penguin.Entity.CommentEntity;

public class CommentDAO {

	private Session currentSession;	
	private Transaction currentTransaction;
	
	private String allUsers = "from CommentEntity";

	public CommentDAO() {
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

	public void persist(CommentEntity entity) {
		getCurrentSession().save(entity);
	}

	public void update(CommentEntity entity) {
		getCurrentSession().update(entity);
	}

	public CommentEntity findById(String id) {
		CommentEntity comment = (CommentEntity) getCurrentSession().get(CommentEntity.class, id);
		return comment; 
	}

	public void delete(CommentEntity entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CommentEntity> findAll() {
		List<CommentEntity> comments = (List<CommentEntity>) getCurrentSession().createQuery(allUsers).list();
		return comments;
	}


	public void deleteAll() {
		List<CommentEntity> entityList = findAll();
		for (CommentEntity entity : entityList) {
			delete(entity);
		}
	}
}