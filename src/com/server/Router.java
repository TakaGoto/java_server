package com.server;

import java.util.Hashtable;

public class Router {
    private Hashtable<String, String> routes;

    public Router() {
        routes = new Hashtable<String, String>();
    }
    public Hashtable<String, String> route(Hashtable<String, String> request) {
        Hashtable<String, String> response = new Hashtable<String, String>();
        response.put("HTTP-Version", request.get("HTTP-Version"));

        if(routes.get(request.get("Request-URI")) != null) {
            response.put("Status-Code", "200");
            response.put("Reason-Phrase", "OK");
        } else {
            response.put("Status-Code", "404");
            response.put("Reason-Phrase", "Not Found");
        }
        response.put("Body", "Hello World!");
        return response;
    }

    public void addRoute(String route) {
        routes.put(route, " ");
    }
}
