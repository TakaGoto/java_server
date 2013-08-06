package com.server.Responses;

import com.server.Handlers.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class Router {
    private Hashtable<String, Responder> routes;
    private String rootDir = null;

    public Router(String rootDir) {
        this.rootDir = rootDir;
        setUpRoutes();
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
            response.put("message-body", "".getBytes(Charset.forName("utf-8")));
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

    private void setUpRoutes() {
        routes = new Hashtable<String, Responder>();
        routes.put("/redirect", new Redirect());
        routes.put("/form", new PutPost());
        routes.put("/parameters", new ParameterDecode());
        routes.put("/", new Root());
        routes.put("/text-file.txt", new FileHandler(rootDir));
        routes.put("/partial_content.txt", new FileHandler(rootDir));
        routes.put("/file1", new FileHandler(rootDir));
        routes.put("/image.jpeg", new FileHandler(rootDir));
        routes.put("/image.png", new FileHandler(rootDir));
        routes.put("/image.gif", new FileHandler(rootDir));
    }
}
