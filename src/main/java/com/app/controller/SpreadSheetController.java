package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.api.OAuthAPI;
import com.app.model.Repository;
import com.app.model.SpreadSheet;
import com.app.service.SpreadSheetService;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;

import spark.Request;
import spark.Response;

public class SpreadSheetController extends Controller {
    public static String index(Request request,Response response) throws IOException,GeneralSecurityException{
        Repository repo = request.session().attribute("repo");
        Map<SpreadSheet,Object> data = new HashMap<>();
        for(SpreadSheet spreadSheet:repo.getSpreadSheets()){
            data.put(spreadSheet,spreadSheet.getValues());  
            
        }
        Map<String,Object> param = new HashMap<>();
        param.put("data",data);
        return render(param,"spreadsheet.ftl");
        

    }
    public static Response addSpreadSheetById(Request request,Response response) throws IOException,GeneralSecurityException{
       String id = request.queryParams("spreadsheetId");

    SpreadSheet spreadsheet = SpreadSheetService.getSpreadsheet(id);
    Repository repo = request.session().attribute("repo");
    repo.addSpreadSheet(spreadsheet);
        
        response.status(200);
        return response;
    }
    public static String tables(Request request ,Response response) throws IOException,GeneralSecurityException{
        return render("tables.ftl");

    }
}
