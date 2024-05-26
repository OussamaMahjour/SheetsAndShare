package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import spark.Response;
import spark.Request;
public class EmailController extends Controller {
    public static String index(Request request,Response response) throws IOException,GeneralSecurityException{
        return render("email.ftl");
    }
}
