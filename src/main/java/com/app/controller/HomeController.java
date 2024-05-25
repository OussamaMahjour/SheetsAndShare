package com.app.controller;

import java.io.File;

import spark.Request;
import spark.Response;

public class HomeController extends Controller{
        public static String index(Request request,Response response){
            File f = new File("tokens/StoredCredential");
            if(f.exists()){
                return render("home.ftl") ;
            }
            else{
                return render("login.ftl") ;
            }

            
        }
}