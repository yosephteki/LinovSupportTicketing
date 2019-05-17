/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;

import LinovSupport.Ticketing.model.Account;

/**
 * @author Yosep Teki
 *
 */
@Repository("AccountDao")
public class AccountDao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public Account findById(String id) {
		List<Account> list = super.entityManager
				.createQuery("FROM Account WHERE idAccount=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return (Account) list.get(0);
		} else {
			return new Account();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Account findByBk(String bk) {
		List<Account> list = super.entityManager
				.createQuery("FROM Account WHERE nama=:nama ")
				.setParameter("nama", bk)
				.getResultList();
		if (list.size() > 0) {
			return (Account) list.get(0);
		} else {
			return new Account();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<Account> list = super.entityManager
				.createQuery("FROM Account WHERE idAccount=:id")
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
	public boolean isBkExist(String bk) {
		List<Account> list = super.entityManager
				.createQuery("FROM Account WHERE nama=:nama")
				.setParameter("nama", bk)
				.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void insertAccount(Account account) {
		super.entityManager.merge(account);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Account> findByFilter(String telepon,String alamat,String nama){
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Account WHERE 1=1");
		if (!telepon.trim().isEmpty()) {
			hql.append(" AND telepon =:telepon");
		}
		if (!alamat.trim().isEmpty()) {
			hql.append(" AND alamat =:alamat");
		}
		if (!nama.trim().isEmpty()) {
			hql.append(" AND nama =:nama");
		}
		
		Query query = (Query) super.entityManager.createQuery(hql.toString());
		
		if (!telepon.trim().isEmpty()) {
			query.setParameter("telepon",telepon);
		}
		if (!alamat.trim().isEmpty()) {
			query.setParameter("alamat", alamat);
		}
		if (!nama.trim().isEmpty()) {
			query.setParameter("nama", nama);
		}
		List<Account> accounts = query.getResultList();
		
		return accounts;
	}

	@Transactional
	public void updateAccount(Account account) {
		super.entityManager.merge(account);
	}

	@Transactional
	public void deleteAccount(String id) {
		Account account = findById(id);
		super.entityManager.remove(account);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Account> findAll(){
		List<Account> list = super.entityManager
				.createQuery("FROM Account")
				.getResultList();
		return list;
	}

}


