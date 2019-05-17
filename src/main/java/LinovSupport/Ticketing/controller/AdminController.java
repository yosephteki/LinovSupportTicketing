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
import LinovSupport.Ticketing.model.Admin;
import LinovSupport.Ticketing.service.AdminService;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		return ResponseEntity.ok(adminService.findById(id));
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByBk(@PathVariable String email) {
		return ResponseEntity.ok(adminService.findByBk(email));
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(adminService.findAll());
	}

	@PostMapping("")
	public ResponseEntity<?>insertAdmin(@RequestBody Admin admin)
	 throws ErrorException {
		String msg;
		try {
			adminService.insertAdmin(admin);
			msg = "success creating admin";
			return ResponseEntity.ok(msg);
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}
	@PutMapping("")
	public ResponseEntity<?> updateAdmin(@RequestBody Admin admin) throws ErrorException{
		String msg;
		try {
			adminService.updateAdmin(admin);
			msg="Sucess updating admin";
			return ResponseEntity.ok(msg);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?>deleteAdmin(@PathVariable String id) {
		String msg;
		try {
			adminService.deleteAdmin(id);
			msg = "success deleting admin";
			return ResponseEntity.ok(msg);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
