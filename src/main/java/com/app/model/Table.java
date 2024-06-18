package com.app.model;

import java.util.List;
import java.util.Map;


public class Table {
    private String name;
    private String id;
    private String sheet;
    private List<Column> data;

    public Table(){}
    public Table(String id,String name, List<Column> data){
        this.id = id;
        this.name = name;
        this.data = data;

    }
    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public List<Column> getData(){
        return this.data;
    }

    public String getSheet(){
        return this.sheet;
    }
    public Table setSheet(String sheet){
        this.sheet = sheet;
        return this;
    };

    public Table setName(String name){
        this.name = name;
        return this;
    }

    public Table setId( String id){
        this.id = id;
        return this;
    }
    public Table setData(List<Column> data){
        this.data = data;
        return this;
    }
    public Column getColumn(String name){
        for(Column column : this.data ){
            if(column.getName().equals(name)){
                return column;
            }
        }
        return null;

    }

  

}
