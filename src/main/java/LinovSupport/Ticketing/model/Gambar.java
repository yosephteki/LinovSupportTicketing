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
@Table(name = "tbl_gambar")
public class Gambar {

	@Id
	@Column(name = "id_gambar")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idGambar;

	@Column(name ="kode_gambar")
	private String kodeGambar;
	
	@Column(name = "gambar")
	private byte[] gambar;
	

	public String getKodeGambar() {
		return kodeGambar;
	}

	public void setKodeGambar(String kodeGambar) {
		this.kodeGambar = kodeGambar;
	}

	public String getIdGambar() {
		return idGambar;
	}

	public void setIdGambar(String idGambar) {
		this.idGambar = idGambar;
	}

	public byte[] getGambar() {
		return gambar;
	}

	public void setGambar(byte[] gambar) {
		this.gambar = gambar;
	}
}
