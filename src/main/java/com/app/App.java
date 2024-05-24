package com.app;

import spark.Spark;
import com.app.controller.HomeController;
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
        get("/",HomeController::index);

    }
}
