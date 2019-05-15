/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.User;

/**
 * @author Yosep Teki
 *
 */
@Repository("UserDao")
public class UserDao extends CommonDao{

	@SuppressWarnings("unchecked")
	@Transactional
	public User findByID(String id) {
		List<User> list = super.entityManager
				.createQuery("FROM User WHERE idUser =:id")
				.setParameter("id",id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		}else {
			return new User();
		}
	}
	
	@Transactional
	public boolean isIDExist(String id) {
		if (findByID(id).getIdUser().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	@Transactional
	public void create(User user) {
		super.entityManager.merge(user);
	}
}

