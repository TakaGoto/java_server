package com.server.Responses;

import com.server.Handlers.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class Router {
    private Hashtable<String, Responder> routes = new Hashtable<String, Responder>();
    private String rootDir = null;

    public Router(String rootDir) {
        this.rootDir = rootDir;
    }

    public Hashtable route(Hashtable<String, Object> req) throws IOException {
        Hashtable response = new Hashtable<String, Object>();
        String URI = (String) req.get("Request-URI");

        if(routes.containsKey(URI)) {
            Responder responder = routes.get(URI);
            response = responder.respond(req);
        } else {
            response = generateFourOhFourResponse(req, response);
        }
        return response;
    }

    private Hashtable generateFourOhFourResponse(Hashtable req, Hashtable response) {
        Hashtable<String, Object> header = new Hashtable<String, Object>();
        response.put("status-line", ResponseStatusLine.get("404", req.get("HTTP-Version")));
        response.put("message-header", header);
        response.put("message-body", "404".getBytes(Charset.forName("utf-8")));
        return response;
    }
    private boolean uriIsFile(String URI) {
        File file = new File(rootDir, URI.substring(1));
        return file.isFile();
    }

    public String getDir() {
        return rootDir;
    }

    public Hashtable<String, Responder> getRoutes() {
        return routes;
    }

    public void addRoute(String URI, Responder responder) {
        routes.put(URI, responder);
    }
}
