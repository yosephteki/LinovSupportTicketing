/**
 * 
 */
package LinovSupport.Ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LinovSupport.Ticketing.dao.AgentDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Agent;

/**
 * @author Yosep Teki
 *
 */
@Service
public class AgentService {
	
	@Autowired
	private AgentDao agentDao;
	
	public Agent findById(String id) {
		return agentDao.findById(id);
	}
	
	public Agent findByBK(String bk1,String bk2) {
		return agentDao.findByBk(bk1, bk2);
	}
	
	public void insertAgent(Agent agent) throws ErrorException{
		if (agentDao.isIDExist(agent.getIdAgent())) {
			throw new ErrorException("id sudah ada");
		}
		if (agentDao.isBkExist(agent.getAccount().getIdAccount(),agent.getEmail())) {
			throw new ErrorException("User sudah ada!");
		}
		if (agent.getAccount().equals(null)) {
			throw new ErrorException("id account tidak boleh kosong");
		}
		if (agent.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		if (agent.getEmail().isEmpty()) {
			throw new ErrorException("email tidak boleh kosong");
		}
		if (agent.isActive() == false) {
			throw new ErrorException("status active tidak boleh kosong");
		}
		agentDao.create(agent);
	}
	
	public void update(Agent agent) throws ErrorException{
		if (!agentDao.isIDExist(agent.getIdAgent())) {
			throw new ErrorException("ID Agent tidak ditemukan!");
		}
		if (!agentDao.isBkExist(agent.getAccount().getIdAccount(),agent.getEmail())) {
			throw new ErrorException("User tidak dtemukan!");
		}
		if (agent.getNama().isEmpty()) {
			throw new ErrorException("Nama tidak boleh kosong!");
		}
		if (agent.getEmail().isEmpty()) {
			throw new ErrorException("Email tidak boleh kosong!");
		}
		agentDao.create(agent);
	}
	public void delete(String id) throws ErrorException{
		if (agentDao.isIDExist(id)) {
			agentDao.delete(id);
		}else {
			throw new ErrorException("Agent tidak ditemukan!");
		}
	}
}
