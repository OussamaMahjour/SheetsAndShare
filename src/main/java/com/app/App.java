package com.app;


import com.app.config.AppConfig;
import com.app.controller.AuthController;
import com.google.inject.Guice;
import com.google.inject.Injector;

import spark.Spark;
import static spark.debug.DebugScreen.enableDebugScreen;


public class App 
{
    public static void main( String[] args )
    {
        App.run();
    }
    public static void run(){
        // Set the port from the environment variable PORT, defaulting to 8080
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Spark.port(port);

        // Set static file location to resources/public
        Spark.staticFiles.location("/public");

        Injector injector = Guice.createInjector(new AppConfig());
        AuthController authController = injector.getInstance(AuthController.class);
        authController.initRoutes();
        
        enableDebugScreen();

    }
}
