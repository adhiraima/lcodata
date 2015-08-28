/**
 * 
 */
package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name="jv")
public class JV extends Model {

	@Id
	@Column(name="jv_code")
	private String jvCode;
	
	@Column(name="jv_name")
	private String jvName;
	
	@Column(name="state")
	private Long stateId;
	
	@Column(name="city")
	private Long cityId;
	
	@SuppressWarnings({"deprecation" })
	private static Finder<String, JV> find = new Finder<String, JV>(
			String.class, JV.class);
	
	public static List<JV> findAll() {
		return find.all();
	}
	
	public static JV findByCityAndState (Long stateId, Long cityId) {
		return find.where().eq("stateId", stateId).eq("cityId", cityId).findUnique();
	}
	
	public static JV findByCJVCode(String jvCode) {
		return find.where().eq("jvCode", jvCode).findUnique();
	}

	public String getJvCode() {
		return jvCode;
	}

	public void setJvCode(String jvCode) {
		this.jvCode = jvCode;
	}

	public String getJvName() {
		return jvName;
	}

	public void setJvName(String jvName) {
		this.jvName = jvName;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
}
