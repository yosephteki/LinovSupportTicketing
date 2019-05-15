/**
 * 
 */
package LinovSupport.Ticketing.model;

/**
 * @author Yosep Teki
 *
 */
public class Multi {
	private String username;
	private String password;
	private String idRole;
	private String id;
	private Account idAcc;
//	private String idAcc;
	private String email;
	private String nama;
	private boolean active;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Account getIdAcc() {
		return idAcc;
	}
	public void setIdAcc(Account idAcc) {
		this.idAcc = idAcc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	

}
