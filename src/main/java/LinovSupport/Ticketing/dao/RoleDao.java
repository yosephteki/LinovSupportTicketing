/**
 * 
 */
package LinovSupport.Ticketing.dao;
import java.util.List;
import javax.persistence.Query;
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
	public Role findByBk(String kodeRole) {
		List<Role> list = super.entityManager
				.createQuery("FROM Role WHERE kodeRole=:kodeRole")
				.setParameter("kodeRole", kodeRole)
				.getResultList();
		if (list.size()>0) {
			return list.get(0);
		}else {
			return new Role();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Role>findByFilter(String nama) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Role WHERE 1=1");
		if (!nama.trim().isEmpty()) {
			hql.append("AND nama=:nama");
		}
		
		Query query = (Query) super.entityManager
				.createQuery(hql.toString());
		if (!nama.trim().isEmpty()) {
			query.setParameter("nama",nama);
		}
		List<Role>roles = query.getResultList();
		return roles;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isBkExist(String kodeRole) {
		List<Role> list = super.entityManager
				.createQuery("FROM Role WHERE kodeRole=:kodeRole")
				.setParameter("kodeRole", kodeRole)
				.getResultList();
	if (list.size()>0) {
		return true;
	} else {
		return false;
	}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<Role> list = super.entityManager
				.createQuery("FROM Role WHERE idRole=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	@Transactional
	public void insertRole(Role role) {
		super.entityManager.merge(role);
	}
	
	@Transactional
	public void updateRole(Role role) {
		super.entityManager.merge(role);
	}
	
	@Transactional
	public void deleteRole(String id) {
		Role role = findById(id);
		super.entityManager.remove(role);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Role> findAllRole() {
		List<Role> list = super.entityManager
				.createQuery("From Role")
				.getResultList();
		return list;
	}
}
