/**
 * 
 */
package LinovSupport.Ticketing.model;
import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name="tbl_detail_ticket")
public class DetailTiket {
	
	@Id
	@Column(name = "id_detail_ticket")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idDetailTiket;
	
	@ManyToOne
	@JoinColumn(name="id_ticket",referencedColumnName="id_ticket")
	private Tiket idTiket;
	
	@Column(name="jam")
	private LocalDateTime waktu;
	
	@Column(name="id_pengirim")
	private String pengirim;
	
	@Column(name="id_penerima")
	private String penerima;
	
	@Column(name="pesan")
	private String pesan;
	
	@Column(name="screenshot")
	private String idGambar;
	
	@Column(name="status")
	private String status;

	public String getIdDetailTiket() {
		return idDetailTiket;
	}

	public void setIdDetailTiket(String idDetailTiket) {
		this.idDetailTiket = idDetailTiket;
	}

	public Tiket getIdTiket() {
		return idTiket;
	}

	public void setIdTiket(Tiket idTiket) {
		this.idTiket = idTiket;
	}

	

	public LocalDateTime getWaktu() {
		return waktu;
	}

	public void setWaktu(LocalDateTime waktu) {
		this.waktu = waktu;
	}

	public String getPengirim() {
		return pengirim;
	}

	public void setPengirim(String pengirim) {
		this.pengirim = pengirim;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	public String getIdGambar() {
		return idGambar;
	}

	public void setIdGambar(String idGambar) {
		this.idGambar = idGambar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPenerima() {
		return penerima;
	}

	public void setPenerima(String penerima) {
		this.penerima = penerima;
	}

}
