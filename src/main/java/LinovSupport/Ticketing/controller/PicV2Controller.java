package LinovSupport.Ticketing.controller;
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
//	@PostMapping("/list")
//	public ResponseEntity<?> insertListPic(@r List<PicV2> pics) throws ErrorException{
//		String msg;
//		try {
//			for(PicV2 pic : pics) {
//				System.out.println(pic.getNama());
//			}
//			msg="Data Pic berhasil ditambahkan";
//			return ResponseEntity.ok(msg);
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
	
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
		try {	
			List<PicV2> pics = picService.findAll();
			
			for(PicV2 pic : pics) {
				AccountV2 acc= new AccountV2();
				String idAcc = pic.getAccount().getIdAccount();
				acc.setIdAccount(idAcc);
				pic.setAccount(acc);
				}
			return new ResponseEntity<>(pics,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}
	
	@GetMapping("/{idPic}")
	public ResponseEntity<?> findById(@PathVariable String idPic) {
		PicV2 pic = picService.findById(idPic);
		AccountV2 account = new AccountV2();
		account.setIdAccount(pic.getAccount().getIdAccount());
		account.setNama(pic.getAccount().getNama());
		pic.setAccount(account);
		return ResponseEntity.ok(pic);
	}
	

	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByBk(@RequestBody AccountV2 account,@PathVariable String email) {
		PicV2 pic = picService.findByBk(account, email);
		pic.setAccount(null);
		return ResponseEntity.ok(pic);
		}

	@GetMapping("/{email}/{nama}")
	public ResponseEntity<?> findByFilter(@PathVariable String email,@PathVariable String nama) {
		List<PicV2> picV2s = picService.findByFilter(email, nama);
		
		for(PicV2 pic : picV2s) {
			AccountV2 account = new AccountV2();
			account.setIdAccount(pic.getAccount().getIdAccount());
			account.setNama(pic.getAccount().getNama());
			pic.setAccount(account);
		}
		return new ResponseEntity<>(picV2s,HttpStatus.OK);
	}
}
