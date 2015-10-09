package fr.esiea.penguin.Service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.esiea.penguin.DAO.UserDAO;
import fr.esiea.penguin.Entity.UserEntity;

@Controller
@Service("UserService")
public class UserService {

	private static UserDAO userDao;

	public UserService() {
		userDao = new UserDAO();
	}

	public void persist(UserEntity entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.persist(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public void update(UserEntity entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.update(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public UserEntity findById(String id) {
		userDao.openCurrentSession();
		UserEntity user = userDao.findById(id);
		userDao.closeCurrentSession();
		return user;
	}
	
	public UserEntity toLogin(String login, String password) {
		userDao.openCurrentSession();
		UserEntity result = userDao.toLogin(login, password);
		userDao.closeCurrentSession();
		return result;
	}

	public void delete(String id) {
		userDao.openCurrentSessionwithTransaction();
		UserEntity user = userDao.findById(id);
		userDao.delete(user);
		userDao.closeCurrentSessionwithTransaction();
	}

	public List<UserEntity> findAll() {
		userDao.openCurrentSession();
		List<UserEntity> users = userDao.findAll();
		userDao.closeCurrentSession();
		return users;
	}

	public void deleteAll() {
		userDao.openCurrentSessionwithTransaction();
		userDao.deleteAll();
		userDao.closeCurrentSessionwithTransaction();
	}

	public UserDAO userDao() {
		return userDao;
	}
}