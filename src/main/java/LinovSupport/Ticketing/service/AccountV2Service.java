/**
 * 
 */
package LinovSupport.Ticketing.service;

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
	public void insertAccountV2(AccountV2 account) throws ErrorException{
		accountV2Dao.insertAccount(account);
	}
}
