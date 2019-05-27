/**
 * 
 */
package LinovSupport.Ticketing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

import LinovSupport.Ticketing.enumeration.Level;
import LinovSupport.Ticketing.enumeration.Status;

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

	@OneToOne
	@JoinColumn(name = "id_pic", referencedColumnName = "id_pic")
	private PicV2 idPic;

	@Enumerated(EnumType.STRING)
	@Column(name = "level_ticket")
	private Level level;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@OneToMany(mappedBy = "idTiket", targetEntity = DetailTiket.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DetailTiket> detailTiket;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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

	public PicV2 getIdPic() {
		return idPic;
	}

	public void setIdPic(PicV2 idPic) {
		this.idPic = idPic;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<DetailTiket> getDetailTiket() {
		return detailTiket;
	}

	public void setDetailTiket(List<DetailTiket> detailTiket) {
		this.detailTiket = detailTiket;
	}
}
