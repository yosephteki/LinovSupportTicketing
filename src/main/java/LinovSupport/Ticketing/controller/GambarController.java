/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Gambar;
import LinovSupport.Ticketing.service.GambarService;

/**
 * @author Yosep Teki
 *
 */
@RestController
@RequestMapping("/gambar")
public class GambarController {

	@Autowired
	private GambarService gambarService;

	@PostMapping("")
	public ResponseEntity<?> create(@RequestParam("file") MultipartFile gambar) throws ErrorException {
		String msg;
		try {
			Gambar logo = new Gambar();
			logo.setGambar(gambar.getBytes());
			gambarService.create(logo);
			msg = "success uploading image";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return ResponseEntity.ok(gambarService.findById(id));
	}
}
