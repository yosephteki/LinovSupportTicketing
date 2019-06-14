package LinovSupport.Ticketing.controller;			
import java.util.Date;
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
import LinovSupport.Ticketing.encrypt.RandomString;			
import LinovSupport.Ticketing.exception.ErrorException;			
import LinovSupport.Ticketing.model.Admin;			
import LinovSupport.Ticketing.model.Agen;			
import LinovSupport.Ticketing.model.Multi;			
import LinovSupport.Ticketing.model.User;			
import LinovSupport.Ticketing.service.AdminService;			
import LinovSupport.Ticketing.service.UserService;			
			
@CrossOrigin(origins= "*", allowedHeaders= "*")			
@RestController			
@RequestMapping("/admin")			
public class AdminController {			
			
	@Autowired		
	private AdminService adminService;		
	@Autowired		
	private UserService userService;		
			
	private BCrypt bc;		
			
			
	@GetMapping("/{id}")		
	public ResponseEntity<?> findById(@PathVariable String id) {		
		return ResponseEntity.ok(adminService.findById(id));	
	}		
	@GetMapping("/email/{email}")		
	public ResponseEntity<?> findByBk(@PathVariable String email) {		
		return ResponseEntity.ok(adminService.findByBk(email));	
	}		
			
	@GetMapping("")		
	public ResponseEntity<?> findAll() {		
		return ResponseEntity.ok(adminService.findAll());	
	}		
			
			
	@PostMapping("")		
	public ResponseEntity<?> insertAdmin(@RequestBody Multi admin) throws ErrorException {		
		String msgs;	
		try {	
			Admin admint = new Admin();
			
			admint.setIdAdmin(admin.getId());
			admint.setNama(admin.getNama());
			admint.setEmail(admin.getEmail());
			admint.setActive(admin.isActive());
			
			adminService.insertAdmin(admint);
			
			Admin newAdmin = new Admin();
			newAdmin = adminService.findByBk(admin.getEmail());
			
			System.out.println(admin.getRole());
			RandomString randomString = new RandomString();
			String pass = admin.getPassword();
			String encrypt = BCrypt.hashpw(pass,bc.gensalt());
			User user = new User();
			user.setUsername(admin.getUsername());
			user.setPassword(encrypt);
			user.setIdRole(admin.getRole());
			user.setDetailRole(newAdmin.getIdAdmin());
			
			
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
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newAdmin.getEmail()));
			msg.setSubject("Pendaftaran Akun Linov Ticketing");
			msg.setContent("Gunakan Informasi ini untuk login : <br> email = " + newAdmin.getEmail() + " password = " + pass, "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Anda telah terdaftar sebagai <b>Admin</b> di Linov support ticketing <br> Gunakan Informasi ini untuk login : <br> username = <b>" + newAdmin.getEmail() + "</b> password = <b>" + pass+"</b>", "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();

//			   attachPart.attachFile("hewan.png");
//			   multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);

			
			
			msgs = "Data berhasil di tambah";
			return ResponseEntity.ok(msgs);
		} catch (Exception e) {	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}		
			
	@PutMapping("")		
	public ResponseEntity<?> updateAdmin(@RequestBody Admin admin) throws ErrorException{		
		String msg;	
		try {	
			adminService.updateAdmin(admin);
			msg="Sucess updating admin";
			return ResponseEntity.ok(msg);
		} catch(Exception e) {	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}	
	}		
	@DeleteMapping("/del/{id}")		
	public ResponseEntity<?>deleteAdmin(@PathVariable String id) {		
		String msg;	
		try {	
			adminService.deleteAdmin(id);
			msg = "success deleting admin";
			return ResponseEntity.ok(msg);
		} catch(Exception e) {	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}		
}			