package com.app.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import com.app.api.GoogleUserApi;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;

public class AuthService {
    public static Map<String,Object> getUserInfo() throws IOException,GeneralSecurityException{
        PeopleService service = GoogleUserApi.getService();

        Person profile = service.people().get("people/me").setPersonFields("emailAddresses,photos").execute();

        
        
        
      
        Map<String,Object> info = new HashMap<>();
        info.put("email", profile.getEmailAddresses().get(0).getValue());
        info.put("profile", profile.getPhotos().get(0).getUrl());

        return info;


    }
}
