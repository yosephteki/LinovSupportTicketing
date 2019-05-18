/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.AgenDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Account;
import LinovSupport.Ticketing.model.Agen;

@Service
public class AgenService {
	
	@Autowired
	private AgenDao agenDao;
	
	public Agen findById(String id) {
		return agenDao.findById(id);
	}
	
	public Agen findByBK(Account bk1,String bk2) {
		return agenDao.findByBk(bk1, bk2);
	}
	public void insertAgen(Agen agen) {
		agenDao.insertAgen(agen);
	}
	
	public void insertAgent(Agen agen) throws ErrorException{
		if (agenDao.isIDExist(agen.getIdAgen())) {
			throw new ErrorException("id sudah ada");
		}
		if (agenDao.isBkExist(agen.getAccount(),agen.getEmail())) {
			throw new ErrorException("User sudah ada!");
		}
		if (agen.getAccount().equals(null)) {
			throw new ErrorException("id account tidak boleh kosong");
		}
		if (agen.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		if (agen.getEmail().isEmpty()) {
			throw new ErrorException("email tidak boleh kosong");
		}
		if (agen.isActive() == false) {
			throw new ErrorException("status active tidak boleh kosong");
		}
		agenDao.insertAgen(agen);
	}
	
	public void update(Agen agen) throws ErrorException{
		if (!agenDao.isIDExist(agen.getIdAgen())) {
			throw new ErrorException("ID Agent tidak ditemukan!");
		}
		if (!agenDao.isBkExist(agen.getAccount(),agen.getEmail())) {
			throw new ErrorException("User tidak dtemukan!");
		}
		if (agen.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (agen.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		agenDao.insertAgen(agen);
	}
	public void delete(String id) throws ErrorException{
		if (agenDao.isIDExist(id)) {
			agenDao.delete(id);
		}else {
			throw new ErrorException("Agent tidak ditemukan!");
		}
	}
	
	public List<Agen> findByFilter(String email,String nama){
	List<Agen> agen = agenDao.findByFilter(email, nama);
	return agen;
	}
	
	
	
	

}
