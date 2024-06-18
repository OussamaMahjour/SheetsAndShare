package com.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Spreadsheet;
import com.app.model.Table;

public class Repository {
    List<Spreadsheet> spreadsheets;
    List<Table> tables;

    public Repository(){
        this.spreadsheets= new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    public Repository addTable(Table table){
        this.tables.add(table);
        return this;

    }

    public Repository addSpreadsheet(Spreadsheet spreadsheet){
        this.spreadsheets.add(spreadsheet);
        return this;
    }

    public Spreadsheet getSpreadsheetById(String id){
        for(Spreadsheet spreadsheet:this.spreadsheets){
            if(spreadsheet.getId().equals(id)){
                return spreadsheet;
            }
        }
        return null;
    }

    public Table getTableName(String name){
        for(Table table:this.tables){
            if(table.getName().equals(name)){
                return table;
            }
        }
        return null;

    }

    public List<Spreadsheet> getSpreadsheets(){
        return this.spreadsheets;
    }

    public List<Table> getTables(){
        return this.tables;
    }




    
}
