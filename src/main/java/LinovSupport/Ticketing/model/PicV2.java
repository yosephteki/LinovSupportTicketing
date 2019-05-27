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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name = "tbl_pic")
public class PicV2 {

	public PicV2() {
		idPic = "";
		account = new AccountV2();
		nama = "";
		email = "";
	}
	
	@Id
	@Column(name = "id_pic")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idPic;
	
	@ManyToOne
	@JoinColumn(name="id_account",referencedColumnName="id_account")
	private AccountV2 account;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nama")
	private String nama;

	@Column(name = "isactive")
	private boolean active;

	public String getIdPic() {
		return idPic;
	}

	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}

	public AccountV2 getAccount() {
		return account;
	}

	public void setAccount(AccountV2 account) {
		this.account = account;
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
