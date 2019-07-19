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
			messageBodyPart.setContent("\r\n" + 
					"<!DOCTYPE html>\r\n" + 
					"<html class=\"no-js\" lang=\"en\">\r\n" + 
					"\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"    <!-- TITLE -->\r\n" + 
					"    <title>notif</title>\r\n" + 
					"    <!-- DESCRIPTION -->\r\n" + 
					"    <meta name=\"description\" content=\"\">\r\n" + 
					"    <!-- KEYWORDS -->\r\n" + 
					"    <meta name=\"keywords\" content=\"\">\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*\">\r\n" + 
					"    <!-- ICONS  -->\r\n" + 
					"    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css\">\r\n" + 
					"    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n" + 
					"    <link rel=\"stylesheet\" type=\"text/css\" href=\"bld/css/spectrum.min.css\">\r\n" + 
					"        <link rel=\"stylesheet\" href=\"//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css\">\r\n" + 
					"        <!-- STYLE CSS -->\r\n" + 
					"        <link rel=\"stylesheet\" type=\"text/css\" href=\"bld/css/style.css\">\r\n" + 
					"        <link rel=\"stylesheet\" type=\"text/css\" href=\"bld/css/mobile.css\">\r\n" + 
					"\r\n" + 
					"        <!-- GOOGLE FONTS LINK -->\r\n" + 
					"        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700\" rel=\"stylesheet\" />\r\n" + 
					"        <link href=\"https://fonts.googleapis.com/css?family=Lora\" rel=\"stylesheet\" />\r\n" + 
					"        <link href=\"https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700\" rel=\"stylesheet\" />\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"        <style type=\"text/css\">\r\n" + 
					"                /*------ Client-Specific Style ------ */\r\n" + 
					"                @-ms-viewport{width:device-width;}\r\n" + 
					"                table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;}\r\n" + 
					"                img{-ms-interpolation-mode:bicubic; border: 0;}\r\n" + 
					"                p, a, li, td, blockquote{mso-line-height-rule:exactly;}\r\n" + 
					"                p, a, li, td, body, table, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%;}\r\n" + 
					"                #outlook a{padding:0;}\r\n" + 
					"                .ReadMsgBody{width:100%;} .ExternalClass{width:100%;}\r\n" + 
					"                .ExternalClass,.ExternalClass div,.ExternalClass font,.ExternalClass p,.ExternalClass span,.ExternalClass td,img{line-height:100%;}\r\n" + 
					"\r\n" + 
					"                /*------ Reset Style ------ */\r\n" + 
					"                *{-webkit-text-size-adjust:none;-webkit-text-resize:100%;text-resize:100%;}\r\n" + 
					"                table{border-spacing: 0 !important;}\r\n" + 
					"                h1, h2, h3, h4, h5, h6{display:block; Margin:0; padding:0;}\r\n" + 
					"                img, a img{border:0; height:auto; outline:none; text-decoration:none;}\r\n" + 
					"                body, #bodyTable, #bodyCell{height:100%; margin:0; padding:0; width:100%;}\r\n" + 
					"\r\n" + 
					"                .appleLinks a {color: #c2c2c2 !important; text-decoration: none;}\r\n" + 
					"        span.preheader { display: none !important; }\r\n" + 
					"\r\n" + 
					"                /*------ Google Font Style ------ */\r\n" + 
					"                [style*=\"Open Sans\"] {font-family:'Open Sans', Helvetica, Arial, sans-serif !important;}\r\n" + 
					"                [style*=\"Poppins\"] {font-family:'Poppins', Helvetica, Arial, sans-serif !important;}\r\n" + 
					"                [style*=\"Lora\"] {font-family:'Lora', Georgia, Times, serif !important;}\r\n" + 
					"\r\n" + 
					"                /*------ General Style ------ */\r\n" + 
					"                .wrapperWebview, .wrapperBody, .wrapperFooter{width:100%; max-width:600px; Margin:0 auto;}\r\n" + 
					"\r\n" + 
					"                /*------ Column Layout Style ------ */\r\n" + 
					"                .tableCard {text-align:center; font-size:0;}\r\n" + 
					"                \r\n" + 
					"                /*------ Images Style ------ */\r\n" + 
					"                .imgHero img{ width:600px; height:auto; }\r\n" + 
					"\r\n" + 
					"        </style>\r\n" + 
					"        \r\n" + 
					"                <!-- Email Wrapper Webview Open //-->\r\n" + 
					"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\" width=\"100%\" class=\"wrapperWebview\">\r\n" + 
					"                        <tr>\r\n" + 
					"                                <td align=\"center\" valign=\"top\">\r\n" + 
					"                                        <!-- Content Table Open // -->\r\n" + 
					"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
					"                                                \r\n" + 
					"                                        </table>\r\n" + 
					"                                        <!-- Content Table Close // -->\r\n" + 
					"                                </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                </table>\r\n" + 
					"                <!-- Email Wrapper Webview Close //-->\r\n" + 
					"\r\n" + 
					"                <!-- Email Wrapper Header Open //-->\r\n" + 
					"                \r\n" + 
					"                <!-- Email Wrapper Header Close //-->\r\n" + 
					"\r\n" + 
					"                <!-- Email Wrapper Body Open // -->\r\n" + 
					"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\" width=\"100%\" class=\"wrapperBody\">\r\n" + 
					"                        <tr>\r\n" + 
					"                                <td align=\"center\" valign=\"top\">\r\n" + 
					"\r\n" + 
					"                                        <!-- Table Card Open // -->\r\n" + 
					"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#FFFFFF;border-color:#E5E5E5; border-style:solid; border-width:0 1px 1px 1px;\" width=\"100%\" class=\"tableCard\">\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <!-- Header Top Border // -->\r\n" + 
					"                                                        <td height=\"3\" style=\"background-color:#003CE5;font-size:1px;line-height:3px;\" class=\"topBorder\" >&nbsp;</td>\r\n" + 
					"                                                </tr>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td align=\"center\" valign=\"top\" style=\"padding-bottom:20px\" class=\"imgHero\">\r\n" + 
					"                                                                <!-- Hero Image // -->\r\n" + 
					"                                                                <a href=\"#\" style=\"text-decoration:none;\">\r\n" + 
					"                                                                    <img src=\"http://weekly.grapestheme.com/notify/img/hero-img/blue/heroFill/user-welcome.png\" width=\"600\" alt=\"\" border=\"0\" style=\"width:70%; max-width:600px; height:auto; display:block;\" />\r\n" + 
					"                                                                 </a>\r\n" + 
					"                                                        </td>\r\n" + 
					"                                                </tr>\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td align=\"center\" valign=\"top\" style=\"padding-bottom:5px;padding-left:20px;padding-right:20px;\" class=\"mainTitle\">\r\n" + 
					"                                                                <!-- Main Title Text // -->\r\n" + 
					"                                                                <h2 class=\"text\" style=\"color:#000000; font-family:'Poppins', Helvetica, Arial, sans-serif; font-size:28px; font-weight:500; font-style:normal; letter-spacing:normal; line-height:36px; text-transform:none; text-align:center; padding:0; margin:0\">\r\n" + 
					"                                                                         Hi "+newPic.getNama()+", Anda telah terdaftar di Linov Support Ticketing sebagai <b>PIC</b>\r\n" + 
					"                                                                </h2>\r\n" + 
					"                                                        </td>\r\n" + 
					"                                                </tr>\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td align=\"center\" valign=\"top\" style=\"padding-bottom:30px;padding-left:20px;padding-right:20px;\" class=\"subTitle\">\r\n" + 
					"                                                                <!-- Sub Title Text // -->\r\n" + 
					"                                                                <h4 class=\"text\" style=\"color:#999999; font-family:'Poppins', Helvetica, Arial, sans-serif; font-size:16px; font-weight:500; font-style:normal; letter-spacing:normal; line-height:24px; text-transform:none; text-align:center; padding:0; margin:0\">\r\n" + 
					"                                                                        Gunakan informasi berikut untuk login \r\n" + 
					"                                                                        <br>\r\n" + 
					"                                                                        Username : "+user.getUsername()+"\r\n" + 
					"                                                                        <br>\r\n" + 
					"                                                                        Password : "+pic.getPassword()+"\r\n" + 
					"                                                                        <br>\r\n" + 
					"                                                                </h4>\r\n" + 
					"                                                        </td>\r\n" + 
					"                                        \r\n" + 
					"                                                                </table>\r\n" + 
					"\r\n" + 
					"                                                                \r\n" + 
					"\r\n" + 
					"                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"tableButton\">\r\n" + 
					"                                                                        <tr>\r\n" + 
					"                                                                                <td align=\"center\" valign=\"top\" style=\"padding-top:20px;padding-bottom:20px;\">\r\n" + 
					"\r\n" + 
//					"                                                                                        <!-- Button Table // -->\r\n" + 
//					"                                                                                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
//					"                                                                                                <tr>\r\n" + 
//					"                                                                                                        <td align=\"center\" class=\"ctaButton\" style=\"background-color:#003CE5;padding-top:12px;padding-bottom:12px;padding-left:35px;padding-right:35px;border-radius:50px\">\r\n" + 
//					"                                                                                                                <!-- Button Link // -->\r\n" + 
//					"                                                                                                                <a class=\"text\" href=\"#\" style=\"color:#FFFFFF; font-family:'Poppins', Helvetica, Arial, sans-serif; font-size:13px; font-weight:600; font-style:normal;letter-spacing:1px; line-height:20px; text-transform:uppercase; text-decoration:none; display:block\">\r\n" + 
//					"                                                                                                                        Lihat Ticket\r\n" + 
//					"                                                                                                                </a>\r\n" + 
//					"                                                                                                        </td>\r\n" + 
//					"                                                                                                </tr>\r\n" + 
//					"                                                                                        </table>\r\n" + 
					"\r\n" + 
					"                                                                                </td>\r\n" + 
					"                                                                        </tr>\r\n" + 
					"                                                                </table>\r\n" + 
					"\r\n" + 
					"                                                        </td>\r\n" + 
					"                                                </tr>\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td height=\"20\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\r\n" + 
					"                                                </tr>\r\n" + 
					"\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td height=\"20\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\r\n" + 
					"                                                </tr>\r\n" + 
					"                                        </table>\r\n" + 
					"                                        <!-- Table Card Close// -->\r\n" + 
					"\r\n" + 
					"                                        <!-- Space -->\r\n" + 
					"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"space\">\r\n" + 
					"                                                <tr>\r\n" + 
					"                                                        <td height=\"30\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\r\n" + 
					"                                                </tr>\r\n" + 
					"                                        </table>\r\n" + 
					"\r\n" + 
					"                                </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                </table>\r\n" + 
					"                <!-- Email Wrapper Body Close // -->\r\n" + 
					"\r\n" + 
					"                <!-- Email Wrapper Footer Open // -->\r\n" + 
					"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\" width=\"100%\" class=\"wrapperFooter\">\r\n" + 
					"                                   \r\n" + 
					"</body>\r\n" + 
					"</html>", "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();

//			   attachPart.attachFile("hewan.png");
//			   multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);

			
			msgs = "Berhasil menambahkan data PIC";
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
