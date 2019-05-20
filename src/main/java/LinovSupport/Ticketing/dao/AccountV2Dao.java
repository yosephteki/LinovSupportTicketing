/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.PicV2;

/**
 * @author Yosep Teki
 *
 */
@Repository("AccountV2Dao")
public class AccountV2Dao extends CommonDao{
	
	@Transactional
	public AccountV2 findById(String id) {
		AccountV2 account;
		try {
		 account  =  (AccountV2)super.entityManager
				.createQuery("FROM AccountV2 WHERE idAccount=:id")
				.setParameter("id", id)
				.getSingleResult();
//		AccountV2 acc = new AccountV2();
//		List<PicV2> pics = new ArrayList<PicV2>();
//			for(PicV2 picss : account.getPics()) {
//				picss.setAccount(acc);
//				pics.add(picss);
//			}
//		account.setPics(pics);
//		
		}catch(Exception e) {
		return account = new AccountV2();
	}
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
	
	@Transactional
	public boolean isIdExist(String id) {
		if (!findById(id).getIdAccount().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	public boolean isBkExsit(String nama) {
		if (!findByBk(nama).getIdAccount().isEmpty()) {
			return true;
		}else {
			return false;
		}
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
	
	@Transactional
	public List<AccountV2> findAll(){
		List<AccountV2> list = super.entityManager
				.createQuery("F")
	}
		
}
