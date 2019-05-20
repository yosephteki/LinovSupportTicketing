/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.service.AccountV2Service;
import LinovSupport.Ticketing.service.PicV2Service;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/accountv2")
public class AccountV2Controller {
	
	@Autowired
	private AccountV2Service accountV2Service;
	
	@Autowired
	private PicV2Service picV2Service;
	
	@PostMapping("")
	private ResponseEntity<?> insertAccountV2(@RequestBody AccountV2 accountV2) throws ErrorException{
		try {
			String msg;
			accountV2Service.insertAccountV2(accountV2);
			System.out.println("berhasil insert");
			AccountV2 idAccount = accountV2Service.findByBk(accountV2.getNama());
			
			for(PicV2 pic : accountV2.getPics()) {
				pic.setAccount(idAccount);
				picV2Service.insertPicV2(pic);
			}
			msg="success";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?> findById(@PathVariable String id) throws ErrorException{
		try {
			String msg="success";
			AccountV2 acc = accountV2Service.findById(id);
			return ResponseEntity.ok(acc);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
