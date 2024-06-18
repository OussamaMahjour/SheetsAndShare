package com.app.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.app.exeption.LoginException;
import com.app.model.User;
import com.app.util.GoogleUtilApi;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.inject.Inject;

import spark.Request;

public class UserService {
    PeopleService service ;

    @Inject
    public UserService(GoogleUtilApi api) throws IOException,LoginException,GeneralSecurityException{
        this.service = api.getPeopleService();
    }

    public String getEmail() throws IOException{
        Person profile = service.people().get("people/me").setPersonFields("emailAddresses").execute();
        String email = profile.getEmailAddresses().get(0).getValue();
        return email;
    }
    public String getProfile() throws IOException{
        Person profile = service.people().get("people/me").setPersonFields("photos").execute();
        String photo = profile.getPhotos().get(0).getUrl();
        return photo;
    }
    public User creatUser(Request request) throws IOException{
        
        Person profile = service.people().get("people/me").setPersonFields("emailAddresses,photos").execute();
        User user = new User(profile);
        user.setId(request.session().id());
        request.session().attribute("user",user);
        return user;
    }
    
}
