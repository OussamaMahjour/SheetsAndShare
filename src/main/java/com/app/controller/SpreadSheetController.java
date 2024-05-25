package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.service.SpreadSheetService;

import spark.Request;
import spark.Response;

public class SpreadSheetController extends Controller {
    public static String index(Request request,Response response) throws IOException,GeneralSecurityException{
        Map<String,List<List<Object>>> values ;
        if(request.session().attribute("spreadSheet")!=null){
            values = request.session().attribute("spreadSheet");
        }
        else if(
            request.queryParams("spreadSheetId")!=null && 
            request.queryParams("spreadSheetId").length()!=0
        ){
            values = SpreadSheetService.getSheetsValues(request.queryParams("spreadSheetId"));
            request.session().attribute("spreadSheet", values);

        }
        else{
            return render("spreadsheet.ftl");
        }
        
        Map<String,Object> param = new HashMap<>();
        param.put("data", values);
        return render(param,"spreadsheet.ftl");

    }
}
