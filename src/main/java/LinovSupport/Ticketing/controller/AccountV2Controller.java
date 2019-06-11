/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import LinovSupport.Ticketing.encrypt.BCrypt;
import LinovSupport.Ticketing.encrypt.RandomString;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;
import LinovSupport.Ticketing.model.Gambar;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.User;
import LinovSupport.Ticketing.service.AccountV2Service;
import LinovSupport.Ticketing.service.AgenService;
import LinovSupport.Ticketing.service.GambarService;
import LinovSupport.Ticketing.service.PicV2Service;
import LinovSupport.Ticketing.service.RoleService;
import LinovSupport.Ticketing.service.UserService;

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

	@Autowired
	private UserService userService;

	@Autowired
	private AgenService agenService;

	@Autowired
	private GambarService gambarService;

	@Autowired
	private RoleService roleService;

	BCrypt bc;

	int i = 1;

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		try {
			List<AccountV2> account = accountV2Service.findAll();
			AccountV2 newAccount = new AccountV2();
			for (AccountV2 acc : account) {

				List<PicV2> Pics = new ArrayList<PicV2>();
				for (PicV2 pic : acc.getPics()) {
					pic.setAccount(newAccount);
					Pics.add(pic);
				}
				acc.setPics(Pics);
			}

			return new ResponseEntity<>(account, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> findAllv2() {
		try {
			List<AccountV2> accounts = accountV2Service.findAll();
			for (AccountV2 account : accounts) {
				AccountV2 acc1 = new AccountV2();
				acc1.setIdAccount(account.getIdAccount());
				acc1.setAgen(null);
				Agen newAgent = agenService.findByAccount(account);
				newAgent.setAccount(acc1);
				if (newAgent.getIdAgen() == null) {
					account.setAgen(null);
				} else {
					account.setAgen(newAgent);
				}
				account.setGambar(gambarService.findById(account.getIdGambar()));
				AccountV2 acc = new AccountV2();
				acc.setIdAccount(account.getIdAccount());
				List<PicV2> pics = new ArrayList<PicV2>();
				for (PicV2 picss : account.getPics()) {
					picss.setAccount(acc);
					pics.add(picss);
				}
				account.setPics(pics);
			}
			return new ResponseEntity<>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{idAccount}")
	public ResponseEntity<?> findById(@PathVariable String idAccount) {
		AccountV2 account = accountV2Service.findById(idAccount);
		AccountV2 acc1 = new AccountV2();
		acc1.setIdAccount(account.getIdAccount());
		acc1.setAgen(null);
		Agen newAgent = agenService.findByAccount(account);

		newAgent.setAccount(acc1);
		if (newAgent.getIdAgen() == null) {
			account.setAgen(null);
		} else {
			account.setAgen(newAgent);
		}

		account.setGambar(gambarService.findById(account.getIdGambar()));
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
		AccountV2 account = accountV2Service.findByBk(nama);
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

	@GetMapping("/{nama}/{telepon}/{alamat}")
	public ResponseEntity<?> findByFilter(@PathVariable String nama, @PathVariable String telepon,
			@PathVariable String alamat) {
		List<AccountV2> account = accountV2Service.findByFilter(nama, telepon, alamat);

		for (AccountV2 acc : account) {
			for (PicV2 pic : acc.getPics()) {
				pic.setAccount(null);
			}
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

//	@PostMapping("")
//	private ResponseEntity<?> insertAccount(@RequestBody AccountV2 accountV2) throws ErrorException {
//		String msg;
//		try {
//			accountV2Service.insertAccount(accountV2);
//			AccountV2 idAccount = accountV2Service.findByBk(accountV2.getNama());
//
//			for (PicV2 pic : accountV2.getPics()) {
//				pic.setAccount(idAccount);	
//				picV2Service.insertPic(pic);
//				String idPic = picV2Service.findByBk(idAccount, pic.getEmail()).getIdPic();
//				
//				RandomString randomString = new RandomString();
//				String pass = randomString.getPass();
//				String encrypt = BCrypt.hashpw(pass,bc.gensalt());
//				User user = new User();
//				user.setUsername(pic.getEmail());
//				user.setPassword(encrypt);
//				user.setIdRole("b48efe72-8058-11e9-9894-78843c9a95db");
//				user.setDetailRole(idPic);
//				userService.insertUser(user);
//				
//			}
//			msg = "Data berhasil di tambah";
//			return ResponseEntity.ok(msg);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
	@PostMapping("/param")
	private ResponseEntity<?> insertAccountv2(@RequestParam("idAccount") String idaccount,
			@RequestParam("nama") String nama, @RequestParam("telepon") String telepon,
			@RequestParam("alamat") String alamat, @RequestParam("gambar") MultipartFile gambar,
			@RequestParam("active") boolean active) {
		String msg;
		try {
			AccountV2 account = new AccountV2();
			account.setIdAccount(idaccount);
			account.setNama(nama);
			account.setTelepon(telepon);
			account.setAlamat(alamat);
			account.setActive(active);
			accountV2Service.insertAccount(account);

			Gambar fileGambar = new Gambar();
			fileGambar.setGambar(gambar.getBytes());
			gambarService.create(fileGambar);
			msg = "success";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/params")
	private ResponseEntity<?> insertAccountv3(@RequestParam("account") String inputAccount,
			@RequestParam("gambar") MultipartFile inputGambar)throws AddressException, MessagingException, IOException  {
		try {
			String msgs;
			ObjectMapper mapper = new ObjectMapper();
			AccountV2 account = mapper.readValue(inputAccount, AccountV2.class);

			AccountV2 accountV2 = new AccountV2();
			accountV2.setIdAccount(account.getIdAccount());
			accountV2.setNama(account.getNama());
			accountV2.setTelepon(account.getTelepon());
			accountV2.setAlamat(account.getAlamat());
			accountV2.setActive(account.isActive());

			Gambar gambar = new Gambar();
			int kodeGambar = accountV2.getNama().hashCode();
			gambar.setKodeGambar(kodeGambar);
			gambar.setGambar(inputGambar.getBytes());
			gambarService.create(gambar);
			String idGambar = gambarService.findByBk(kodeGambar).getIdGambar();
			accountV2.setIdGambar(idGambar);

			accountV2Service.insertAccount(accountV2);
			AccountV2 newAccount = accountV2Service.findByBk(accountV2.getNama());

			for (PicV2 pic : account.getPics()) {
				pic.setAccount(newAccount);
				picV2Service.insertPic(pic);
				PicV2 newPic = picV2Service.findByBk(newAccount, pic.getEmail());

				RandomString randomString = new RandomString();
				String pass = randomString.getPass();
				System.out.println(pass);
				String encrypt = BCrypt.hashpw(pass, bc.gensalt());

				User user = new User();
				user.setUsername(pic.getEmail());
				user.setPassword(encrypt);
				user.setIdRole(roleService.findByBk("003").getIdRole());
				user.setDetailRole(newPic.getIdPic());
				userService.insertUser(user);
//				Properties props = new Properties();	
//				   props.put("mail.smtp.auth", "true");
//				   props.put("mail.smtp.starttls.enable", "true");
//				   props.put("mail.smtp.host", "smtp.gmail.com");
//				   props.put("mail.smtp.port", "587");
//				   
//				   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//				      protected PasswordAuthentication getPasswordAuthentication() {
//				         return new PasswordAuthentication("yoseph.3912@gmail.com", "zedoteki7777");
//				      }
//				   });
//				   Message msg = new MimeMessage(session);
//				   msg.setFrom(new InternetAddress("yosephteki@gmail.com", false));
//				   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pic.getEmail()));
//				   msg.setSubject("Pendaftaran Akun Linov Ticketing");
//				   msg.setContent("email = "+pic.getEmail()+" password = "+pass, "text/html");
//				   msg.setSentDate(new Date());
//
//				   MimeBodyPart messageBodyPart = new MimeBodyPart();
//				   messageBodyPart.setContent("email = "+pic.getEmail()+" password = "+pass, "text/html");
//
//				   Multipart multipart = new MimeMultipart();
//				   multipart.addBodyPart(messageBodyPart);
//				   MimeBodyPart attachPart = new MimeBodyPart();
//
////				   attachPart.attachFile("hewan.png");
////				   multipart.addBodyPart(attachPart);
//				   msg.setContent(multipart);
//				   Transport.send(msg);
			}
			

			msgs = "Data Account berhasil ditambahkan";
			return ResponseEntity.ok(msgs);
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
			accountV2Service.findById(id).setAgen(null);
			accountV2Service.deleteAccount(id);
			msg = "data berhasil dihapus";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> deleteAccountV2(@PathVariable String id) {
		try {
			String msg;
			AccountV2 account = accountV2Service.findById(id);
			for (PicV2 pic : account.getPics()) {
				if (pic.getIdPic() != null) {
					picV2Service.deletePic(pic.getIdPic());
				}
			}
			Agen agen = agenService.findByAccount(account);
			if (agen.getIdAgen() != null) {
				agenService.delete(agen.getIdAgen());
			}
			account.setAgen(null);
			accountV2Service.findById(id).setAgen(null);
			accountV2Service.deleteAccount(id);
			msg = "Data berhasil dihapus";

			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/delv3/{id}")
	public ResponseEntity<?> deleteAccountV3(@PathVariable String id) {
		try {
			String msg;
			AccountV2 account = accountV2Service.findById(id);
			for (PicV2 pic : account.getPics()) {
				if (pic.getIdPic() != null) {
					picV2Service.deletePic(pic.getIdPic());
				}
			}
			Agen agen = agenService.findByAccount(account);
			if (agen.getIdAgen() != null) {
				agenService.delete(agen.getIdAgen());
			}
			account.setAgen(null);
			accountV2Service.findById(id).setAgen(null);
			accountV2Service.deleteAccount(id);
			msg = "Data berhasil dihapus";

			if (i <= 1) {
				i = i + 1;
				return deleteAccountV2(id);
			} else {
				i = 1;
				return ResponseEntity.ok(msg);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("/{id}/{status}")
	public ResponseEntity<?> patchActive(@PathVariable String id, @PathVariable boolean status) throws ErrorException {
		try {
			String msg;
			AccountV2 account = accountV2Service.findById(id);
			account.setActive(status);
//			accountV2Service.insertAccount(account);
			accountV2Service.updateAccount(account);
			msg = "Status aktif berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
