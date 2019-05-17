/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PatchMapping;
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
import LinovSupport.Ticketing.model.Pic;
import LinovSupport.Ticketing.service.AccountService;
import LinovSupport.Ticketing.service.LogoService;
import LinovSupport.Ticketing.service.PicService;

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
	@GetMapping("")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(accountService.findAll());
	}
	@GetMapping("/idAccount/{idAccount}")
	public ResponseEntity<?> findById(@PathVariable String idAccount){
		return ResponseEntity.ok(accountService.findById(idAccount));
	}
	@GetMapping("/nama/{nama}")
	public ResponseEntity<?> findByBk(@PathVariable String nama){
		return ResponseEntity.ok(accountService.findByBk(nama));
	}
	@GetMapping("/{nama}/{telepon}/{alamat}")
	public ResponseEntity<?> findByFilter(@PathVariable String nama,@PathVariable String telepon
			,@PathVariable String alamat){
		return ResponseEntity.ok(accountService.findByFilter(nama, telepon, alamat));
	}
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
	
//	@PostMapping("/detail")
//	public ResponseEntity<?> insertAccountdetail(@RequestBody Account account)
//	throws ErrorException{
//		String msg;
//		try {
//			accountService.insertAccount(account);
//			Account account2 = accountService.findByBk(account.getNama());
//			for(Pic pic : account.getPic()) {
//				pic.setIdAccount(account2);
//				picService.create(pic);
//			}
//			msg="success";
//			return ResponseEntity.ok(msg);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}

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
	public ResponseEntity<?> deleteAccount(@PathVariable String id) {
		String msg;
		try {
			accountService.deleteAccount(id);
			msg = "data berhasil dihapus";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
