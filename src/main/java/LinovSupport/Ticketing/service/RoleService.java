/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.RoleDao;
import LinovSupport.Ticketing.model.Role;


/**
 * @author Yosep Teki
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public void create(Role role) {
		roleDao.create(role);
	}
}
