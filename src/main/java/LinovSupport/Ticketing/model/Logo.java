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
@Table(name="logo")
public class Logo {

	@Id
	@Column(name = "id_logo")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name= "UUID", strategy ="org.hibernate.id.UUIDGenerator")
	private String id_logo;
	
	@Column(name="logo")
	private byte[] logo;

	public String getId_logo() {
		return id_logo;
	}

	public void setId_logo(String id_logo) {
		this.id_logo = id_logo;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	
}
