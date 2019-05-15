/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.AccountDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Account;

/**
 * @author Yosep Teki
 *
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	public Account findById(String id) {
		return accountDao.findById(id);
	}

	public Account findByBk(String bk) {
		return accountDao.findByBk(bk);
	}

	public void insertAccount(Account account) throws ErrorException {
		if (accountDao.isIdExist(account.getIdAccount())) {
			throw new ErrorException("ID sudah digunakan!");
		}
		if (accountDao.isBkExist(account.getNama())) {
			throw new ErrorException("nama account sudah ada");
		}
		if (account.getNama().isEmpty()) {
			throw new ErrorException("nama account tidak boleh kosong");
		}
		if (account.getAlamat().isEmpty()) {
			throw new ErrorException("alamat tidak boleh kosong");
		}
		if (account.getTelepon().isEmpty()) {
			throw new ErrorException("telepon tidak boleh kosong");
		}
		if (account.isActive() == false) {
			throw new ErrorException("status active tidak boleh kosong");
		}
		accountDao.create(account);
	}
	
	public void updateAccount(Account account) throws ErrorException{
		if (!accountDao.isIdExist(account.getIdAccount())) {
			throw new ErrorException("id account tidak ditemukan");
		}
		if (!accountDao.isBkExist(account.getNama())) {
			throw new ErrorException("Nama PT tidak ditemukan");
		}
		if (!accountDao.findById(account.getIdAccount()).getNama().equals(account.getNama())) {
			throw new ErrorException("id account dan nama PT tidak cocok");
		}
		if (account.getAlamat().isEmpty()) {
			throw new ErrorException("alamat tidak boleh kosong");
		}
		if (account.getTelepon().isEmpty()) {
			throw new ErrorException("telepon tidak boleh kosong");
		}
		if (account.isActive() == false) {
			throw new ErrorException("status active tidak boleh kosong");
		}
		accountDao.update(account);
	}
	
	public void delete(String id) throws ErrorException{
		if (accountDao.isIdExist(id)) {
			accountDao.delete(id);
		}else {
			throw new ErrorException("Account tidak ditemukan!");
		}
	}
	
}
