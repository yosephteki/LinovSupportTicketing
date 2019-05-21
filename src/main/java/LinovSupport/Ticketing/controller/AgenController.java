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
@RequestMapping("/agen")
public class AgenController {

	@Autowired
	private AgenService agenService;
	@Autowired
	private UserService userService;

	@PostMapping("")
	public ResponseEntity<?> insertAgen(@RequestBody Multi agen) throws ErrorException {
		String msg;
		try {
			Agen agent = new Agen();
			
			agent.setIdAgen(agen.getId());
			agent.setAccount(agen.getAccount());
			agent.setEmail(agen.getEmail());
			agent.setActive(agen.isActive());
			agent.setNama(agen.getNama());
			System.out.println(agen.getRole());
			System.out.println(agen.getEmail());
			
			agenService.insertAgent(agent);
			
			Agen newAgen = new Agen();
			newAgen = agenService.findByBK(agen.getAccount(),agen.getEmail());
			
			System.out.println(agen.getRole());
			
			User user = new User();
			user.setUsername(agen.getUsername());
			user.setPassword(agen.getPassword());
			user.setIdRole(agen.getRole());
			user.setDetailRole(newAgen.getIdAgen());

			
			userService.insertUser(user);
			
			msg = "Data berhasil di tambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Agen agen) throws ErrorException{
		String msg;
		try {
			agenService.update(agen);
			msg="success updating agent";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		String msg;
		try {
			agenService.delete(id);
			msg="success deleting agent";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{email}/{nama}")
	public ResponseEntity<?> findByFilter(@PathVariable String email,@PathVariable String nama){
		return ResponseEntity.ok(agenService.findByFilter(email, nama));
	}
	@GetMapping("id/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return ResponseEntity.ok(agenService.findById(id));
	}
	
}
