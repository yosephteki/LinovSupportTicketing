/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.Role;

/**
 * @author Yosep Teki
 *
 */
@Repository("RoleDao")
public class RoleDao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public Role findById(String id) {
		List<Role> list = super.entityManager
				.createQuery("FROM Role WHERE idRole=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Role();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public Role findByBk(String bk) {
		List<Role> list = super.entityManager
				.createQuery("FROM Role WHERE nama=:nama")
				.setParameter("nama", bk)
				.getResultList();
		if (list.size()>0) {
			return list.get(0);
		}else {
			return new Role();
		}
	}
	@Transactional
	public void create(Role role) {
		super.entityManager.merge(role);
	}
}
