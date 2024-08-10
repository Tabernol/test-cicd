package com.legion.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.legion.service.DisplayService;

import java.io.IOException;
import java.io.OutputStream;

public class DisplayController implements HttpHandler {

    private final DisplayService displayService;

    public DisplayController(DisplayService displayService) {
        this.displayService = displayService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Check if the request method is GET
        if ("GET".equals(exchange.getRequestMethod())) {
            // Define the response message
            String response = displayService.getMessage();
            // Send the response headers
            exchange.sendResponseHeaders(200, response.getBytes().length);
            // Write the response body
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            System.out.println("success");
        } else {
            System.out.println("failed");
            // If not a GET request, respond with 405 Method Not Allowed
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}
