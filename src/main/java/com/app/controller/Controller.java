package com.app.controller;
import java.util.HashMap;
import java.util.Map;

import com.app.App;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public abstract class Controller {

    public static  FreeMarkerEngine FreeMarkerEngine() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(App.class, "/public/templates");
        return new FreeMarkerEngine(cfg);
    }
    public static String render(Map<String,Object> param,String file){
        return FreeMarkerEngine().render(new ModelAndView(param, file));
    }
    public static String render(String file){
        return FreeMarkerEngine().render(new ModelAndView((new HashMap<>()), file));
    }

}
