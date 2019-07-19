/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
import com.sun.org.apache.bcel.internal.generic.AALOAD;

import LinovSupport.Ticketing.encrypt.BCrypt;
import LinovSupport.Ticketing.encrypt.RandomString;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Agen;
import LinovSupport.Ticketing.model.AllaccountInfo;
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

//	@GetMapping("")
//	public ResponseEntity<?> findAll() {
//		try {
//			List<AccountV2> accounts = accountV2Service.findAll();
////			List<AllaccountInfo> aai = new ArrayList<AllaccountInfo>();
//			AllaccountInfo aai = new AllaccountInfo();
//			for(AccountV2 account : accounts) {
//				AccountV2 account2 = new AccountV2();
//				account2.setIdAccount(account.getIdAccount());
//				
//				aai.setIdAccount(account.getIdAccount());
//				aai.setNamaAccount(account.getNama());
//				aai.setTeleponAccount(account.getTelepon());
//				aai.setAlamatAccount(account.getAlamat());
//				aai.setActive(account.isActive());
//				aai.setAccountPic(account.getPics());
//				aai.setAgenAccount(agenService.findByAccount(account));
//				aai.getAgenAccount().setAccount(account2);
//				aai.setGambarAccount(gambarService.findById(account.getIdGambar()));
//				for(PicV2 pic : aai.getAccountPic()) {
//					pic.setAccount(account2);
//				}
//			}
//			
//
//			return new ResponseEntity<>(aai, HttpStatus.OK);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
	
	@GetMapping("") 
	ResponseEntity<?> findAll(){
		try {
			List<AccountV2> accounts = accountV2Service.findAll();
			List<AllaccountInfo> ai = new ArrayList<AllaccountInfo>();
			
			for(AccountV2 account : accounts) {
				AllaccountInfo allacount = new AllaccountInfo();
				AccountV2 account2 = new AccountV2();
				account2.setIdAccount(account.getIdAccount());
				
				allacount.setIdAccount(account.getIdAccount());
				allacount.setNamaAccount(account.getNama());
				allacount.setTeleponAccount(account.getTelepon());
				allacount.setAlamatAccount(account.getAlamat());
				allacount.setActive(account.isActive());
				allacount.setAccountPic(account.getPics());
				allacount.setAgenAccount(agenService.findByAccount(account));
				allacount.getAgenAccount().setAccount(account2);
				allacount.setGambarAccount(gambarService.findById(account.getIdGambar()));
				
				for(PicV2 pic :allacount.getAccountPic()) {
					pic.setAccount(account2);
				}
				
				ai.add(allacount);
				
			}
			return new ResponseEntity<>(ai, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{idAccount}")
	public ResponseEntity<?> findById(@PathVariable String idAccount) {
//		AccountV2 account = accountV2Service.findById(idAccount);
//		AccountV2 acc1 = new AccountV2();
//		acc1.setIdAccount(account.getIdAccount());
////		acc1.setAgen(null);
//		Agen newAgent = agenService.findByAccount(account);
//
//		newAgent.setAccount(acc1);
//		if (newAgent.getIdAgen() == null) {
////			account.setAgen(null);
//		} else {
////			account.setAgen(newAgent);
//		}
//
////		account.setGambar(gambarService.findById(account.getIdGambar()));
//		AccountV2 acc = new AccountV2();
//		acc.setIdAccount(account.getIdAccount());
//		List<PicV2> pics = new ArrayList<PicV2>();
//		for (PicV2 picss : account.getPics()) {
//			picss.setAccount(acc);
//			pics.add(picss);
//		}
//		account.setPics(pics);
		return ResponseEntity.ok(accountV2Service.findById(idAccount));
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
//	@PostMapping("/param")
//	private ResponseEntity<?> insertAccountv2(@RequestParam("idAccount") String idaccount,
//			@RequestParam("nama") String nama, @RequestParam("telepon") String telepon,
//			@RequestParam("alamat") String alamat, @RequestParam("gambar") MultipartFile gambar,
//			@RequestParam("active") boolean active) {
//		String msg;
//		try {
//			AccountV2 account = new AccountV2();
//			account.setIdAccount(idaccount);
//			account.setNama(nama);
//			account.setTelepon(telepon);
//			account.setAlamat(alamat);
//			account.setActive(active);
//			accountV2Service.insertAccount(account);
//
//			Gambar fileGambar = new Gambar();
//			fileGambar.setGambar(gambar.getBytes());
//			gambarService.create(fileGambar);
//			msg = "success";
//			return ResponseEntity.ok(msg);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}

	@PostMapping("/params")
	private ResponseEntity<?> insertAccountv3(@RequestParam("account") String inputAccount,
			@RequestParam("gambar") MultipartFile inputGambar)
			throws AddressException, MessagingException, IOException {
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
			LocalDateTime now = LocalDateTime.now();
			String kodeGambar = accountV2.getNama()+now;
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
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pic.getEmail()));
				msg.setSubject("Pendaftaran Akun Linov Ticketing");
				msg.setContent("Gunakan Informasi ini untuk login : <br> email = " + pic.getEmail() + " password = " + pass, "text/html");
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
						"                                                                         Hi "+pic.getNama()+", Anda telah terdaftar di Linov Support Ticketing sebagai <b>PIC</b> \r\n" + 
						"                                                                </h2>\r\n" + 
						"                                                        </td>\r\n" + 
						"                                                </tr>\r\n" + 
						"\r\n" + 
						"                                                <tr>\r\n" + 
						"                                                        <td align=\"center\" valign=\"top\" style=\"padding-bottom:30px;padding-left:20px;padding-right:20px;\" class=\"subTitle\">\r\n" + 
						"                                                                <!-- Sub Title Text // -->\r\n" + 
						"                                                                <h4 class=\"text\" style=\"color:#999999; font-family:'Poppins', Helvetica, Arial, sans-serif; font-size:16px; font-weight:500; font-style:normal; letter-spacing:normal; line-height:24px; text-transform:none; text-align:center; padding:0; margin:0\">\r\n" + 
						"                                                                        Gunakan informasi berikut untuk Login\r\n" + 
						"                                                                        <br>\r\n" + 
						"                                                                        Username : "+user.getUsername()+"\r\n" + 
						"                                                                        <br>\r\n" + 
						"                                                                        Password : "+pass+"\r\n" + 
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
//						"                                                                                        <!-- Button Table // -->\r\n" + 
//						"                                                                                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
//						"                                                                                                <tr>\r\n" + 
//						"                                                                                                        <td align=\"center\" class=\"ctaButton\" style=\"background-color:#003CE5;padding-top:12px;padding-bottom:12px;padding-left:35px;padding-right:35px;border-radius:50px\">\r\n" + 
//						"                                                                                                                <!-- Button Link // -->\r\n" + 
//						"                                                                                                                <a class=\"text\" href=\"#\" style=\"color:#FFFFFF; font-family:'Poppins', Helvetica, Arial, sans-serif; font-size:13px; font-weight:600; font-style:normal;letter-spacing:1px; line-height:20px; text-transform:uppercase; text-decoration:none; display:block\">\r\n" + 
//						"                                                                                                                        Lihat Ticket\r\n" + 
//						"                                                                                                                </a>\r\n" + 
//						"                                                                                                        </td>\r\n" + 
//						"                                                                                                </tr>\r\n" + 
//						"                                                                                        </table>\r\n" + 
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

//				   attachPart.attachFile("hewan.png");
//				   multipart.addBodyPart(attachPart);
				msg.setContent(multipart);
				Transport.send(msg);
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
//			accountV2Service.findById(id).setAgen(null);
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
//			account.setAgen(null);
//			accountV2Service.findById(id).setAgen(null);
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
//			account.setAgen(null);
//			accountV2Service.findById(id).setAgen(null);
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
			accountV2Service.updateAccount(account);
			msg = "Status aktif berhasil diubah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
