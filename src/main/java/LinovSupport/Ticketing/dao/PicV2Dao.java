/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.PicV2;

/**
 * @author Yosep Teki
 *
 */
@Repository("PicV2Dao")
public class PicV2Dao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public PicV2 findById(String id) {
		List<PicV2> list = super.entityManager
				.createQuery("FROM PicV2 WHERE idPic=:id")
				.setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return (PicV2) list.get(0);
		} else {
			return new PicV2();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public PicV2 findByBk(AccountV2 account, String email) {
		List<PicV2> list = super.entityManager
				.createQuery("FROM PicV2 WHERE account=:account AND email=:email")
				.setParameter("account", account)
				.setParameter("email", email)
				.getResultList();
		if (list.size() > 0) {
			return (PicV2) list.get(0);
		} else {
			return new PicV2();
		}
	}
	
	public boolean isIdExist(String id) {
		if (!findById(id).getIdPic().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isBkExist(AccountV2 account,String email) {
		if (!findByBk(account, email).getIdPic().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	@Transactional
	public void insertPicV2(PicV2 picV2) {
		super.entityManager.merge(picV2);
	}
	
	
	
	

}
