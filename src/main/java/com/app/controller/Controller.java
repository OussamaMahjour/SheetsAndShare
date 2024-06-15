package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import com.app.App;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public abstract class Controller {
    
    /**
     * Configures and returns a FreeMarkerEngine with the specified template directory.
     *
     * @return a configured FreeMarkerEngine instance.
     */
    public static  FreeMarkerEngine FreeMarkerEngine() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(App.class, "/public/templates");
        return new FreeMarkerEngine(cfg);
    }

    
    /**
     * Renders a template with the provided parameters.
     *
     * @param request the spark request object.
     * @param param a map of parameters to pass to the template.
     * @param file the name of the FreeMarker template file.
     * @return the rendered template as a string.
     */
    public static String render(Request request,Map<String,Object> param,String file){
        param.put("user",request.session().attribute("user"));  
        return FreeMarkerEngine().render(new ModelAndView(param, file));
    }

    /**
     * Renders a FreeMarker template without additional parameters.
     *
     * @param request the spark request object.
     * @param file the name of the FreeMarker template file.
     * @return the rendered template as a string.
     */
    public static String render(Request request,String file){
        Map<String,Object> param = new HashMap<>();
        param.put("user",request.session().attribute("user"));
        return FreeMarkerEngine().render(new ModelAndView(param, file));
    }


    /**
     * Initializes the routes for the controller.
     * Every Controller should at least initialise one route (handled by index())
     */
    public abstract void initRoutes();


     /**
     * Handles the controller main route
     *
     * @param request the spark request object.
     * @param response the spark response object.
     * @return the response as a string.
     */
    public abstract String index(Request request,Response response);

}
