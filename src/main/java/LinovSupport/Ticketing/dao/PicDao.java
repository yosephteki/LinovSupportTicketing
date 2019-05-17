/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.Pic;

/**
 * @author Yosep Teki
 *
 */
@Repository("PicDao")
public class PicDao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public Pic findById(String id) {
		List<Pic> list = super.entityManager
				.createQuery("FROM Pic WHERE idPic=:id").setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Pic();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Pic findByBk(String bk1, String bk2) {
		List<Pic> list = super.entityManager
				.createQuery("FROM Pic WHERE idAccount=:idacc AND email=:email")
				.setParameter("idacc", bk1)
				.setParameter("email", bk2)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Pic();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIdExist(String id) {
		List<Pic> list = super.entityManager
				.createQuery("FROM Pic WHERE idPic=:id")
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
	public boolean isBkExist(String bk1, String bk2) {
		List<Pic> list = super.entityManager
				.createQuery("FROM Pic WHERE idAccount=:idacc AND email=:email")
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
	public void insertPic(Pic pic) {
		super.entityManager.merge(pic);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pic> findByFilter(String email, String nama) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Agent WHERE 1=1");
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
		List<Pic> pics = query.getResultList();

		return pics;
	}

	@Transactional
	public void updatePic(Pic pic) {
		super.entityManager.merge(pic);
	}

	@Transactional
	public void deletePic(String id) {
		Pic pic = findById(id);
		super.entityManager.remove(pic);
	}
}
