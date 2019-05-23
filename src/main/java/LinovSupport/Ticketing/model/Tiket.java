/**
 * 
 */
package LinovSupport.Ticketing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

import com.progamer.model.DetailTransaksiPembelian;

/**
 * @author Yosep Teki
 *
 */
@Entity
@Table(name = "tbl_ticket", uniqueConstraints = @UniqueConstraint(columnNames = { "judul_ticket", "id_pic" }))
public class Tiket {

	@Id
	@Column(name = "id_ticket")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idTiket;

	@Column(name = "judul_ticket")
	private String judulTiket;

	@Column(name = "id_pic")
	private String idPic;

	@Column(name = "level_ticket")
	private String lvlTiket;

	@OneToMany(mappedBy = "idTiket", targetEntity = DetailTiket.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DetailTiket> detailTiket;

	public String getIdTiket() {
		return idTiket;
	}

	public void setIdTiket(String idTiket) {
		this.idTiket = idTiket;
	}

	public String getJudulTiket() {
		return judulTiket;
	}

	public void setJudulTiket(String judulTiket) {
		this.judulTiket = judulTiket;
	}

	public String getIdPic() {
		return idPic;
	}

	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}

	public String getLvlTiket() {
		return lvlTiket;
	}

	public void setLvlTiket(String lvlTiket) {
		this.lvlTiket = lvlTiket;
	}

	public List<DetailTiket> getDetailTiket() {
		return detailTiket;
	}

	public void setDetailTiket(List<DetailTiket> detailTiket) {
		this.detailTiket = detailTiket;
	}
}
