/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.Account;
import LinovSupport.Ticketing.model.Agen;
/**
 * @author Yosep Teki
 *
 */
@Repository("AgenDao")
public class AgenDao extends CommonDao{
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Agen findById(String id){
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE idAgen=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Agen();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public Agen findByBk(Account bk1, String bk2) {
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
	public boolean isBkExist(Account bk1, String bk2) {
		List<Agen> list = super.entityManager
				.createQuery("FROM Agen WHERE account=:idacc AND email=:email")
				.setParameter("idacc", bk1)
				.setParameter("email", bk2)
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
			hql.append(" AND email=:email");
		}
		if (!nama.trim().isEmpty()) {
			hql.append(" AND nama=:nama");
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
	
	@Transactional
	public void update(Agen agen) {
		super.entityManager.merge(agen);
	}
	@Transactional
	public void delete(String id) {
		Agen agen = findById(id);
		super.entityManager.remove(agen);
	}
	
	

}
