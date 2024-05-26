package com.app;

import spark.Spark;

import java.io.File;

import com.app.controller.AuthController;
import com.app.controller.EmailController;
import com.app.controller.HomeController;
import com.app.controller.SpreadSheetController;
import com.app.service.SpreadSheetService;
/**
 * Hello world!
 *
 */
public class App extends Spark
{
    public static void main( String[] args )
    {
        App.run();
    }
    private static void run(){
        port(8080);
        Spark.staticFiles.location("/public");
        before((req,res)->{
             File f = new File("tokens/StoredCredential");
             if(!f.exists() && !req.pathInfo().equals("/") && !req.pathInfo().equals("/login")){
                res.redirect("/");
             }
            
            
        });
        
        get("/",HomeController::index);
        get("/login",AuthController::login);
        get("/logout",AuthController::logOut);
        get("/spreadsheet",SpreadSheetController::index);
        get("/addSpreadSheetById",SpreadSheetController::addSpreadSheetById);
        get("/email",EmailController::index);
        get("/tables",SpreadSheetController::tables);
    
    }
}
