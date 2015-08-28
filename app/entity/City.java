/**
 * 
 */
package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import vo.CityVO;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity
@Table(name="city")
public class City extends Model {

	@Id
	@Column(name="city_id")
	private Long cityId;
	
	@Column(name="state_id")
	private Long state;
	
	@Column(name="city_name")
	private String cityName;
	
	@SuppressWarnings({"deprecation" })
	private static Finder<Long, City> find = new Finder<Long, City>(
			Long.class, City.class);

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public List<City> findAll() {
		return find.all();
	}
	
	public static List<City> findByState(Long stateId) {
		State state = State.findById(stateId);
		if (null != state && null != state.getName() 
				&& state.getName().trim().length() > 0) {
			return find.where().eq("state", state.getStateId()).findList();
		}
		return null;
	}
	
	public static City findById(Long cityId) {
		return find.byId(cityId);
	}
	
	public CityVO getVO() {
		CityVO vo = new CityVO();
		vo.cityId = this.cityId;
		vo.cityName = this.cityName;
		vo.state = this.state;
		
		return vo;
	}
}
