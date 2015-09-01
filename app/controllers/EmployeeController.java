package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import entity.Employee;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.BodyParser;

import flexjson.JSONDeserializer;

import vo.EmployeeVO;

/**
 * @author adhiraima
 *
 */
public class EmployeeController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result add() {
		ObjectNode jsonRes = Json.newObject();
		Employee payload = new JSONDeserializer<Employee>().deserialize(
                request().body().asJson().toString(), Employee.class);
		Ebean.save(payload);
		return ok();
	}
}