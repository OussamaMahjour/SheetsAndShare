package com.app.controller;

import java.io.IOException;
import com.app.exeption.invalidSpreadsheetException;
import com.app.model.Spreadsheet;
import com.app.model.User;
import com.app.service.SpreadsheetService;
import com.google.inject.Inject;
import spark.Request;
import spark.Response;
import spark.Spark;

public class SpreadsheetController extends Controller{

    SpreadsheetService service ;

    @Inject
    public SpreadsheetController(SpreadsheetService spreadsheetService){
        this.service = spreadsheetService;
    }


    @Override 
    public void initRoutes(){
        Spark.path("/spreadsheet",()->{
            Spark.get("", this::index);
            Spark.get("/addSpreadsheet",this::importSpreadsheet);
            Spark.get("/deleteSpreadsheet",this::deleteSpreadsheet);
            Spark.exception(invalidSpreadsheetException.class, (exception,request,response)->{
                Spark.halt(502);});
            });
    }



    public String index(Request request ,Response response){
        return render(request, "spreadsheet.ftl");
    }



    public Response importSpreadsheet(Request request, Response response) throws IOException,invalidSpreadsheetException{
        String id = request.queryParams("spreadsheetId");
        User user = request.session().attribute("user");
        this.service.addSpreadsheet(id,user);
        return response;
    }


    
    public Response deleteSpreadsheet(Request request,Response response){
        String id = request.queryParams("id");
        User user = request.session().attribute("user");
       this.service.deleteSpreadsheet(id,user);
       return response;
    }
}
