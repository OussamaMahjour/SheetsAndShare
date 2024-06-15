package com.app.controller;


import java.io.IOException;
import java.security.GeneralSecurityException;
import com.app.config.AppConfig;
import com.app.exeption.LoginException;
import com.app.util.GoogleUtilApi;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleService.People;
import com.google.api.services.people.v1.model.Person;
import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.Request;
import spark.Response;
import spark.Spark;

public class AuthController extends Controller {

    @Override 
    public  void initRoutes(){
        Spark.get("/",this::index);
        Spark.get("/callback",this::auth);
        Spark.get("/login",this::login);

    } 


    @Override
    public String index(Request request,Response response){

        return render(request, "login.html");

    }

    public String login(Request request,Response response) throws IOException{
        Injector injector = Guice.createInjector(new AppConfig());
        GoogleUtilApi google = injector.getInstance(GoogleUtilApi.class);
        String url = google.getAuthURL();
        response.redirect(url);
        return null;
    }

    public String auth(Request request,Response response) throws IOException,GeneralSecurityException,LoginException{
        String code = request.queryParams("code");
          if(code!=null){
            Injector injector = Guice.createInjector(new AppConfig());
            GoogleUtilApi googleApi = injector.getInstance(GoogleUtilApi.class);
            googleApi.setToken(code);

            PeopleService service = googleApi.getPeopleService();
             Person profile = service.people().get("people/me").setPersonFields("emailAddresses").execute();
             String email = profile.getEmailAddresses().get(0).getValue();

             return email;
           
          }
        response.redirect("/");
        return "connected you may now close this window";
        
    }
}
