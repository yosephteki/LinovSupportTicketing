package LinovSupport.Ticketing.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import LinovSupport.Ticketing.dao.AdminDao;
import LinovSupport.Ticketing.exception.ErrorException;
import LinovSupport.Ticketing.model.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public Admin findById(String id) {
		return adminDao.findById(id);
	}
	
	public Admin findByBk(String email) {
		return adminDao.findByBk(email);
	}
	
	public void insertAdmin(Admin admin) throws ErrorException {
		if(adminDao.isIdExist(admin.getIdAdmin())) {
			throw new ErrorException("Id Sudah digunakan");
		}
		if(adminDao.isBkExist(admin.getEmail())) {
			throw new ErrorException("email sudah di gunakan");
		}
		if(admin.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		if(admin.getEmail().isEmpty()){
			throw new ErrorException("email tidak boleh kosong");
		}
		adminDao.insertAdmin(admin);
	}
	public void updateAdmin(Admin admin)throws ErrorException{
		if(!adminDao.isIdExist(admin.getIdAdmin())) {
			throw new ErrorException("id admin tidak di temukan");
		}
		
		if(!adminDao.isBkExist(admin.getEmail())) {
			throw new ErrorException("email tidak di temukan");
		}
		if(admin.getNama().isEmpty()) {
			throw new ErrorException("nama tidak boleh kosong");
		}
		if(admin.getEmail().isEmpty()){
			throw new ErrorException("email tidak boleh kosong");
		}
		adminDao.updateAdmin(admin);
	}
	public void deleteAdmin(String id)throws ErrorException{
		if (adminDao.isIdExist(id)) {
			adminDao.deleteAdmin(id);
		}else {
			throw new ErrorException("Admin tidak di temukan");
		}
	}
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

}
