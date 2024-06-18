package com.app.model;


import com.app.repository.Repository;
import com.google.api.services.people.v1.model.Person;

public class User {
    private String email;
    private String profile;
    private Repository repository;
    private String id;
    public User(Person user){
        this.email = user.getEmailAddresses().get(0).getValue();
        this.profile = user.getPhotos().get(0).getUrl();
        this.repository = new Repository();
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
   
    public String getEmail(){
        return this.email;
    }
    public String getProfile(){
        return this.profile;
    }
   
    public User setEmail(String email){
        this.email = email;
        return this;
    }
    public User setProfile(String url){
        this.profile = url;
        return this;
    }
    public Repository getRepository(){
        return this.repository;
    }
    public User setRepository(Repository repo){
        this.repository = repo;
        return this;

    }
    
}