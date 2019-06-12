/**
 * 
 */
package LinovSupport.Ticketing.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import LinovSupport.Ticketing.model.Gambar;

/**
 * @author Yosep Teki
 *
 */
@Repository("GambarDao")
public class GambarDao extends CommonDao {

@SuppressWarnings("unchecked")
@Transactional
public Gambar findById(String id) {
	List<Gambar> list = super.entityManager
			.createQuery("FROM Gambar WHERE idGambar=:id")
			.setParameter("id", id)
			.getResultList();
	if (list.size()>0) {
		return list.get(0);
	}else {
		return new Gambar();
	}
}

@SuppressWarnings("unchecked")
@Transactional
public Gambar findByBk(String kode) {
	List<Gambar> list = super.entityManager
			.createQuery("FROM Gambar WHERE kodeGambar=:kode")
			.setParameter("kode", kode)
			.getResultList();
	if (list.size()>0) {
		return list.get(0);
	}else {
		return new Gambar();
	}
}

	
@Transactional
public void create(Gambar gambar) {
	super.entityManager.merge(gambar);
}
public void update(Gambar gambar) {
	super.entityManager.merge(gambar);
}
@Transactional
public void delete(String id) {
	Gambar gambar = findById(id);
	super.entityManager.remove(gambar);
}

}
