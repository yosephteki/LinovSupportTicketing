/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.enumeration.Status;
import LinovSupport.Ticketing.model.DetailTiket;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.Tiket;

/**
 * @author Yosep Teki
 *
 */
@Repository("TiketDao")
public class TiketDao extends CommonDao {

	@Transactional
	public Tiket findById(String id) {
		Tiket tiket;
		try {
			tiket = (Tiket) super.entityManager
					.createQuery("FROM Tiket WHERE idTiket=:id")
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			return tiket = new Tiket();
		}
		return tiket;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Tiket findByBk(String judul, PicV2 pic) {
		List<Tiket> list = super.entityManager
				.createQuery("FROM Tiket WHERE judulTiket=:judul AND idPic=:pic")
				.setParameter("judul", judul)
				.setParameter("pic", pic)
				.getResultList();
		if (list.size() > 0) {
			return (Tiket)list.get(0);
		} else {
			return new Tiket();
		}
	}
	
	@Transactional
	public boolean isIdExist(String id) {
		if (!findById(id).getIdTiket().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	@Transactional
	public boolean isBkExist(String judul,PicV2 pic) {
		if (!findByBk(judul, pic).getIdTiket().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tiket> findAll() {
		List<Tiket> list = super.entityManager
				.createQuery("FROM Tiket")
				.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tiket> findByFilter(PicV2 pic,Level level) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Tiket WHERE 1=1");
//		if (!judul.trim().isEmpty()) {
//			hql.append(" AND judulTiket =:judul");
//		}
		if (pic != null) {
			hql.append(" AND idPic =:pic");
		}
		if (!level.toString().trim().isEmpty()) {
			hql.append(" AND level =:level");
		}

		Query query = super.entityManager.createQuery(hql.toString());

//		if (!judul.trim().isEmpty()) {
//			query.setParameter("judul", judul);
//		}
		if (pic != null) {
			query.setParameter("pic", pic);
		}
		if (!level.toString().trim().isEmpty()) {
			query.setParameter("level", level);
		}

		List<Tiket> list = query.getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tiket> findByLevel(Level level) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Tiket WHERE 1=1");

		if (!level.toString().trim().isEmpty()) {
			hql.append(" AND level =:level");
		}
		Query query = super.entityManager.createQuery(hql.toString());

		if (!level.toString().trim().isEmpty()) {
			query.setParameter("level", level);
		}

		List<Tiket> list = query.getResultList();

		return list;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tiket> findByStatus(Status status){
		List<Tiket> tiket = super.entityManager
				.createQuery("FROM Tiket WHERE status =: status")
				.setParameter("status", status)
				.getResultList();
		return tiket;
	}

	@Transactional
	public void insertTiket(Tiket tiket) {
		super.entityManager.merge(tiket);
	}
	
	@Transactional
	public void updateTiket(Tiket tiket) {
		super.entityManager.merge(tiket);
	}
	
	@Transactional
	public void deleteTiket(String id) {
		Tiket tiket = findById(id);
		super.entityManager.remove(tiket);
	}
	
//	------------------------------------- DETAIL --------------------------------------
	@Transactional
	public DetailTiket findDetailById(String id) {
		DetailTiket detailTiket;
		try {
			detailTiket = (DetailTiket) super.entityManager
					.createQuery("FROM DetailTiket WHERE idDetailTiket=:id")
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			return detailTiket = new DetailTiket();
		}
		return detailTiket;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailTiket findDetailByBk(String idTiket,Date waktu) {
		List<DetailTiket> list = super.entityManager
				.createQuery("FROM DetailTiket WHERE idTiket =:id AND waktu=:waktu")
				.setParameter("id", idTiket)
				.setParameter("waktu", waktu)
				.getResultList();
		if (list.size()>0) {
			return (DetailTiket)list.get(0);
		}else {
			return new DetailTiket();
		}
	}
	
	public boolean isIdDetailExist(String id) {
		if (!findDetailById(id).getIdDetailTiket().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isBkDetailExist(String idTiket,Date waktu) {
		if (!findDetailByBk(idTiket, waktu).getIdDetailTiket().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	public void insertDetail(DetailTiket detailTiket) {
		super.entityManager.merge(detailTiket);
	}
	
	@Transactional
	public void updateDetail(DetailTiket detailTiket) {
		super.entityManager.merge(detailTiket);
	}
	
	@Transactional
	public void deleteDetail(String id) {
		DetailTiket detailTiket = findDetailById(id);
		super.entityManager.remove(detailTiket);
	}
	

}
