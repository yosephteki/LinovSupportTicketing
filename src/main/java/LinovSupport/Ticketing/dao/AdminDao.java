package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.Admin;

@Repository("AdminDao")
public class AdminDao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public Admin findById(String id) {
		List<Admin> list =super.entityManager
			.createQuery("FROM Admin where idAdmin=:id")
			.setParameter("id", id)
			.getResultList();
	if(list.size() >0) {
		return (Admin) list.get(0);
	} else {
		return new Admin();
	}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public Admin findByBk(String email) {
		List<Admin> list =super.entityManager
			.createQuery("FROM Admin where email=:email")
			.setParameter("email", email)
			.getResultList();
	if(list.size() >0) {
		return (Admin) list.get(0);
	} else {
		return new Admin();
	}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<Admin> list = super.entityManager
				.createQuery("FROM Admin WHERE idAdmin=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isBkExist(String email) {
		List<Admin> list = super.entityManager
				.createQuery("FROM Admin WHERE email=:email")
				.setParameter("email", email)
				.getResultList();
		if (list.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public void insertAdmin(Admin admin) {
		super.entityManager.merge(admin);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Admin> findByFilter(String nama, String email) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Admin where 1=1");
		
		if( !nama.trim().isEmpty()) {
			hql.append("AND nama=:nama");
		}
		if (!email.trim().isEmpty()) {
			hql.append("AND email=:email");
		}
		Query query = (Query)super.entityManager.createQuery(hql.toString());
			if (!nama.trim().isEmpty()) {
				query.setParameter("nama",nama);
			}
			if (!nama.trim().isEmpty()) {
				query.setParameter("email", email);
			}
		List<Admin> admins = query.getResultList();
		return admins;
	}
	@Transactional
	public void updateAdmin(Admin admin) {
		super.entityManager.merge(admin);
	}
	@Transactional
	public void deleteAdmin(String id) {
		Admin admin = findById(id);
		super.entityManager.remove(admin);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Admin> findAll() {
		List<Admin> list = super.entityManager
				.createQuery("FROM Admin")
				.getResultList();
		return list;
	}
}
