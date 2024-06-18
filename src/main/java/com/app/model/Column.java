package com.app.model;

import java.util.List;


public class Column { 
    
    private String name;
    private int srcColumn;
    private int startRow;
    private int endRow;
    private List<Object> data;
     

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSrcColumn() {
        return this.srcColumn;
    }

    public void setSrcColumn(int srcColumn) {
        this.srcColumn = srcColumn;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public List<Object> getData() {
        return this.data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
   


    
}
