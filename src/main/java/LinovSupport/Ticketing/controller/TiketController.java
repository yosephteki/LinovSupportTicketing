/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Tiket;
import LinovSupport.Ticketing.service.TiketService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tiket")
public class TiketController {
	
	@Autowired
	private TiketService tiketService;
	
	@GetMapping("/{idTiket}")
	public ResponseEntity<?> findById(@PathVariable String idTiket){
	return ResponseEntity.ok(tiketService.findById(idTiket));
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(tiketService.findAll());
	}
	
	@GetMapping("/judul/{judul}/pic/{pic}/level/{level}")
	public ResponseEntity<?> findByFilter(@PathVariable String judul, 
			@PathVariable String pic,@PathVariable String level){
		return ResponseEntity.ok(tiketService.findByFilter(judul, pic, level));
	}
	
	@GetMapping("/level/{level}")
	public ResponseEntity<?> findByLevel(@PathVariable String level){
		return ResponseEntity.ok(tiketService.findByLevel(level));
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Tiket tiket) throws ErrorException {
		String msg;
		try {
			tiketService.insertTiket(tiket);
			msg= "Success creating ticket";
			return ResponseEntity.ok(msg);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	
	

}
