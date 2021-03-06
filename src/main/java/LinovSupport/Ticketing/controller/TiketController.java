/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	@GetMapping("/filter/{pic}/{level}")
	public ResponseEntity<?> findByFilter(@PathVariable String pic,@PathVariable Level level) {
		PicV2 pic1 = new PicV2();
		if (!pic.trim().isEmpty()) {
			pic1 = picService.findById(pic);
		}else {
			pic1 = null;
		}
		List<Tiket> tikets = tiketService.findByFilter(pic1,level);
		for(Tiket tiket : tikets) {
			tiket.getIdPic().getAccount().setPics(null);
			tiket.getIdPic().getAccount().setAgen(null);
			for(DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(null);
			}
		}
		return ResponseEntity.ok(tikets);
	}

	@GetMapping("/level/{level}")
	public ResponseEntity<?> findByLevel(@PathVariable Level level) {
		List<Tiket> tikets = tiketService.findByLevel(level);
		for(Tiket tiket : tikets) {
			tiket.getIdPic().getAccount().setPics(null);
			tiket.getIdPic().getAccount().setAgen(null);
			for(DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(null);
			}
		}
			
		
		
		return ResponseEntity.ok(tikets);
	}

	@PostMapping("")
	public ResponseEntity<?> insertTiket(@RequestBody Tiket tiket) throws ErrorException {
		String msg = null;
		try {
			tiketService.insertTiket(tiket);
			Tiket tiket2 = tiketService.findByBk(tiket.getJudulTiket(), tiket.getIdPic());
			for (DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(tiket2);  
				LocalDateTime now = LocalDateTime.now();  
				dtl.setWaktu(now);
				tiketService.insertDetail(dtl);
			}
			msg = "Data Tiket berhasil ditambah";
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
	public ResponseEntity<?> updateDetailTiket(@PathVariable("")String idTiket,@RequestBody DetailTiket detail){
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
