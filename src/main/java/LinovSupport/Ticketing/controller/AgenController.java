/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import LinovSupport.Ticketing.encrypt.BCrypt;
import LinovSupport.Ticketing.encrypt.RandomString;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;
import LinovSupport.Ticketing.model.Multi;
import LinovSupport.Ticketing.model.User;
import LinovSupport.Ticketing.service.AgenService;
import LinovSupport.Ticketing.service.UserService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/agen")
public class AgenController {

	@Autowired
	private AgenService agenService;
	@Autowired
	private UserService userService;
	
	private BCrypt bc;

	@PostMapping("")
	public ResponseEntity<?> insertAgen(@RequestBody Multi agen) throws ErrorException, AddressException, MessagingException, IOException {
		String msgs;
		try {
			Agen agent = new Agen();
			
			agent.setIdAgen(agen.getId());
			agent.setAccount(agen.getAccount());
			agent.setEmail(agen.getEmail());
			agent.setActive(agen.isActive());
			agent.setNama(agen.getNama());
			
			agenService.insertAgent(agent);
			
			Agen newAgen = new Agen();
			newAgen = agenService.findByBK(agen.getAccount(),agen.getEmail());
			
			System.out.println(agen.getRole());
			RandomString randomString = new RandomString();
			String pass = agen.getPassword();
			String encrypt = BCrypt.hashpw(pass,bc.gensalt());
			User user = new User();
			user.setUsername(agen.getUsername());
			user.setPassword(encrypt);
			user.setIdRole(agen.getRole());
			user.setDetailRole(newAgen.getIdAgen());

			
			userService.insertUser(user);
			
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp.gmail.com");
//			props.put("mail.smtp.port", "587");
//
//			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("yoseph.3912@gmail.com", "zedoteki7777");
//				}
//			});
//			Message msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress("yosephteki@gmail.com", false));
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newAgen.getEmail()));
//			msg.setSubject("Pendaftaran Akun Linov Ticketing");
//			msg.setContent("Gunakan Informasi ini untuk login : <br> email = " + newAgen.getEmail() + " password = " + pass, "text/html");
//			msg.setSentDate(new Date());
//
//			MimeBodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent("Gunakan Informasi ini untuk login : <br> email = " + newAgen.getEmail() + " password = " + pass, "text/html");
//
//			Multipart multipart = new MimeMultipart();
//			multipart.addBodyPart(messageBodyPart);
//			MimeBodyPart attachPart = new MimeBodyPart();
//
////			   attachPart.attachFile("hewan.png");
////			   multipart.addBodyPart(attachPart);
//			msg.setContent(multipart);
//			Transport.send(msg);

			
			msgs = "Berhasil menambahkan data Agen";
			return ResponseEntity.ok(msgs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateAgen(@RequestBody Agen agen) throws ErrorException{
		String msg;
		try {
			agenService.updateAgen(agen);
			msg="Data berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		String msg;
		try {
			agenService.delete(id);
			msg="success deleting agent";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{email}/{nama}")
	public ResponseEntity<?> findByFilter(@PathVariable String email,@PathVariable String nama){
		try {
		List<Agen> agens = agenService.findByFilter(email, nama);
		
		for(Agen agen : agens) {
			AccountV2 acc = new AccountV2();
			String idacc = agen.getAccount().getIdAccount();
			acc.setIdAccount(idacc);
			agen.setAccount(acc);
		}
		return new ResponseEntity<>(agens,HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("id/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		Agen agen = agenService.findById(id);
		AccountV2 account = new AccountV2();
		account.setIdAccount(agen.getAccount().getIdAccount());
		account.setNama(agen.getAccount().getNama());
		agen.setAccount(account);
		return ResponseEntity.ok(agen);
	}
	@GetMapping("")
	public ResponseEntity<?> findAll(){
		try {
			List<Agen> agens = agenService.findAll();
			
			for(Agen agen : agens) {
				AccountV2 acc = new AccountV2();
				String idacc = agen.getAccount().getIdAccount();
				acc.setIdAccount(idacc);
				agen.setAccount(acc);
			}
			return new ResponseEntity<>(agens,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
//	@GetMapping("/acc")
//	public ResponseEntity<?> findByAccount(){
//		AccountV2 acc = new AccountV2();
//		acc.setIdAccount("6db55088-c27b-4777-bb20-b44cde31adf2");
//		return ResponseEntity.ok(agenService.findByAccount(acc));
//	}
	
}
