package com.app.model;

import java.util.List;
import java.util.Map;

public class Table {
    private String name ;
    private String spreadsheetId;
    private String sheet;
    private List<List<Object>> data;

    public Table(String name,String id,String sheet,SpreadSheet spreadsheet){
        this.name =name;
        this.spreadsheetId = id;
        this.sheet = sheet;
        this.data = (List<List<Object>>)spreadsheet.getValues().get(sheet);
        
        
    }
    public String getName(){
        return this.name;
    }
    public String getSpreadsheetId(){
        return this.spreadsheetId;
    }
    public String getSheet(){
        return this.sheet;
    }
    public List<List<Object>> getData(){
        return this.data;
    }
}
