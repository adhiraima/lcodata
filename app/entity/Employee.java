/**
 * 
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import vo.EmployeeVO;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name="employee")
public class Employee extends Model {

	@Id
	@Column(name="employee_id")
	private String employeeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email", unique=true)
	private String email;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@SuppressWarnings({"deprecation" })
	private static Finder<String, Employee> find = new Finder<String, Employee>(
			String.class, Employee.class);
   
	public static Employee findEmployeeeById(String employeeId) {
		return find.byId(employeeId);
	}
	
	public static Employee findEmployeeByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}
	
	public EmployeeVO getVO() {
		EmployeeVO vo = new EmployeeVO();
		vo.email = this.email;
		vo.employeeId = this.employeeId;
		vo.name = this.name;
		
		return vo;
	}
}
