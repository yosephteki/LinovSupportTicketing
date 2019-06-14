/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.encrypt.BCrypt;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.Admin;
import LinovSupport.Ticketing.model.Agen;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.User;
import LinovSupport.Ticketing.model.masterUser;
import LinovSupport.Ticketing.service.AccountV2Service;
import LinovSupport.Ticketing.service.AdminService;
import LinovSupport.Ticketing.service.AgenService;
import LinovSupport.Ticketing.service.PicV2Service;
import LinovSupport.Ticketing.service.RoleService;
import LinovSupport.Ticketing.service.UserService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	BCrypt bc = new BCrypt();
	
	@Autowired
	private AccountV2Service accountService;
	
	@Autowired
	private PicV2Service picService;
	
	@Autowired
	private AgenService agenService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleservice;

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("idUser/{idUser}")
	public ResponseEntity<?> findById(@PathVariable String idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}

	@GetMapping("username/{username}")
	public ResponseEntity<?> findByBk(@PathVariable String username) {
		return ResponseEntity.ok(userService.findByBk(username));
	}

	@GetMapping("user/{username}")
	public ResponseEntity<?> findByFilter(@PathVariable String username) {
		return ResponseEntity.ok(userService.findByFilter(username));
	}
	@GetMapping("/{username}/{password}")
	public ResponseEntity<?> login(@PathVariable String username,@PathVariable String password){
		try {
			User user = userService.findByBk(username);
			if(bc.checkpw(password,user.getPassword())) {
				masterUser mUser = new masterUser();
				mUser.setIdUser(user.getIdUser());
				mUser.setUsercode(user.getDetailRole());
				mUser.setUsername(user.getUsername());
				mUser.setPassword(user.getPassword());
				mUser.setRole(roleservice.findById(user.getIdRole()).getNama());
				
				PicV2 pic = picService.findById(mUser.getUsercode());
				Agen agen = agenService.findById(mUser.getUsercode());
				Admin admin = adminService.findById(mUser.getUsercode());
				
				System.out.println(pic.getIdPic());
				System.out.println(agen.getIdAgen());
				System.out.println(admin.getIdAdmin());
				if (pic.getIdPic().matches(mUser.getUsercode())) {
					mUser.setNama(pic.getNama());
					mUser.setEmail(pic.getEmail());
					mUser.setStatus(pic.isActive());
					mUser.setAccount(pic.getAccount());
					mUser.getAccount().setPics(null);
					AccountV2 account = accountService.findById(pic.getAccount().getIdAccount());
					mUser.setAgen(agenService.findByAccount(account));
					
				}
				if(agen.getIdAgen() != null && agen.getIdAgen().matches(mUser.getUsercode())) {
					mUser.setNama(agen.getNama());
					mUser.setEmail(agen.getEmail());
					mUser.setStatus(agen.isActive());
					mUser.setAccount(agen.getAccount());
					mUser.getAccount().setPics(null);
					
				}
				if(admin.getIdAdmin() != null && admin.getIdAdmin().matches(mUser.getUsercode())) {
					mUser.setNama(admin.getNama());
					mUser.setEmail(admin.getEmail());
					mUser.setStatus(admin.isActive());
				}
				 
				return ResponseEntity.ok(mUser);
			}else {
				return ResponseEntity.ok("Username dan Password tidak cocok / user tidak ada");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}
}
