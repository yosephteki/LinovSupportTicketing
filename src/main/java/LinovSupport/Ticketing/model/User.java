/**
 * 
 */
package LinovSupport.Ticketing.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name="tbl_user")
public class User {
	
	@Id
	@Column(name = "id_user")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idUser;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password_user")
	private String password;
	
	@Column(name="id_role")
	private String idRole;
	
	@Column(name="detail_role")
	private String detailRole;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getDetailRole() {
		return detailRole;
	}

	public void setDetailRole(String detailRole) {
		this.detailRole = detailRole;
	}
	
	
	

}
