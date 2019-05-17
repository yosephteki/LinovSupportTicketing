/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.service.UserService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("idUser/{idUser}")
	public ResponseEntity<?> findById(@PathVariable String idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}

	@GetMapping("username/{username}")
	public ResponseEntity<?> findByBk(@PathVariable String username) {
		return ResponseEntity.ok(userService.findByBk(username));
	}

	@GetMapping("user/{username}")
	public ResponseEntity<?> findByFilter(@PathVariable String username) {
		return ResponseEntity.ok(userService.findByFilter(username));
	}
}
