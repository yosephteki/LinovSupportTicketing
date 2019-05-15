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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Account;
import LinovSupport.Ticketing.model.Logo;
import LinovSupport.Ticketing.service.AccountService;
import LinovSupport.Ticketing.service.LogoService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
//	@Autowired
//	private LogoService logoService;

//	@PostMapping("")
//	public ResponseEntity<?> create(@RequestBody Account account, @RequestParam("file") MultipartFile logo)
//			throws ErrorException {
//		String msg;
//		try {
//			accountService.create(account);
//			Logo lg = new Logo();
//			lg.setLogo(logo.getBytes());
//			logoService.create(lg);
//			account.setLogo(lg.getId_logo());
//			accountService.update(account);
//			msg = "success creating account";
//			return ResponseEntity.ok(msg);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
	@PostMapping("")
	public ResponseEntity<?> insertAccount(@RequestBody Account account)
			throws ErrorException {
		String msg;
		try {
			accountService.insertAccount(account);
			msg = "Data berhasil di tambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("")
	public ResponseEntity<?> updateAccount(@RequestBody Account account) throws ErrorException {
		String msg;
		try {
			accountService.updateAccount(account);
			msg = "Data berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		String msg;
		try {
			accountService.delete(id);
			msg = "success deleting account";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
