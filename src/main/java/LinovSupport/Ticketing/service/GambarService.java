/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.dao.GambarDao;
import LinovSupport.Ticketing.model.Gambar;

/**
 * @author Yosep Teki
 *
 */
@Service
public class GambarService {
	
	@Autowired
	private GambarDao gambarDao;
	
	
	public void create(Gambar gambar) {
		gambarDao.create(gambar);
	}
	public void update(Gambar gambar) {
		gambarDao.update(gambar);
	}
	
	public void delete(String id) {
		gambarDao.delete(id);
	}
	
	public Gambar findById(String id) {
		return gambarDao.findById(id);
	}
	
	public Gambar findByBk(String kode) {
		return gambarDao.findByBk(kode);
	}

}
