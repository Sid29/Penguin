package fr.esiea.penguin.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import fr.esiea.penguin.DAO.CommentDAO;
import fr.esiea.penguin.Entity.CommentEntity;

@Service("CommentService")
public class CommentService {

	private static CommentDAO commentDao;

	public CommentService() {
		commentDao = new CommentDAO();
	}

	public void persist(CommentEntity entity) {
		commentDao.openCurrentSessionwithTransaction();
		commentDao.persist(entity);
		commentDao.closeCurrentSessionwithTransaction();
	}

	public void update(CommentEntity entity) {
		commentDao.openCurrentSessionwithTransaction();
		commentDao.update(entity);
		commentDao.closeCurrentSessionwithTransaction();
	}

	public CommentEntity findById(String id) {
		commentDao.openCurrentSession();
		CommentEntity article = commentDao.findById(id);
		commentDao.closeCurrentSession();
		return article;
	}

	public void delete(String id) {
		commentDao.openCurrentSessionwithTransaction();
		CommentEntity comment = commentDao.findById(id);
		commentDao.delete(comment);
		commentDao.closeCurrentSessionwithTransaction();
	}

	public List<CommentEntity> findAll() {
		commentDao.openCurrentSession();
		List<CommentEntity> comments = commentDao.findAll();
		commentDao.closeCurrentSession();
		return comments;
	}
	
	public List<CommentEntity> getCommentsByArticleId(int idArticle){
		commentDao.openCurrentSession();
		List<CommentEntity> comments = commentDao.getCommentsByArticleId(idArticle);
		commentDao.closeCurrentSession();
		return comments;
	}

	public void deleteAll() {
		commentDao.openCurrentSessionwithTransaction();
		commentDao.deleteAll();
		commentDao.closeCurrentSessionwithTransaction();
	}

	public CommentDAO commentDao() {
		return commentDao;
	}
}