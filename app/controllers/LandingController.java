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
}
