/**
 * 
 */
package vo;

import java.io.Serializable;

/**
 * @author adhiraima
 *
 */
public class LcoDataVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long serialNumber;
	public String lcoName;
	public String lcoCode;
	public String address;
	public String jvCode;
	public String jvName;
	public String state;
	public String city;
	public String aopLocation;
	public String pincode;
	public String phone;
	public String parentCode;
	public String area;
	public Long dealerTypeId;
	public String oldAopLocation;
	public Long employeeId;
	public long agreementId;
	public long kycId;
}
