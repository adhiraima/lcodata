/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import vo.LcoDataVO;
import entity.Employee;
import entity.Lco;
import entity.LcoData;

/**
 * @author adhiraima
 *
 */
public class LandingController extends Controller {

	public static Result showMasterData() {
		Logger.info(">>>>>>>>here in get master list");
		List<LcoData> lcos = LcoData.getAll();
		List<LcoDataVO> vos = new ArrayList<LcoDataVO>();
		
		for (LcoData lco : lcos) {
			vos.add(lco.getVO());
		}
		return ok(Json.toJson(vos));
	}
	
	public static Result showSelectedData(String employeeId) {
		Logger.info(">>>>>>>>here in get master list");
		List<Lco> lcos = Lco.findAllByEmployee(employeeId);
		List<LcoData> lcodata = LcoData.findByLcoCodes(lcos);
		List<LcoDataVO> vos = new ArrayList<LcoDataVO>();
		for (LcoData lco : lcodata) {
			vos.add(lco.getVO());
		}
		return ok(Json.toJson(vos));
	}
	
	public static Result getEmployee(String employeeId) {
		return ok(Json.toJson(Employee.findEmployeeeById(employeeId).getVO()));
	}
}
