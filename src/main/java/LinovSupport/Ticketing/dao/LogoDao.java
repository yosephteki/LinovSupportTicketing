/**
 * 
 */
package LinovSupport.Ticketing.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.Logo;

/**
 * @author Yosep Teki
 *
 */
@Repository("LogoDao")
public class LogoDao extends CommonDao{

	@Transactional
	public void create(Logo logo) {
		super.entityManager.merge(logo);
	}
	
//	@Transactional 
//	public Logo findById(String id) {
//		
//	}
	
	
}
