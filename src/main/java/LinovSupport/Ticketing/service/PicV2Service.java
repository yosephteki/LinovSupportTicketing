/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.PicV2Dao;
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
	
	public PicV2 findById(String id) {
		return picV2Dao.findById(id);
	}
	
	public PicV2 findByBk(AccountV2 account,String email) {
		return picV2Dao.findByBk(account, email);
	}
	
	public void insertPicV2(PicV2 picV2) {
		picV2Dao.insertPicV2(picV2);
	}
	
}
