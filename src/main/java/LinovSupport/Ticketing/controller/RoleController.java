/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		return ResponseEntity.ok(roleService.findById(id));
	}

	@GetMapping("/kode/{kodeRole}")
	public ResponseEntity<?> findByBk(@PathVariable String kodeRole) {
		return ResponseEntity.ok(roleService.findByBk(kodeRole));
	}

	@GetMapping("")
	public ResponseEntity<?> findAllRole() {
		return ResponseEntity.ok(roleService.findAllRole());
	}

	@GetMapping("/nama/{nama}")
	public ResponseEntity<?> findByFilter(@PathVariable String nama) {
		return ResponseEntity.ok(roleService.findByFilter(nama));
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Role role) throws ErrorException {
		String msg;
		try {
			roleService.insertRole(role);
			msg = "success creating role";
//			msg = roleService.findByBk(role.getKodeRole()).getIdRole();
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Role role) throws ErrorException {
		String msg;
		try {
			roleService.updateRole(role);
			msg = "success updating role";
			return ResponseEntity.ok(msg);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		String msg;
		try {
			roleService.deleteRole(id);
			msg = "success deleting role";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
