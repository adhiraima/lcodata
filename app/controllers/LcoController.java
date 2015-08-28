/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import vo.CityVO;
import vo.StateVO;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;

import entity.City;
import entity.Lco;
import entity.LcoData;
import entity.State;
import flexjson.JSONDeserializer;

/**
 * @author adhiraima
 *
 */
public class LcoController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result addLcoData() {
		ObjectNode jsonRes = Json.newObject();
		Logger.info(">>>>>>>>"+request().body());
		Lco payload = new JSONDeserializer<Lco>().deserialize(
                request().body().asJson().toString(), Lco.class);
		
		if (null != Lco.findById(payload.getLcoCode())) {
			jsonRes.put("status", "CONFLICT");
			jsonRes.put("message", "LCO already exists");
			return ok(jsonRes);
		} else {
			Ebean.save(payload);
			//create an lco master entry
			LcoData data = new LcoData();
			data.setLcoCode(payload.getLcoCode());
			data.setArea(City.findById(payload.getCity()).getCityName());
			Ebean.save(data);
		}
		jsonRes.put("status", "SUCCESS");
		jsonRes.put("message", "added successfully");
		return redirect("list.html");
	}
	@BodyParser.Of(BodyParser.Json.class)
	public static Result editLcoData() {
		Lco payload = new JSONDeserializer<Lco>().deserialize(
                request().body().asJson().toString(), Lco.class);
		Ebean.update(payload);
		return ok();
	}
	
	public static Result uploadAggrement() {
		return ok();
	}
	
	public static Result uploadKYC() {
		return ok();
	}
	
	public static Result getStates() {
		List<State> states = State.findAllStates();
		List<StateVO> vos = new ArrayList<StateVO>();
		for (State state : states) {
			vos.add(state.getVO());
		}
		return ok(Json.toJson(vos));
	}
	
	public static Result getCities(Long stateId) {
		List<City> cities = City.findByState(stateId);
		List<CityVO> vos = new ArrayList<CityVO>();
		for (City city : cities) {
			vos.add(city.getVO());
		}
		return ok(Json.toJson(vos));
	}

}
