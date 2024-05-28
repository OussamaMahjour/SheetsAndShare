package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.model.Repository;
import com.app.model.SpreadSheet;
import com.app.model.Table;

import spark.Request;
import spark.Response;

public class TableController extends Controller {
    public static String index(Request request,Response response)throws IOException,GeneralSecurityException{
        Repository repo = request.session().attribute("repo");
        List<SpreadSheet> spreadsheets = repo.getSpreadSheets() ;
        List<Table> tables = repo.getTables();
        Map<String,Object> data = new HashMap<>();
        data.put("spreadsheets",spreadsheets);
        data.put("tables",tables);
        
        
        return render(data,"tables.ftl");
    }
    public static String createTable(Request request,Response response) throws IOException,GeneralSecurityException{
        String id = request.queryParams("spreadsheet");
        String sheet = request.queryParams("sheet");
        String name = request.queryParams("name");
        Repository repo = request.session().attribute("repo");
        SpreadSheet spreadSheet =null ;
        for(SpreadSheet spread :repo.getSpreadSheets()){
            if(spread.getId().equals(id) ){
                spreadSheet = spread;
            }
        }
        
        Table table = new Table(name, id, sheet,spreadSheet);
        repo.addTable(table);
        System.out.println(table.getData());
        
        return null;
    }
    
}
