/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.LogoDao;
import LinovSupport.Ticketing.model.Logo;

/**
 * @author Yosep Teki
 *
 */
@Service
public class LogoService {
	
	@Autowired
	private LogoDao logoDao;
	
	public void create(Logo logo) {
		logoDao.create(logo);
	}

}
