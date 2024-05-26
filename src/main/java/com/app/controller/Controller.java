package com.app.controller;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import com.app.App;
import com.app.service.AuthService;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public abstract class Controller {

    public static  FreeMarkerEngine FreeMarkerEngine() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(App.class, "/public/templates");
        return new FreeMarkerEngine(cfg);
    }
    public static String render(Map<String,Object> param,String file)throws IOException,GeneralSecurityException{

        param.putAll(AuthService.getUserInfo());
        return FreeMarkerEngine().render(new ModelAndView(param, file));
    }
    public static String render(String file)throws IOException,GeneralSecurityException{
        Map<String,Object> param = new HashMap<>(AuthService.getUserInfo());
        return FreeMarkerEngine().render(new ModelAndView(param, file));
    }

}
