package com.server.Responses;

import com.server.Handlers.*;

import java.util.Hashtable;

public class Router {
    private Hashtable<String, Responder> routes;

    public Router() {
        routes = new Hashtable<String, Responder>();
        routes.put("/redirect", new Redirect());
        routes.put("/form", new PutPost());
        routes.put("/", new Root());
        routes.put("/text-file.txt", new File());
        routes.put("/file1", new File());
    }

    public Hashtable<String, Object> route(Hashtable<String, Object> req) {
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
}
