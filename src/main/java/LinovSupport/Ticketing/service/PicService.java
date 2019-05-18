/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.PicDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Pic;

/**
 * @author Yosep Teki
 *
 */
@Service
public class PicService {

	@Autowired
	private PicDao picDao;

	public Pic findById(String id) {
		return picDao.findById(id);
	}

	public Pic findByBk(String bk1, String bk2) {
		return picDao.findByBk(bk1, bk2);
	}

	public void insertPic(Pic pic) throws ErrorException {
		if (picDao.isIdExist(pic.getIdPic())) {
			throw new ErrorException("ID sudah digunakan!");
		}
		if (picDao.isBkExist(pic.getIdAccount().getIdAccount(), pic.getEmail())) {
			throw new ErrorException("Pic sudah ada!");
		}
		if (pic.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (pic.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		picDao.insertPic(pic);
	}

	public void updatePic(Pic pic) throws ErrorException {
		if (!picDao.isIdExist(pic.getIdPic())) {
			throw new ErrorException("ID tidak ditemukan!");
		}
		if (!picDao.isBkExist(pic.getIdAccount().getIdAccount(), pic.getEmail())) {
			throw new ErrorException("Pic tidak ditemukan!");
		}
		if (pic.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (pic.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		picDao.updatePic(pic);
	}

	public void deletePic(String id) throws ErrorException {
		if (picDao.isIdExist(id)) {
			picDao.deletePic(id);
		} else {
			throw new ErrorException("Pic tidak ditemukan!");
		}
	}

	public List<Pic> findByFilter(String email, String nama) {
		return findByFilter(email, nama);
	}
}
