package com.server.Responses;

import com.server.Handlers.*;

import java.io.IOException;
import java.util.Hashtable;

public class Router {
    private Hashtable<String, Responder> routes;
    private String rootDir = null;

    public Router(String rootDir) {
        this.rootDir = rootDir;
        routes = new Hashtable<String, Responder>();
        routes.put("/redirect", new Redirect());
        routes.put("/form", new PutPost());
        routes.put("/", new Root());
        routes.put("/text-file.txt", new FileHandler(rootDir));
        routes.put("/partial_content.txt", new FileHandler(rootDir));
        routes.put("/file1", new FileHandler(rootDir));
    }

    public Hashtable<String, Object> route(Hashtable<String, Object> req) throws IOException {
        Hashtable<String, Object> response = new Hashtable<String, Object>();
        String URI = (String) req.get("Request-URI");

        if(routes.containsKey(URI)) {
                Responder responder = routes.get(URI);
                return responder.respond(req);
        } else {
            Hashtable<String, Object> header = new Hashtable<String, Object>();
            response.put("status-line", new ResponseStatusLine("404", req.get("HTTP-Version")).getStatusLine());
            header.put("Content-Type", "text/html");
            response.put("message-header", header);
            response.put("message-body", "");
            return response;
        }
    }

    public void setDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getDir() {
        return rootDir;
    }

    public Hashtable<String, Responder> getRoutes() {
        return routes;
    }
}
