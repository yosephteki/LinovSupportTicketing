/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.Tiket;

/**
 * @author Yosep Teki
 *
 */
@Repository("TiketDao")
public class TiketDao extends CommonDao{

	@Transactional
	public Tiket findById(String id) {
		Tiket tiket;
		try {
			tiket = (Tiket)super.entityManager
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
	public List<Tiket> findAll(){
		List<Tiket> list = super.entityManager
				.createQuery("FROM Tiket")
				.getResultList();
		return list;
	}
		
}
