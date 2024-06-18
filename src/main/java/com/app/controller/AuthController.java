package com.app.controller;


import java.io.IOException;
import java.security.GeneralSecurityException;
import com.app.config.AppConfig;
import com.app.exeption.LoginException;
import com.app.model.User;
import com.app.service.UserService;
import com.app.util.GoogleUtilApi;
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
        Spark.get("logOut",this::logOut);

    } 


    @Override
    public String index(Request request,Response response){
       if(request.session().attribute("user")!=null){
        return render(request,"home.ftl");
      } 
        return render(request, "login.ftl");

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
            Spark.unmap("/login");
            UserService userService = injector.getInstance(UserService.class);
            User user = userService.creatUser(request);
            request.session().attribute("user",user);
            SpreadsheetController spreadsheetController= injector.getInstance(SpreadsheetController.class);
            EmailController emailController = injector.getInstance(EmailController.class);
            TableController tableController = injector.getInstance(TableController.class);
            spreadsheetController.initRoutes();
            emailController.initRoutes();
            tableController.initRoutes();
          }
        response.redirect("/");
        return "connected you may now close this window";
        
    }

    public String logOut(Request request,Response response){
        request.session().removeAttribute("user");
        response.redirect("/");
        Spark.get("/login",this::login);

        return "";
    }
}
