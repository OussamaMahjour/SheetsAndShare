package com.app.model;

import java.util.Map;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet ;

public class SpreadSheet {
    private Spreadsheet spreadsheet;
    private Map<String,Object> values;
    private String id ;
    private String name ;

    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public Spreadsheet getSpreadsheet(){
        return this.spreadsheet;
    }
    public Map<String,Object> getValues(){
        return this.values;
    }
    public SpreadSheet setSpreadSheet(Spreadsheet spreadsheet){
        this.spreadsheet = spreadsheet;
        this.name = spreadsheet.getProperties().getTitle();
        this.id = spreadsheet.getSpreadsheetId();
        return this;
    }
    public SpreadSheet setValues(Map<String,Object> values){
        this.values =values;
        return this;
    }


    
}
