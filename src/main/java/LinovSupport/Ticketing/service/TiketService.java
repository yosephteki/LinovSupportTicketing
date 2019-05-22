/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.TiketDao;
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
}
