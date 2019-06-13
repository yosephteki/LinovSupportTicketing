package LinovSupport.Ticketing.controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.encrypt.BCrypt;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Multi;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.User;
import LinovSupport.Ticketing.service.PicV2Service;
import LinovSupport.Ticketing.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pic")
public class PicV2Controller {

	@Autowired
	private PicV2Service picService;

	@Autowired
	private UserService userService;

	private BCrypt bc;

	@PostMapping("")
	public ResponseEntity<?> insertPic(@RequestBody Multi pic) throws ErrorException {
		String msgs;
		try {

			PicV2 picc = new PicV2();
			picc.setIdPic(pic.getId());
			picc.setAccount(pic.getAccount());
			picc.setEmail(pic.getEmail());
			picc.setActive(pic.isActive());
			picc.setNama(pic.getNama());

			picService.insertPic(picc);

			PicV2 newPic = new PicV2();
			newPic = picService.findByBk(pic.getAccount(), pic.getEmail());
			String pass = pic.getPassword();
			String encrypt = BCrypt.hashpw(pass, bc.gensalt());

			User user = new User();
			user.setUsername(pic.getUsername());
			user.setPassword(encrypt);
			user.setIdRole(pic.getRole());
			user.setDetailRole(newPic.getIdPic());
			userService.insertUser(user);
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("yoseph.3912@gmail.com", "zedoteki7777");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("yosephteki@gmail.com", false));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newPic.getEmail()));
			msg.setSubject("Pendaftaran Akun Linov Ticketing");
			msg.setContent("Gunakan Informasi ini untuk login : <br> email = " + newPic.getEmail() + " password = " + pass, "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Anda telah terdaftar sebagai <b>Pic</b> di Linov support ticketing <br> Gunakan Informasi ini untuk login : <br> username = <b>" + newPic.getEmail() + "</b> password = <b>" + pass+"</b>", "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();

//			   attachPart.attachFile("hewan.png");
//			   multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);

			
			msgs = "Berhasil menambahkan data Agen";
			return ResponseEntity.ok(msgs);
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
			msg = "success updating pic";
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
	public ResponseEntity<?> findAll() {
		try {
			List<PicV2> pics = picService.findAll();
			for (PicV2 pic : pics) {
				AccountV2 acc = new AccountV2();
				String idAcc = pic.getAccount().getIdAccount();
				acc.setIdAccount(idAcc);
				pic.setAccount(acc);
			}
			return new ResponseEntity<>(pics, HttpStatus.OK);
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
	public ResponseEntity<?> findByBk(@RequestBody AccountV2 account, @PathVariable String email) {
		PicV2 pic = picService.findByBk(account, email);
		AccountV2 acc = new AccountV2();
		acc.setIdAccount(account.getIdAccount());
		pic.setAccount(acc);
		return ResponseEntity.ok(pic);
	}

	@GetMapping("/{email}/{nama}")
	public ResponseEntity<?> findByFilter(@PathVariable String email, @PathVariable String nama) {
		List<PicV2> picV2s = picService.findByFilter(email, nama);

		for (PicV2 pic : picV2s) {
			AccountV2 account = new AccountV2();
			account.setIdAccount(pic.getAccount().getIdAccount());
			account.setNama(pic.getAccount().getNama());
			pic.setAccount(account);
		}
		return new ResponseEntity<>(picV2s, HttpStatus.OK);
	}
}
