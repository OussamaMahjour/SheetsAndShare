package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.app.api.OAuthAPI;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import spark.Request;
import spark.Response;

public class AuthController {
    public static String login(Request request,Response response)throws IOException,GeneralSecurityException{
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        OAuthAPI.getCredentials(HTTP_TRANSPORT,response);
        return "";
    }
    public static String logOut(Request request,Response response){
        return "";
    }
}
