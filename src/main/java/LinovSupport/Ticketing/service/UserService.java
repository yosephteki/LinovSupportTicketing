/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.UserDao;
import LinovSupport.Ticketing.model.User;

/**
 * @author Yosep Teki
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void create(User user) {
		userDao.create(user);
	}
}



