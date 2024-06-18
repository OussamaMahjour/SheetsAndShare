package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.app.model.Column;
import com.app.model.Spreadsheet;
import com.app.model.Table;
import com.app.model.User;
import com.app.repository.Repository;

import spark.Request;

public class TableService {
        public Table creatTable(Request request,String table_name,String id,String sheet,List<Column> columns){
            Table table = new Table();
            User user = request.session().attribute("user");
            Repository repo = user.getRepository();
            Spreadsheet spreadsheet = repo.getSpreadsheetById(id);
            List<List<Object>> data = spreadsheet.getData().get(sheet);
            List<Column> table_data = new ArrayList<>();


            for(int i = 0;i<columns.size();i++){
                List<Object> rows  = new ArrayList<>();
                Column column = columns.get(i);
                for(int j=column.getStartRow();j<=column.getEndRow();j++){
                    String value=data.get(j).get(column.getSrcColumn()).toString();
                    try{
                        rows.add(Integer.parseInt(value));
                    }
                    catch(NumberFormatException e){
                        rows.add(value);
                    }
                    
                   
                   
                    
                }
                column.setData(rows);
                table_data.add(column);


            }
            table.setId(id)
                 .setSheet(sheet)
                 .setName(table_name)
                 .setData(table_data);
           repo.addTable(table);

       
       




    


            return table;

        }
        public Table creatTable(Request request,String table_name,List<List<String>> data){

            Table table = new Table();
            table.setName(table_name);
            table.setId(UUID.randomUUID().toString());
            User user = request.session().attribute("user");
            Repository repo = user.getRepository();
            List<Column> columns = new ArrayList<>();
            for(int i=0;i<data.get(0).size();i++){
                Column column = new Column();
                column.setName(data.get(0).get(i));
                column.setEndRow(data.size());
                column.setData(new ArrayList<>());
                columns.add(column);
            }
            for(int i =1 ;i<data.size();i++){
                for(int j=0;j<data.get(i).size();j++){
                    columns.get(j).getData().add(data.get(i).get(j));
                }
            }
            table.setData(columns);
            repo.addTable(table);
            return table;
        }
}
