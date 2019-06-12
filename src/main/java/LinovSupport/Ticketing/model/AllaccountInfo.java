/**
 * 
 */
package LinovSupport.Ticketing.model;

import java.util.List;

/**
 * @author Yosep Teki
 *
 */
public class AllaccountInfo {
	private String idAccount;
	private String namaAccount;
	private String teleponAccount;
	private String alamatAccount;
	private boolean active;
	private Agen agenAccount;
	private List<PicV2> accountPic;
	private Gambar gambarAccount;
	
	public Agen getAgenAccount() {
		return agenAccount;
	}
	public void setAgenAccount(Agen agenAccount) {
		this.agenAccount = agenAccount;
	}
	public Gambar getGambarAccount() {
		return gambarAccount;
	}
	public void setGambarAccount(Gambar gambarAccount) {
		this.gambarAccount = gambarAccount;
	}
	public String getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}
	public String getNamaAccount() {
		return namaAccount;
	}
	public void setNamaAccount(String namaAccount) {
		this.namaAccount = namaAccount;
	}
	public String getTeleponAccount() {
		return teleponAccount;
	}
	public void setTeleponAccount(String teleponAccount) {
		this.teleponAccount = teleponAccount;
	}
	public String getAlamatAccount() {
		return alamatAccount;
	}
	public void setAlamatAccount(String alamatAccount) {
		this.alamatAccount = alamatAccount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<PicV2> getAccountPic() {
		return accountPic;
	}
	public void setAccountPic(List<PicV2> accountPic) {
		this.accountPic = accountPic;
	}
	
	
	
	

}
