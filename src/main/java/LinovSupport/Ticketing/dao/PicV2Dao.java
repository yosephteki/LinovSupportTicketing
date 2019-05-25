/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

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
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<PicV2> list = super.entityManager
				.createQuery("FROM PicV2 WHERE idPic=:id")
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
	public boolean isBkExist(AccountV2 account,String email) {
		List<PicV2> list = super.entityManager
				.createQuery("FROM PicV2 WHERE account=:account AND email=:email")
				.setParameter("account", account)
				.setParameter("email", email)
				.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Transactional
	public void insertPicV2(PicV2 picV2) {
		super.entityManager.merge(picV2);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PicV2> findAll() {
		List<PicV2> list = super.entityManager
				.createQuery("FROM PicV2")
				.getResultList();
		return list;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PicV2> findByFilter(String email, String nama) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM PicV2 WHERE 1=1");
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
		List<PicV2> pics = query.getResultList();

		return pics;
	}

	@Transactional
	public void updatePic(PicV2 pic) {
		super.entityManager.merge(pic);
	}

	@Transactional
	public void deletePic(String id) {
		PicV2 pic = findById(id);
		super.entityManager.remove(pic);
	}
	

}
