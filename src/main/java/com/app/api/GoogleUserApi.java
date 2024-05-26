package com.app.api;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.people.v1.PeopleService;


public class GoogleUserApi {
    private static final String APPLICATION_NAME = "Sheets&Share";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static final PeopleService getService() throws IOException,GeneralSecurityException{
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            PeopleService peopleService = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, OAuthAPI.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
            return peopleService;
            }
    
}
