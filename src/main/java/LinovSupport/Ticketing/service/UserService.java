/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.UserDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.User;

/**
 * @author Yosep Teki
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User findById(String id) {
		return userDao.findById(id);
	}
	
	public User findByBk(String bk) {
		return userDao.findByBk(bk);
	}

	public void insertUser(User user) throws ErrorException{
		if (userDao.isBkExist(user.getUsername())) {
			throw new ErrorException("Username sudah digunakan");
		}
		if (user.getUsername().isEmpty()) {
			throw new ErrorException("Username tidak boleh kosong");
		}
		if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
			throw new ErrorException("Password harus lebih dari 6 karakter dan maksimal 16 karakter");
		}
		if (user.getPassword().isEmpty()) {
			throw new ErrorException("Password tidak boleh kosong");
		}
		userDao.insertUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}
	
	public User findAll() {
		return userDao.findAll();
	}
	
	public List<User> findByFilter(String username) {
		return userDao.findByFilter(username);
	}
	
	
	

}
