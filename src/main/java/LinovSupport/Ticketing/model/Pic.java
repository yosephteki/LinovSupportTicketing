/**
 * 
 */
package LinovSupport.Ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name = "tbl_pic")
public class Pic {
	
	public Pic() {
		idPic = "";
		idAccount = new Account();
		nama = "";
		email = "";
//		account  = new Account();
		
	}

	@Id
	@Column(name = "id_pic")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idPic;

	@OneToOne
	@JoinColumn(name = "id_account")
	private Account idAccount;

	@Column(name = "nama")
	private String nama;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private boolean active;
	
//	@ManyToOne
//	@JoinColumn(name = "id_account",referencedColumnName = "id_account")
//	private Account idAccount;
//	
//	public Account getAccount() {
//		return idAccount;
//	}
//
//	public void setAccount(Account idAccount) {
//		this.idAccount = idAccount;
//	}

	public String getIdPic() {
		return idPic;
	}

	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}

	public Account getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Account idAccount) {
		this.idAccount = idAccount;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
