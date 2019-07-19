/**
 * 
 */
package LinovSupport.Ticketing.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name="tbl_agent",uniqueConstraints = @UniqueConstraint(columnNames = { "id_account", "email" }))
public class Agen {
	
	@Id
	@Column(name = "id_agent")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name= "UUID", strategy ="org.hibernate.id.UUIDGenerator")
	private String idAgen;
	
	@OneToOne
	@JoinColumn(name="id_account",referencedColumnName="id_account" )
	private AccountV2 account;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="isactive")
	private boolean active;

	public String getIdAgen() {
		return idAgen;
	}

	public void setIdAgen(String idAgent) {
		this.idAgen = idAgent;
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
