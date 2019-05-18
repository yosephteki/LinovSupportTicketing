/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.AccountV2;

/**
 * @author Yosep Teki
 *
 */
@Repository("AccountDao2")
public class AccountV2Dao extends CommonDao{
	
	@SuppressWarnings("unchecked")
	@Transactional
	public AccountV2 findById(String id) {
		List<AccountV2> list = super.entityManager
				.createQuery("FROM Account WHERE idAccount=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size()>0) {
			return (AccountV2)list.get(0);
		}
		else {
			return new AccountV2();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public AccountV2 findByBk(String nama) {
		List<AccountV2> list = super.entityManager
				.createQuery("FROM Account WHERE nama=:nama")
				.setParameter("nama",nama)
				.getResultList();
		if (list.size() > 0) {
			return (AccountV2)list.get(0);
		}else {
			return new AccountV2();
		}
	}
	
	@Transactional
	public void insertAccount(AccountV2 accountV2) {
		super.entityManager.merge(accountV2);
	}
	
		
}
