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
import LinovSupport.Ticketing.model.Role;
import LinovSupport.Ticketing.service.RoleService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Role role)throws ErrorException{
		String msg;
		try {
			roleService.create(role);
			msg="success creating role";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	

}
