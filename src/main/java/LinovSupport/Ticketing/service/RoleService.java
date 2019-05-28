/**
 * 
 */
package LinovSupport.Ticketing.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import LinovSupport.Ticketing.dao.RoleDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Role;


/**
 * @author Yosep Teki
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Role findById(String id) {
		return roleDao.findById(id);
	}
	public Role findByBk(String kodeRole) {
		return roleDao.findByBk(kodeRole);
	}
	
	public void insertRole(Role role)throws ErrorException {
		if(roleDao.isIdExist(role.getIdRole())) {
			throw new ErrorException("id sudah digunakan");
		}
		
		if(roleDao.isBkExist(role.getKodeRole())) {
			throw new ErrorException("Kode role sudah digunakan");
		}
		if(role.getKodeRole().isEmpty()) {
			throw new ErrorException("kode tidakboleh kosong");
		}
		if(role.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		roleDao.insertRole(role);
	}
	public void updateRole(Role role) throws ErrorException{
		if(!roleDao.isIdExist(role.getIdRole())) {
			throw new ErrorException("id role tidak ditemukan");
		}
		if(!roleDao.isBkExist(role.getKodeRole())) {
			throw new ErrorException("kode Role tidak ditemukan");
		}
		if(role.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		roleDao.updateRole(role);
	}
	
	public void deleteRole(String id)throws ErrorException{
		roleDao.deleteRole(id);
	}
	public List<Role> findAllRole(){
		return roleDao.findAllRole();
	}
	public List<Role>findByFilter(String nama){
		List<Role> role = roleDao.findByFilter(nama);
		return role;
	}
}
