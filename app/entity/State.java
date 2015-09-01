/**
 * 
 */
package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import vo.StateVO;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name="state")
public class State extends Model {

	@Id
	@Column(name="state_id")
	private Long stateId;
	
	@Column(name="state_name")
	private String name;
	
	@SuppressWarnings({"deprecation" })
	private static Finder<Long, State> find = new Finder<Long, State>(
			Long.class, State.class);
	
	public static List<State> findAllStates() {
		return find.all();
	}
	
	public static State findById(Long stateId) {
		return find.byId(stateId);
	}
   
	public static State findByName(String stateName) {
		return find.where().eq("name", stateName).findUnique();
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public StateVO getVO() {
		StateVO vo = new StateVO();
		vo.stateId = this.stateId;
		vo.name = this.name;
		
		return vo;
	}
}
