/**
 * 
 */
package LinovSupport.Ticketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.AccountV2Dao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;

/**
 * @author Yosep Teki
 *
 */
@Service
public class AccountV2Service {

	@Autowired
	private AccountV2Dao accountV2Dao;
	
	public AccountV2 findById(String id) {
		return accountV2Dao.findById(id);
	}
	public AccountV2 findByBk(String nama) { 
		return accountV2Dao.findByBk(nama);
	}
	public void insertAccount(AccountV2 account) throws ErrorException{
		
		if (account.getNama().isEmpty()) {
			throw new ErrorException("nama account tidak boleh kosong");
		}
		if (account.getAlamat().isEmpty()) {
			throw new ErrorException("alamat tidak boleh kosong");
		}
		if (account.getTelepon().isEmpty()) {
			throw new ErrorException("telepon tidak boleh kosong");
		}
		if (accountV2Dao.isBkExist(account.getNama())) {
			throw new ErrorException("nama account sudah ada");
		}
		accountV2Dao.insertAccount(account);
	}
	public void updateAccount(AccountV2 account) throws ErrorException{
		
		if (account.getAlamat().isEmpty()) {
			throw new ErrorException("alamat tidak boleh kosong");
		}
		if (account.getTelepon().isEmpty()) {
			throw new ErrorException("telepon tidak boleh kosong");
		}
		if (!accountV2Dao.isBkExist(account.getNama())) {
			throw new ErrorException("Nama PT tidak ditemukan");
		}
		accountV2Dao.updateAccount(account);
	}
	public void deleteAccount(String id) throws ErrorException{
		if (accountV2Dao.isIdExist(id)) {
			accountV2Dao.deleteAccount(id);
		}else {
			throw new ErrorException("Account tidak ditemukan!");
		}
	}
	
	public List<AccountV2> findByFilter(String telepon,String alamat,String nama){
		List<AccountV2> account = accountV2Dao.findByFilter(telepon, alamat, nama);
		return account;
	}

	public List<AccountV2> findAll(){
		return accountV2Dao.findAll();
	}

}
