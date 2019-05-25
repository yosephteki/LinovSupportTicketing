/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.AccountV2Dao;
import LinovSupport.Ticketing.dao.AgenDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Account;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;

@Service
public class AgenService {

	@Autowired
	private AgenDao agenDao;
	
	@Autowired
	private AccountV2Dao accDao;
	
	private AccountV2 acc;

	public Agen findById(String id) {
		return agenDao.findById(id);
	}

	public Agen findByBK(Account bk1, String bk2) {
		return agenDao.findByBk(bk1, bk2);
	}

//	public void insertAgen(Agen agen) {
//		agenDao.insertAgen(agen);
//	}
//	
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

	public void update(Agen agen) throws ErrorException {

		if (agen.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (agen.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		if (!agenDao.isIdExist(agen.getIdAgen())) {
			throw new ErrorException("ID Agent tidak ditemukan!");
		}
		if (!agenDao.isBkExist(agen.getAccount(), agen.getEmail())) {
			throw new ErrorException("User tidak dtemukan!");
		}
		if (!accDao.isIdExist(acc.getIdAccount())) {
			throw new ErrorException("id account tidak ditemukan");
		}
		agenDao.insertAgen(agen);
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

}
