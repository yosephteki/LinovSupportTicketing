/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Logo;
import LinovSupport.Ticketing.service.LogoService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/logo")
public class LogoController {
	@Autowired
	private LogoService logoService;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestParam("file")MultipartFile logo) throws ErrorException{
		String msg;
		try {
			Logo lg= new Logo();
			lg.setLogo(logo.getBytes());
			logoService.create(lg);
			msg="success";
			return ResponseEntity.ok(msg);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	
	}

}
