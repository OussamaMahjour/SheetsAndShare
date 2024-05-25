package com.app.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.api.SpreadSheetAPI;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;

import io.opencensus.metrics.export.Value;

public class SpreadSheetService {


    public static Map<String,List<List<Object>>> getSheetsValues(String id) throws IOException,GeneralSecurityException{
        Sheets service = SpreadSheetAPI.getService();
      
        Spreadsheet spreadsheet = service.spreadsheets().get(id).execute();
        List<Sheet> sheets = spreadsheet.getSheets();
        Map<String,List<List<Object>>> values = new HashMap<String,List<List<Object>>>();
        for(Sheet sheet:sheets){
            String sheetName = sheet.getProperties().getTitle();
            ValueRange response = service.spreadsheets().values().get(id, sheetName).execute();
            values.put(sheetName,response.getValues());
        }
        return values;
        

       

    }
    public static Spreadsheet getSpreadsheet(String id) throws IOException,GeneralSecurityException{
        Sheets service = SpreadSheetAPI.getService();
      
        Spreadsheet spreadsheet = service.spreadsheets().get(id).execute();
        return spreadsheet;

    }
    public static Sheet getSheet(Spreadsheet spreadsheet,String name)throws IOException,GeneralSecurityException
    {
        for(Sheet sheet :spreadsheet.getSheets()){
            if(sheet.getProperties().getTitle().equals(name)){
                return sheet;
            }
        }
        return null;
    }
   
}
