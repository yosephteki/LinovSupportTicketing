/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.TiketDao;
import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.enumeration.Status;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.DetailTiket;
import LinovSupport.Ticketing.model.PicV2;
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
	public Tiket findByBk(String judul, PicV2 pic) {
		return tiketDao.findByBk(judul, pic);
	}

	public List<Tiket> findAll() {
		return tiketDao.findAll();
	}

	public List<Tiket> findByFilter(PicV2 pic,Level level) {
		return tiketDao.findByFilter(pic,level);
	}

	public List<Tiket> findByLevel(Level level) {
		return tiketDao.findByLevel(level);
	}
	public List<Tiket> findByStatus(Status status){
		return tiketDao.findByStatus(status);
	}

	public void insertTiket(Tiket tiket) throws ErrorException {
		
		tiketDao.insertTiket(tiket);
	}

	public void updateTiket(Tiket tiket) throws ErrorException {
		tiketDao.updateTiket(tiket);
	}

	public void deleteTiket(String id) throws ErrorException {
		if (tiketDao.isIdExist(id)) {
			tiketDao.deleteTiket(id);
		} else {
			throw new ErrorException("Tiket tidak ditemukan");
		}
	}

//	------------------------------------ DETAIL --------------------------------

	public DetailTiket findDetailById(String id) {
		return tiketDao.findDetailById(id);

	}

	public DetailTiket findDetailByBk(String idTiket, Date waktu) {
		return tiketDao.findDetailByBk(idTiket, waktu);

	}

	public void insertDetail(DetailTiket detailTiket) {
		tiketDao.insertDetail(detailTiket);

	}

	public void updateDetail(DetailTiket detailTiket) {
		tiketDao.updateDetail(detailTiket);
	}

	public void deleteDetail(String id) throws ErrorException {
		if (tiketDao.isIdExist(id)) {
			tiketDao.deleteDetail(id);
		} else {
			throw new ErrorException("Detail Tiket tidak ditemukan");
		}
	}
}
