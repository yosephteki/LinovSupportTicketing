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
@Table(name="tbl_role")
public class Role {
	
	
	@Id
	@Column(name = "id_role")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idRole;
	
	@Column(name="nama_role")
	private String nama;
	
	@Column(name="kode_role")
	private String kodeRole;

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKodeRole() {
		return kodeRole;
	}

	public void setKodeRole(String kodeRole) {
		this.kodeRole = kodeRole;
	}

	
	
	

}
