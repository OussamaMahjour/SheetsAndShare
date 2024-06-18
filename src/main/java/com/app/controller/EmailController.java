package com.app.controller;


import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.app.model.User;
import com.app.service.EmailService;
import com.app.service.TableService;
import com.google.inject.Inject;

import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Spark;

public class EmailController extends Controller{
    
     EmailService service ;

    @Inject
    public EmailController(EmailService emailService){
        this.service = emailService;
    }
    
    @Override 
    public void initRoutes(){
        Spark.get("/email",this::index);
        Spark.get("/compileEmail",this::compileMessage);
        Spark.get("/sendEmail",this::sendEmail);
    }

    public String index(Request request,Response response){
        return render(request,"email.ftl");
    }
    public String compileMessage(Request request,Response response) throws IOException,TemplateException{
        String message = request.queryParams("message");
        User user = request.session().attribute("user");
        String dest_table = request.queryParams("dest_table");
        String dest_column = request.queryParams("dest_column");
        List<String> results = this.service.compile(user.getRepository().getTableName(dest_table),dest_column,user.getRepository().getTables(), message);
        
       

        return results.get(0);
    }
    public Response sendEmail(Request request,Response response) throws MessagingException,IOException,TemplateException{
        
        String message = request.queryParams("message");
        String dest_table = request.queryParams("dest_table");
        String dest_column = request.queryParams("dest_column");
        String subject = request.queryParams("subject");
        User user = request.session().attribute("user");
        this.service.sendEmail(user.getRepository().getTableName(dest_table), dest_column, user.getRepository().getTables(),subject, message);
        
        return response;
    }
}