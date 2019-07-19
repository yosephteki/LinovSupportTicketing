/**
 * 
 */
package LinovSupport.Ticketing.dao;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;

/**
 * @author Yosep Teki
 *
 */
@Repository("AccountV2Dao")
public class AccountV2Dao extends CommonDao{
	
	@Transactional
	public AccountV2 findById(String id) {
		AccountV2 account = super.entityManager.find(AccountV2.class,id);
		
//		try {
//		 account  =  (AccountV2)super.entityManager
//				.createQuery("FROM AccountV2 WHERE idAccount=:id")
//				.setParameter("id", id)
//				.getSingleResult();
//		}catch(Exception e) {
//		return account = new AccountV2();
//	}
		return account;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public AccountV2 findByBk(String nama) {
		List<AccountV2> list = super.entityManager
				.createQuery("FROM AccountV2 WHERE nama=:nama")
				.setParameter("nama",nama)
				.getResultList();
		if (list.size() > 0) {
			return (AccountV2)list.get(0);
		}else {
			return new AccountV2();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<AccountV2> list = super.entityManager
				.createQuery("FROM AccountV2 WHERE idAccount=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isBkExist(String nama) {
		List<AccountV2> list = super.entityManager
				.createQuery("FROM AccountV2 WHERE nama=:nama")
				.setParameter("nama", nama)
				.getResultList();
		if (list.size()>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<AccountV2> findByFilter(String telepon,String alamat,String nama){
		StringBuilder hql = new StringBuilder();
		hql.append("FROM AccountV2 WHERE 1=1");
		if (!telepon.trim().isEmpty()) {
			hql.append(" AND telepon =:telepon");
		}
		if (!alamat.trim().isEmpty()) {
			hql.append(" AND alamat =:alamat");
		}
		if (!nama.trim().isEmpty()) {
			hql.append(" AND nama =:nama");
		}
		
		Query query = (Query) super.entityManager
				.createQuery(hql.toString());
		
		if (!telepon.trim().isEmpty()) {
			query.setParameter("telepon",telepon);
		}
		if (!alamat.trim().isEmpty()) {
			query.setParameter("alamat", alamat);
		}
		if (!nama.trim().isEmpty()) {
			query.setParameter("nama", nama);
		}
		List<AccountV2> account = query.getResultList();
		
		return account;
	}
	
	@Transactional
	public void insertAccount(AccountV2 accountV2) {
		super.entityManager.merge(accountV2);
	}
	
	@Transactional
	public void updateAccount(AccountV2 account) {
		super.entityManager.merge(account);
	}
	
	@Transactional
	public void deleteAccount(String id) {
		AccountV2 account = findById(id);
		super.entityManager.remove(account);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<AccountV2> findAll(){
		List<AccountV2> list = super.entityManager
				.createQuery("FROM AccountV2")
				.getResultList();
		return list;
	}
	
	public Agen findAgen(AccountV2 account) {
		Agen agen =	(Agen) super.entityManager
				.createQuery("FROM Agen WHERE account=:acc")
				.setParameter("acc", account)
				.getSingleResult();
		return agen;
	}
		
}
