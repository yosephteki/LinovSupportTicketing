/**
 * 
 */
package LinovSupport.Ticketing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
//import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name = "tbl_account")
public class Account {
	
	public Account() {
		idAccount = "";
		nama="";
		telepon="";
		alamat="";
	}
	
	@Id
	@Column(name = "id_account")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idAccount;	

	@Column(name = "nama_pt")
	private String nama;

	@Column(name = "telepon_pt")
	private String telepon;

	@Column(name = "alamat_pt")
	private String alamat;
	
//	@OneToMany(mappedBy = "idAccount",targetEntity = Pic.class,fetch = FetchType.LAZY,
//			cascade = CascadeType.REMOVE)
//	private List<Pic> pic;

//	public List<Pic> getPic() {
//		return pic;
//	}
//
//	public void setPic(List<Pic> pic) {
//		this.pic = pic;
//	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getIdGambar() {
		return idGambar;
	}

	public void setIdGambar(String idGambar) {
		this.idGambar = idGambar;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "id_gambar")
	private String idGambar;
	
	@Column(name = "active")
	private boolean active;


}
