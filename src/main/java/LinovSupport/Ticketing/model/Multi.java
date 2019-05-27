/**
 * 
 */
package LinovSupport.Ticketing.model;

import javax.persistence.Entity;

/**
 * @author Yosep Teki
 *
 */

public class Multi {
	private String username;
	private String password;
	private String role;
	private String id;
	private AccountV2 account;
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


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccountV2 getAccount() {
		return account;
	}

	public void setAccount(AccountV2 account) {
		this.account = account;
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
