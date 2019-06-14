/**
 * 
 */
package LinovSupport.Ticketing.model;

/**
 * @author Yosep Teki
 *
 */
public class masterUser {
	private String idUser;
	private String username;
	private String password;
	private String role;
	private String nama;
	private String email;
	private AccountV2 account;
	private PicV2 pic;
	private Agen agen;
	private String usercode;
	private boolean status;
	
	public AccountV2 getAccount() {
		return account;
	}
	public void setAccount(AccountV2 account) {
		this.account = account;
	}
	public PicV2 getPic() {
		return pic;
	}
	public void setPic(PicV2 pic) {
		this.pic = pic;
	}
	public Agen getAgen() {
		return agen;
	}
	public void setAgen(Agen agen) {
		this.agen = agen;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}
