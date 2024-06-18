package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Column;
import com.app.service.TableService;
import com.google.inject.Inject;

import spark.Request;
import spark.Response;
import spark.Spark;

public class TableController extends Controller{

    TableService service ;

    @Inject
    public TableController(TableService tableService){
        this.service = tableService;
    }


    @Override 
    public void initRoutes(){
        Spark.path("/table",()->{
            Spark.get("",this::index);
            Spark.post("/creatTable",this::creatTable);
        });
        
    }

    public String index(Request request, Response response){
        return render(request, "table.ftl");
    }

    public Response creatTable(Request request,Response response){

        String spreadsheet = request.queryParams("spreadsheet");
        String sheet = request.queryParams("sheet").split("!")[0];
        int ColumnsNbr = Integer.parseInt(request.queryParams("totale-number"));
        String table_name = request.queryParams("table-name");
        List<Column> Columns = new ArrayList<>();
       
        for(int i=0;i<ColumnsNbr;i++){
            if(request.queryParams("table-column-"+i)!=null){
                Column column = new Column();
                column.setName(request.queryParams("table-column-"+i));
                column.setSrcColumn(Integer.parseInt(request.queryParams("sheet-column-"+i)));
                column.setStartRow(Integer.parseInt(request.queryParams("row-start-"+i)));
                column.setEndRow( Integer.parseInt(request.queryParams("row-end-"+i)));

                
                Columns.add(column);
            }
            
           
        }
       

         this.service.creatTable(request, table_name,spreadsheet, sheet, Columns);
       response.redirect("/table");


        return response;

    }
}