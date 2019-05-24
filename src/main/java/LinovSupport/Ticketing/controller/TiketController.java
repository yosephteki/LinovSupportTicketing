/**
 * 
 */
package LinovSupport.Ticketing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.AccountV2;
import LinovSupport.Ticketing.model.DetailTiket;
import LinovSupport.Ticketing.model.PicV2;
import LinovSupport.Ticketing.model.Tiket;
import LinovSupport.Ticketing.service.TiketService;

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
	
	@GetMapping("/{idTiket}")
	public ResponseEntity<?> findById(@PathVariable String idTiket){
	Tiket tiket = tiketService.findById(idTiket);
	Tiket tics = new Tiket();
	tics.setIdTiket(tiket.getIdTiket());
	
//	List<tiket>
	return ResponseEntity.ok(tiketService.findById(idTiket));
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll(){
		try {
			List<Tiket> tiket = tiketService.findAll();
			
			for(Tiket tics : tiket) {
				Tiket newTiket = new Tiket();
				PicV2 newPic = new PicV2();
				String idpic = tics.getIdPic().getIdPic();
				AccountV2 acc = tics.getIdPic().getAccount();
				acc.setPics(null);
				newPic.setIdPic(idpic);
				newPic.setAccount(acc);
				List<DetailTiket> detail = new ArrayList<DetailTiket>();
				tics.setIdPic(newPic);
				for(DetailTiket det : tics.getDetailTiket()) {
					det.setIdTiket(newTiket);
					detail.add(det);
				}
			}
			return new ResponseEntity<>(tiket,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/judul/{judul}/pic/{pic}/level/{level}")
	public ResponseEntity<?> findByFilter(@PathVariable String judul, 
			@PathVariable String pic,@PathVariable Level level){
		return ResponseEntity.ok(tiketService.findByFilter(judul, pic, level));
	}
	
	@GetMapping("/level/{level}")
	public ResponseEntity<?> findByLevel(@PathVariable Level level){
		return ResponseEntity.ok(tiketService.findByLevel(level));
	}
	
	@PostMapping("")
	public ResponseEntity<?> insertTiket(@RequestBody Tiket tiket) throws ErrorException {
		String msg;
		try {
			tiketService.insertTiket(tiket);
			Tiket tiket2 = tiketService.findByBk(tiket.getJudulTiket(), tiket.getIdPic().getIdPic());
			for(DetailTiket dtl : tiket.getDetailTiket()) {
				dtl.setIdTiket(tiket2);
				tiketService.insertDetail(dtl);
			}
			msg= "Data Tiket berhasil ditambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateTiket(@RequestBody Tiket tiket) throws ErrorException{
		String msg;
		try {
			tiketService.updateTiket(tiket);
			msg="success";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	
	

}
