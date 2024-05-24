package com.app.controller;

import spark.Request;
import spark.Response;

public class HomeController{
        public static String index(Request request,Response response){



            return "Home";
        }
}