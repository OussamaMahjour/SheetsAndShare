package com.app.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

public class Repository {
    private List<SpreadSheet> spreadsheets;
    private List<ValueRange> tables;
    public Repository(){
        this.spreadsheets = new ArrayList<>();
        this.tables = new ArrayList<>();
    }
    public List<SpreadSheet> getSpreadSheets(){
        return this.spreadsheets;
    }
    public List<ValueRange> getTables(){
        return this.tables;
    }
    public Repository addSpreadSheet(SpreadSheet spreadSheet){
        this.spreadsheets.add(spreadSheet);
        return this;
    }
    public Repository addTable(ValueRange table){
        this.tables.add(table);
        return this;

    }
    
}
