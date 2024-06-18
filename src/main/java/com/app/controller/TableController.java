package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import com.app.model.Column;
import com.app.service.TableService;
import com.google.inject.Inject;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

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
            Spark.post("/csvTable",this::csvTable);
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
  
    public Response csvTable(Request request,Response response) throws IOException,ServletException,CsvValidationException{
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        try (InputStream is = request.raw().getPart("csvFile").getInputStream()) {
            List<List<String>> list = new ArrayList<>();    
            CSVReader csvReader = new CSVReader(new InputStreamReader(is));
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                
                list.add(Arrays.asList(line));
            }
            is.close();
            csvReader.close();

            this.service.creatTable(request, "testTable", list);
            response.redirect("/table");
            
            return response;
        }
    }
    
}