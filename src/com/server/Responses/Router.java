package com.server.Responses;

import com.server.Handlers.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class Router {
    private Hashtable<String, Responder> routes = new Hashtable<String, Responder>();

    public Hashtable route(Hashtable<String, Object> req) throws IOException {
        String URI = (String) req.get("Request-URI");

        if(routes.containsKey(URI)) {
            return routes.get(URI).respond(req);
        }
        else {
            return new FourOhFour().respond(req);
        }
    }

    public Hashtable<String, Responder> getRoutes() {
        return routes;
    }

    public void addRoute(String URI, Responder responder) {
        routes.put(URI, responder);
    }
}
