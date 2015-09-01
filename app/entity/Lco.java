/**
 * 
 */
package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import vo.LcoVO;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name="lco")
public class Lco extends Model {

	@Column(name = "lco_name")
	private String lcoName;

	@Id
	@Column(name = "lco_code")
	private String lcoCode;

	@Column(name = "address")
	private String address;
	
	@Column(name="contact")
	private String contactName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="jv_code")
	private String jvCode;
	
	@Column(name = "state")
	private Long state;

	@Column(name = "city")
	private Long city;
	
	@Column(name = "employee_id")
	private Long employeeId;
	
	@SuppressWarnings({"deprecation" })
	private static Finder<String, Lco> find = new Finder<String, Lco>(
			String.class, Lco.class);
	
	public static List<Lco> finAll() {
		return find.all();
	}
	
	public static List<Lco> findAllByName(String lcoName) {
		return find.where().eq("lcoName", lcoName).findList();
	}
	
	public static Lco findById(String lcoCode) {
		return find.byId(lcoCode);
	}
	
	public static List<Lco> findAllByEmployee(String employeeId) {
		return find.where().eq("employeeId", employeeId).findList();
	}
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getLcoName() {
		return lcoName;
	}

	public void setLcoName(String lcoName) {
		this.lcoName = lcoName;
	}

	public String getLcoCode() {
		return lcoCode;
	}

	public void setLcoCode(String lcoCode) {
		this.lcoCode = lcoCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getJvCode() {
		return jvCode;
	}

	public void setJvCode(String jvCode) {
		this.jvCode = jvCode;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LcoVO getVO() {
		LcoVO vo = new LcoVO();
		vo.address = this.address;
		vo.city = this.city.toString();
		vo.state = this.state.toString();
		vo.contactName = this.contactName;
		JV jv = JV.findByCJVCode(this.getJvCode());
		if (null != jv) {
			vo.jvCode = jv.getJvCode();
			vo.jvName = jv.getJvName();
		} else {
			vo.jvCode = StringUtils.EMPTY;
			vo.jvName = StringUtils.EMPTY;
		}
		vo.lcoCode  = this.lcoCode;
		vo.lcoName = Lco.findById(this.lcoCode).getLcoName();
		vo.phone = this.phone;
		
		return vo;
	}
}
