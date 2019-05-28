package LinovSupport.Ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_admin",uniqueConstraints = @UniqueConstraint(columnNames = {  "email" } ))
public class Admin {

	@Id
	@Column(name = "id_admin")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name= "UUID", strategy ="org.hibernate.id.UUIDGenerator")
	private String idAdmin;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="email")
	private String email;
	
	@Column(name="isactive")
	private boolean active;

	public String getIdAdmin() {
		return idAdmin;
	}

	public String getNama() {
		return nama;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActive() {
		return active;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
