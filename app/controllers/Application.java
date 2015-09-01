package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return redirect("/index.html");
    }
    
    public static Result show(String page) {
    	if (page.equalsIgnoreCase("home"))
    		return redirect("/index.html");
    	else
    		return redirect("public/"+page);
    }

}
