/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.TiketDao;
import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Tiket;

/**
 * @author Yosep Teki
 *
 */
@Service
public class TiketService {

	@Autowired
	private TiketDao tiketDao;
	
	public Tiket findById(String id) {
		return tiketDao.findById(id);
	}
	public List<Tiket> findAll(){
		return tiketDao.findAll();
	}
	
	public List<Tiket> findByFilter(String judul, String pic, Level level){
		return tiketDao.findByFilter(judul, pic, level);
	}
	
	public List<Tiket> findByLevel(Level level){
		return tiketDao.findByLevel(level);
	}
	
	public void insertTiket(Tiket tiket)throws ErrorException {
		 tiketDao.insertTiket(tiket);
	}
}
