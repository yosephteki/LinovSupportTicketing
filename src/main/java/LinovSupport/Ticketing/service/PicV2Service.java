/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import LinovSupport.Ticketing.dao.PicV2Dao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.PicV2;

/**
 * @author Yosep Teki
 *
 */
@Service
public class PicV2Service {

	@Autowired
	private PicV2Dao picV2Dao;
	
	public List<PicV2> findAll() {
		return picV2Dao.findAll();
	}
	
	public PicV2 findById(String id) {
		return picV2Dao.findById(id);
	}
	
	public PicV2 findByBk(AccountV2 account,String email) {
		return picV2Dao.findByBk(account, email);
	}
	
	public void insertPic(PicV2 picV2) throws ErrorException {
		if (picV2.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (picV2.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		if (picV2.getAccount().getIdAccount().isEmpty()) {
			throw new ErrorException("id account tidak boleh kosong");
		}
		if (picV2Dao.isBkExist(picV2.getAccount(), picV2.getEmail())) {
			throw new ErrorException("kombinasi id account dan email sudah ada");
		}
		picV2Dao.insertPicV2(picV2);
	}

	public void updatePic(PicV2 pic) throws ErrorException {
		if (pic.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (pic.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		if (!picV2Dao.isBkExist(pic.getAccount(), pic.getEmail())) {
			throw new ErrorException("Pic tid ak ditemukan!");
		}
//		if(picV2Dao.findById(pic.getAccount().getIdAccount()).equals(pic.getAccount().getIdAccount())) {
//			throw new ErrorException("id pic dan id account tidak cocok");
//		}
		picV2Dao.updatePic(pic);
	}

	public void deletePic(String id) throws ErrorException {
		if (picV2Dao.isIdExist(id)) {
			picV2Dao.deletePic(id);
		} else {
			throw new ErrorException("Pic tidak ditemukan!");
		}
	}

	public List<PicV2> findByFilter(String email, String nama) {
		List<PicV2> pic = picV2Dao.findByFilter(email, nama);
		return pic;
	}
	
}
