package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import com.app.model.Repository;
import com.app.service.AuthService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller{
        public static String index(Request request,Response response) throws IOException,GeneralSecurityException{
            File f = new File("tokens/StoredCredential");
            if(f.exists()){
                Repository repo = request.session().attribute("repo");
                if(repo==null)
                {   repo = new Repository();
                    request.session().attribute("repo", repo);
                    
                }

                System.out.println(repo.getSpreadSheets().toString());
                return render("home.ftl") ;
            }
            else{
                return FreeMarkerEngine().render(new ModelAndView((new HashMap<>()), "login.ftl"));
            } 

            
        }
}