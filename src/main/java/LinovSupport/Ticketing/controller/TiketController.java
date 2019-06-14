/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.enumeration.Status;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.DetailTiket;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.Tiket;
import LinovSupport.Ticketing.service.PicV2Service;
import LinovSupport.Ticketing.service.TiketService;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.LocalTime;   

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tiket")
public class TiketController {

	@Autowired
	private TiketService tiketService;
	
	@Autowired
	private PicV2Service picService;
	
	Date date = new Date();

	@GetMapping("/{idTiket}")
	public ResponseEntity<?> findById(@PathVariable String idTiket) {
		Tiket tiket = tiketService.findById(idTiket);
		tiket.getIdPic().setAccount(null);
		for(DetailTiket dtl : tiket.getDetailTiket()) {
			dtl.setIdTiket(null);
		}
		return ResponseEntity.ok(tiket);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		try {
			List<Tiket> tiket = tiketService.findAll();
			for (Tiket tics : tiket) {
				Tiket newTiket = new Tiket();
				PicV2 newPic = new PicV2();
				String idpic = tics.getIdPic().getIdPic();
				AccountV2 acc = tics.getIdPic().getAccount();
				acc.setPics(null);
				newPic.setIdPic(idpic);
				newPic.setAccount(acc);
				newPic.setNama(tics.getIdPic().getNama());
				newPic.setEmail(tics.getIdPic().getEmail());
				List<DetailTiket> detail = new ArrayList<DetailTiket>();
				tics.setIdPic(newPic);
				for (DetailTiket det : tics.getDetailTiket()) {
					det.setIdTiket(newTiket);
					detail.add(det);
				}
			}
			return new ResponseEntity<>(tiket, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

//	@GetMapping("/filter/{pic}/{level}")
//	public ResponseEntity<?> findByFilter(@PathVariable String pic,@PathVariable Level level) {
//		PicV2 pic1 = new PicV2();
//		if (!pic.trim().isEmpty()) {
//			pic1 = picService.findById(pic);
//		}else {
//			pic1 = null;
//		}
//		List<Tiket> tikets = tiketService.findByFilter(pic1,level);
//		for(Tiket tiket : tikets) {
//			tiket.getIdPic().getAccount().setPics(null);
//			tiket.getIdPic().getAccount().setAgen(null);
//			for(DetailTiket dtl : tiket.getDetailTiket()) {
//				dtl.setIdTiket(null);
//			}
//		}
//		return ResponseEntity.ok(tikets);
//	}

	@GetMapping("/level/{level}")
	public ResponseEntity<?> findByLevel(@PathVariable Level level) {
		List<Tiket> tikets = tiketService.findByLevel(level);
		for(Tiket tiket : tikets) {
			tiket.getIdPic().getAccount().setPics(null);
//			tiket.getIdPic().getAccount().setAgen(null);
			for(DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(null);
			}
		}
			
		
		
		return ResponseEntity.ok(tikets);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<?> findByStatus(@PathVariable Status status){
		List<Tiket> tikets = tiketService.findByStatus(status);
		for(Tiket tiket : tikets) {
			tiket.getIdPic().getAccount().setPics(null);
			tiket.setDetailTiket(null);
		}
		return ResponseEntity.ok(tikets);
		
	}

	@PostMapping("")
	public ResponseEntity<?> insertTiket(@RequestBody Tiket tiket) throws ErrorException {
		String msgs = null;
		try {
			tiketService.insertTiket(tiket);
			Tiket tiket2 = tiketService.findByBk(tiket.getJudulTiket(), tiket.getIdPic());
			for (DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(tiket2);  
				LocalDateTime now = LocalDateTime.now();  
				dtl.setWaktu(now);
				tiketService.insertDetail(dtl);
			}
			
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
			PicV2 pic = picService.findById(tiket2.getIdPic().getIdPic());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pic.getEmail()));
			msg.setSubject("Pendaftaran Akun Linov Ticketing");
			msg.setContent("", "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Tiket Baru telah dibuat <br> ", "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();

//			   attachPart.attachFile("hewan.png");
//			   multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);

			
			msgs = "Data Tiket berhasil ditambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("")
	public ResponseEntity<?> updateTiket(@RequestBody Tiket tiket) throws ErrorException {
		String msg;
		try {
			tiketService.updateTiket(tiket);
			msg = "success";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("/{idTiket}")
	public ResponseEntity<?> insertDetailTiket(@PathVariable("")String idTiket,@RequestBody DetailTiket detail){
		try {
			String msg;
			Tiket tiket = tiketService.findById(idTiket);
			detail.setIdTiket(tiket);
			LocalDateTime now = LocalDateTime.now();
			detail.setWaktu(now);
			tiketService.insertDetail(detail);
			msg = "berhasil insert detail";
			return ResponseEntity.ok(msg); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PatchMapping("/{id}/{status}")
	public ResponseEntity<?> patchActive(@PathVariable String id,@PathVariable Status status){
		try {
			String msg;
			Tiket tiket = tiketService.findById(id);
			tiket.setStatus(status);
			tiketService.updateTiket(tiket);
			msg = "Status tiket berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
