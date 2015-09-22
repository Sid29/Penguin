package fr.esiea.penguin.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import fr.esiea.penguin.Entity.UserEntity;

public class UserDAO {

	private Session currentSession;	
	private Transaction currentTransaction;
	
	private String allUsers = "from UserEntity";
	private String countUsers = "select count(*) from UserEntity user";

	public UserDAO() {
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

	public void persist(UserEntity entity) {
		getCurrentSession().save(entity);
	}

	public void update(UserEntity entity) {
		getCurrentSession().update(entity);
	}

	public UserEntity findById(String id) {
		UserEntity user = (UserEntity) getCurrentSession().get(UserEntity.class, id);
		return user; 
	}

	public void delete(UserEntity entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> findAll() {
		List<UserEntity> users = (List<UserEntity>) getCurrentSession().createQuery(allUsers).list();
		return users;
	}
	
	public int countAll() {
		Query nbreUsers =   getCurrentSession().createQuery(countUsers);
		Integer nbre = nbreUsers.getFirstResult();		
		return nbre;
	}

	public void deleteAll() {
		List<UserEntity> entityList = findAll();
		for (UserEntity entity : entityList) {
			delete(entity);
		}
	}
}