/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(accountV2Service.findAll());
	}

	@GetMapping("/{idAccount}")
	public ResponseEntity<?> findById(@PathVariable String idAccount) {
		AccountV2 account = accountV2Service.findById(idAccount);
		AccountV2 acc = new AccountV2();
		acc.setIdAccount(account.getIdAccount());
		List<PicV2> pics = new ArrayList<PicV2>();
		for (PicV2 picss : account.getPics()) {
			picss.setAccount(acc);
			pics.add(picss);
		}
		account.setPics(pics);
		return ResponseEntity.ok(account);
	}

	@GetMapping("/nama/{nama}")
	public ResponseEntity<?> findByBk(@PathVariable String nama) {
		return ResponseEntity.ok(accountV2Service.findByBk(nama));
	}

	@GetMapping("/{nama}/{telepon}/{alamat}")
	public ResponseEntity<?> findByFilter(@PathVariable String nama, @PathVariable String telepon,
			@PathVariable String alamat) {
		return ResponseEntity.ok(accountV2Service.findByFilter(nama, telepon, alamat));
	}

	@PostMapping("")
	private ResponseEntity<?> insertAccount(@RequestBody AccountV2 accountV2) throws ErrorException {
		try {
			String msg;
			accountV2Service.insertAccount(accountV2);
			System.out.println("berhasil insert");
			AccountV2 idAccount = accountV2Service.findByBk(accountV2.getNama());

			for (PicV2 pic : accountV2.getPics()) {
				pic.setAccount(idAccount);
				picV2Service.insertPic(pic);
			}
			msg = "Data berhasil di tambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("")
	public ResponseEntity<?> updateAccount(@RequestBody AccountV2 account) throws ErrorException {
		String msg;
		try {
			accountV2Service.updateAccount(account);
			msg = "Data berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable String id) {
		String msg;
		try {
			accountV2Service.deleteAccount(id);
			msg = "data berhasil dihapus";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
