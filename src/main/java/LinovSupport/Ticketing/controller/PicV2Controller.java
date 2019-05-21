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
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.service.PicV2Service;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pic")
public class PicV2Controller {
	
	@Autowired
	private PicV2Service picService;

	@PostMapping("")
	public ResponseEntity<?> insertPic(@RequestBody PicV2 pic) throws ErrorException {
		String msg;
		try {
			picService.insertPic(pic);
			msg = "success creating pic";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody PicV2 pic) throws ErrorException {
		String msg;
		try {
			picService.updatePic(pic);
			msg = "success updatin g pic";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		String msg;
		try {
			picService.deletePic(id);
			msg = "success deleting pic";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("")
	public ResponseEntity<?>findAll() {
		return ResponseEntity.ok(picService.findAll());
	}
	
	@GetMapping("idPic/{idPic}")
	public ResponseEntity<?> findById(@PathVariable String idUser) {
		return ResponseEntity.ok(picService.findById(idUser));
	}

	@GetMapping("idAccount/{idAccount/email/{email}}")
	public ResponseEntity<?> findByBk(@PathVariable String account, String email) {
		return ResponseEntity.ok(findByBk(account, email));
		}

	@GetMapping("user/{nama}/{email}")
	public ResponseEntity<?> findByFilter(@PathVariable String email,String nama) {
		return ResponseEntity.ok(picService.findByFilter(email,nama));
	}
}
