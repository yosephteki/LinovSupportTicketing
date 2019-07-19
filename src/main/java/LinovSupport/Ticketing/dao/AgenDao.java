/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;
/**
 * @author Yosep Teki
 *
 */
@Repository("AgenDao")
public class AgenDao extends CommonDao{
	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	public Agen findById(String id){
//		List<Agen> list = super.entityManager
//				.createQuery("FROM Agen WHERE idAgen=:id")
//				.setParameter("id", id)
//				.getResultList();
//		if (list.size() > 0) {
//			return list.get(0);
//		} else {
//			return new Agen();
//		}
//	}
	
	public Agen findById(String id) {
		Agen agen = super.entityManager.find(Agen.class,id);
		if (agen ==  null) {
				throw new EntityNotFoundException("Agen tidak ditemukan"+id);
		}
		return agen;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Agen findByAccount(AccountV2 accountV2){
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE account=:accountV2")
				.setParameter("accountV2", accountV2)
				.getResultList();
		if (list.size() >0) {
			return list.get(0);
		}else {
			return new Agen();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Agen findByBk(AccountV2 bk1, String bk2) {
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE account=:idacc AND email=:email")
				.setParameter("idacc", bk1)
				.setParameter("email", bk2)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Agen();
		}
	}

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE idAgen=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isBkExist(AccountV2 account, String email) {
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE account=:idacc AND email=:email")
				.setParameter("idacc", account)
				.setParameter("email", email)
				.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Transactional
	public void insertAgen(Agen agen) {
		super.entityManager.merge(agen);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Agen> findByFilter(String email, String nama) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Agen WHERE 1=1");
		if (!email.trim().isEmpty()) {
			hql.append(" AND email =:email");
		}
		if (!nama.trim().isEmpty()) {
			hql.append(" AND nama =:nama");
		}
		Query query = super.entityManager.createQuery(hql.toString());

		if (!email.trim().isEmpty()) {
			query.setParameter("email", email);
		}
		if (!nama.trim().isEmpty()) {
			query.setParameter("nama", nama);
		}
		List<Agen> agens = query.getResultList();
		return agens;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Agen> findAll(){
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen")
				.getResultList();
		return list;
	}
	
	@Transactional
	public void updateAgen(Agen agen) {
		super.entityManager.merge(agen);
	}
	@Transactional
	public void delete(String id) {
		Agen agen = findById(id);
		super.entityManager.remove(agen);
	}
	
	

}
