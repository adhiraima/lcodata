/**
 * 
 */
package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import vo.LcoDataVO;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name = "LCOMaster")
public class LcoData extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_num")
	private Long serialNumber;

	@Column(name="lco_id")
	private String lcoCode;
	
	@Column(name = "aop_location")
	private String aopLocation;

	@Column(name = "pin_code")
	private String pincode;

	@Column(name = "parent_code")
	private String parentCode;

	@Column(name = "area")
	private String area;

	@Column(name = "dealer_type")
	private Long dealerTypeId;

	@Column(name = "old_aop")
	private String oldAopLocation;

	@Column(name = "agreement")
	private long agreementId;

	@Column(name = "kycId")
	private long kycId;

	@SuppressWarnings({"deprecation" })
	private static Finder<Long, LcoData> find = new Finder<Long, LcoData>(
			Long.class, LcoData.class);

	public static List<LcoData> getAll() {
		return find.all();
	}
	
	public static LcoData findById(Long serialNum) {
		return find.byId(serialNum);
	}
	
	public static List<LcoData> findByLcoCodes(List<Lco> lcos) {
		List<String> lcocodes = new ArrayList<String>();
		for (Lco lco : lcos) {
			lcocodes.add(lco.getLcoCode());
		}
		return find.where().in("lcoCode", lcocodes).findList();
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLcoCode() {
		return lcoCode;
	}

	public void setLcoCode(String lcoCode) {
		this.lcoCode = lcoCode;
	}

	public String getAopLocation() {
		return aopLocation;
	}

	public void setAopLocation(String aopLocation) {
		this.aopLocation = aopLocation;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getDealerTypeId() {
		return dealerTypeId;
	}

	public void setDealerTypeId(Long dealerTypeId) {
		this.dealerTypeId = dealerTypeId;
	}

	public String getOldAopLocation() {
		return oldAopLocation;
	}

	public void setOldAopLocation(String oldAopLocation) {
		this.oldAopLocation = oldAopLocation;
	}

	public long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(long agreementId) {
		this.agreementId = agreementId;
	}

	public long getKycId() {
		return kycId;
	}

	public void setKycId(long kycId) {
		this.kycId = kycId;
	}

	public LcoDataVO getVO() {
		LcoDataVO vo = new LcoDataVO();
		vo.serialNumber = this.serialNumber;
		vo.area = this.area;
		vo.aopLocation = this.aopLocation;
		Lco lco = Lco.findById(this.lcoCode);
		if (null != lco) {
			vo.lcoCode = lco.getLcoCode();
			vo.lcoName = lco.getLcoName();
			vo.phone = lco.getPhone();
			vo.state = State.findById(lco.getState()).getName();
			vo.city = City.findById(lco.getCity()).getCityName();
			vo.employeeId = lco.getEmployeeId();
		}
		vo.oldAopLocation = this.oldAopLocation;
		vo.pincode = this.pincode;
		JV jv = JV.findByCJVCode(lco.getJvCode());
		if (null != jv) {
			vo.jvCode = jv.getJvCode();
			vo.jvName = jv.getJvName();
		} else {
			vo.jvCode = StringUtils.EMPTY;
			vo.jvName = StringUtils.EMPTY;
		}
		
		vo.dealerTypeId = this.getDealerTypeId();
		vo.kycId = this.kycId;
		vo.agreementId = this.agreementId;
		
		return vo;
	}
}
