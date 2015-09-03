/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import entity.JV;
import entity.Lco;
import entity.LcoData;
import entity.State;
import flexjson.JSONDeserializer;

/**
 * @author adhiraima
 *
 */
public class LcoController extends Controller {
	
	public static Result getLco(String lcoCode) {
		Lco lco = Lco.findById(lcoCode);
		if (null != lco) {
			return ok(Json.toJson(lco.getVO()));
		} else {
			return badRequest();
		}
	}
	
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
		return ok(Json.toJson(jsonRes));
	}
	@BodyParser.Of(BodyParser.Json.class)
	public static Result editLcoData() {
		Logger.info(">>>>>>>>"+request().body());
		ObjectNode jsonRes = Json.newObject();
		Lco payload = new JSONDeserializer<Lco>().deserialize(
                request().body().asJson().toString(), Lco.class);
		Ebean.update(payload);
		jsonRes.put("message", "updated successfully!!");
		return ok(Json.toJson(jsonRes));
	}
	
	public static Result uploadAgreement(String lcoCode) {
		ObjectNode jsonRes = Json.newObject();
		Map<String, String> fileName = new JSONDeserializer<Map<String, String>>().deserialize(
                request().body().asJson().toString(), Map.class);
		LcoData lco = LcoData.findByLcoCode(lcoCode);
		String filename = fileName.get("file").substring(fileName.get("file").lastIndexOf("\\") + 1, fileName.get("file").length());
		lco.setAgreementId(filename);
		Ebean.update(lco);
		jsonRes.put("successMessage", "file Uploaded!!");
		return ok(Json.toJson(jsonRes));
	}
	
	public static Result uploadKYC(String lcoCode) {
		ObjectNode jsonRes = Json.newObject();
		Map<String, String> fileName = new JSONDeserializer<Map<String, String>>().deserialize(
                request().body().asJson().toString(), Map.class);
		LcoData lco = LcoData.findByLcoCode(lcoCode);
		String filename = fileName.get("file").substring(fileName.get("file").lastIndexOf("\\") + 1, fileName.get("file").length());
		lco.setKycId(filename);
		Ebean.update(lco);
		jsonRes.put("successMessage", "file Uploaded!!");
		return ok(Json.toJson(jsonRes));
	}
	
	public static Result getStates() {
		List<State> states = State.findAllStates();
		List<StateVO> vos = new ArrayList<StateVO>();
		for (State state : states) {
			vos.add(state.getVO());
		}
		return ok(Json.toJson(vos));
	}
	
	public static Result getState(Long stateId) {
		return ok(Json.toJson(State.findById(stateId).getVO()));
	}
	
	public static Result delete(String lcoCode) {
		Logger.info(">>>>>>delete");
		Lco lco = Lco.findById(lcoCode);
		LcoData lcoData = LcoData.findByLcoCode(lcoCode);
		Ebean.delete(lco);
		Ebean.delete(lcoData);
		return ok();
	}
	
	public static Result getCities(Long stateId) {
		List<City> cities = City.findByState(stateId);
		List<CityVO> vos = new ArrayList<CityVO>();
		for (City city : cities) {
			vos.add(city.getVO());
		}
		return ok(Json.toJson(vos));
	}

	public static Result getCity(Long cityId) {
		return ok(Json.toJson(City.findById(cityId).getVO()));
	}
	
	public static Result getJVs() {
		return ok(Json.toJson(JV.findAll()));
	}
}
