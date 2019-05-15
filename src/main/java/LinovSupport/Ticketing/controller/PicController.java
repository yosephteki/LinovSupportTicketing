/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Pic;
import LinovSupport.Ticketing.service.PicService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pic")
public class PicController {

	@Autowired
	private PicService picService;

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Pic pic) throws ErrorException {
		String msg;
		try {
			picService.create(pic);
			msg = "success creating pic";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Pic pic) throws ErrorException {
		String msg;
		try {
			picService.update(pic);
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
			picService.delete(id);
			msg = "success deleting pic";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
