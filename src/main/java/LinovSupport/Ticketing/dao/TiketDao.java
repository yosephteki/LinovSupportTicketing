/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.enumeration.Level;
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
	public Tiket findByBk(String judul,String pic) {
			List<Tiket> list = super.entityManager
					.createQuery("FROM Tiket WHERE judul=:judul AND idPic=:pic")
					.setParameter("judul", judul)
					.setParameter("pic", pic)
					.getResultList();
			if (list.size()>0) {
				return (Tiket)list.get(0);
			}else {
				return new Tiket();
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
	public List<Tiket> findByFilter(String judul, String pic, Level level) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Tiket WHERE 1=1");
		if (!judul.trim().isEmpty()) {
			hql.append(" AND judul =:judul");
		}
		if (!pic.trim().isEmpty()) {
			hql.append(" AND pic =:pic");
		}
		if (!level.toString().trim().isEmpty()) {
			hql.append(" AND level =:level");
		}

		Query query = super.entityManager.createQuery(hql.toString());

		if (!judul.trim().isEmpty()) {
			query.setParameter("judul", judul);
		}
		if (!pic.trim().isEmpty()) {
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
	public List<Tiket> findByLevel(Level level){
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

    public void insertTiket(Tiket tiket) {
        super.entityManager.merge(tiket);
}
}
