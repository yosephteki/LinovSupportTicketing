/**
 * 
 */
package LinovSupport.Ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Agent;
import LinovSupport.Ticketing.model.Pic;
import LinovSupport.Ticketing.service.AgentService;
import LinovSupport.Ticketing.service.PicService;

/**
 * @author Yosep Teki
 *
 */
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentService agentService;
	
	@PostMapping("")
	public ResponseEntity<?> insertAgent(@RequestBody Agent agent) throws ErrorException{
		String msg;
		try {
			agentService.insertAgent(agent);
			msg="Data berhasil di tambah";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
//	buat versi 2 create agen kombinasi user
	public ResponseEntity<?> update(@RequestBody Agent agent) throws ErrorException{
		String msg;
		try {
			agentService.update(agent);
			msg="success updating agent";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		String msg;
		try {
			agentService.delete(id);
			msg="success deleting agent";
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
