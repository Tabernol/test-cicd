package com.legion;

import com.sun.net.httpserver.HttpServer;
import com.legion.config.HttpConfig;
import com.legion.controller.DisplayController;
import okhttp3.OkHttpClient;
import com.legion.service.DisplayService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    private static final int PORT = 8080;


    public static void main(String[] args) {
        OkHttpClient httpClient = HttpConfig.getInstance();
        // Create an HTTP server that listens on port 8080
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
        } catch (IOException e) {
            System.out.println("failed create end-point");
            throw new RuntimeException(e);
        }
        // Define a context and associate it with a handler
        server.createContext("/api/message", new DisplayController(new DisplayService()));
        server.createContext("/api/exit", (handler) -> exit());
        // Create a default executor
        server.setExecutor(null);
        // Start the server
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    private static void exit(){
        System.out.println("Shutdown app");
        System.exit(0);
    }
}
