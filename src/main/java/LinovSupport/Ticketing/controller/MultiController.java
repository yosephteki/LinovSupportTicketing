/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Agen;
import LinovSupport.Ticketing.model.Multi;
import LinovSupport.Ticketing.model.User;
import LinovSupport.Ticketing.service.AgenService;
import LinovSupport.Ticketing.service.UserService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/multi")
public class MultiController {
	
	@Autowired
	private AgenService agenService;
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Multi multi)throws ErrorException{
		String msg;
		try {
			
			Agen agen = new Agen();
			
			agen.setIdAgen(multi.getId());
			agen.setAccount(multi.getAccount());
			agen.setEmail(multi.getEmail());
			agen.setActive(multi.isActive());
			agen.setNama(multi.getNama());
			
			agenService.insertAgent(agen);
			Agen newAgen = new Agen();
			newAgen = agenService.findByBK(multi.getAccount(),multi.getEmail());
			
			User user = new User();
			user.setPassword(multi.getPassword());
			user.setIdRole(multi.getRole());
			user.setDetailRole(newAgen.getIdAgen());
			
			userService.insertUser(user);
			
			msg="success";
			return ResponseEntity.ok(newAgen);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
