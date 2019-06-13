/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import LinovSupport.Ticketing.dao.AgenDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;

@Service
public class AgenService {

	@Autowired
	private AgenDao agenDao;

	public Agen findById(String id) {
		return agenDao.findById(id);
	}

	public Agen findByBK(AccountV2 bk1, String bk2) {
		return agenDao.findByBk(bk1, bk2);
	}

	public void insertAgent(Agen agen) throws ErrorException {

		if (agenDao.isBkExist(agen.getAccount(), agen.getEmail())) {
			throw new ErrorException("agen sudah ada!");
		}
		if (agen.getEmail().isEmpty()) {
			throw new ErrorException("email tidak boleh kosong");
		}
		if (agen.getAccount().equals(null)) {
			throw new ErrorException("id account tidak boleh kosong");
		}
		if (agen.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		agenDao.insertAgen(agen);
	}

	public void updateAgen(Agen agen) throws ErrorException {
		if (agen.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (agen.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		if (!agenDao.isBkExist(agen.getAccount(), agen.getEmail())) {
			throw new ErrorException("Agen tidak ditemukan");
		}
		agenDao.updateAgen(agen);
	}

	public void delete(String id) throws ErrorException {
		if (agenDao.isIdExist(id)) {
			agenDao.delete(id);
		} else {
			throw new ErrorException("Agent tidak ditemukan!");
		}
	}

	public List<Agen> findByFilter(String email, String nama) {
		List<Agen> agen = agenDao.findByFilter(email, nama);
		return agen;
	}
	public List<Agen> findAll(){
		return agenDao.findAll();
	}
	
	public Agen findByAccount(AccountV2 accountV2){
		return agenDao.findByAccount(accountV2);
	}

}
