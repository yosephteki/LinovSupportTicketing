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

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name = "tbl_agent")
public class Agent {

	@Id
	@Column(name = "id_agent")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name= "UUID", strategy ="org.hibernate.id.UUIDGenerator")
	private String idAgent;

	@OneToOne
	@JoinColumn(name = "id_account",referencedColumnName = "id_account")
	private Account account;

	@Column(name = "nama")
	private String nama;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private boolean active;

	public String getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(String idAgent) {
		this.idAgent = idAgent;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
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
