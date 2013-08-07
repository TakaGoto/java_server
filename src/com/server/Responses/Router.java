package com.server.Responses;

import com.server.Handlers.*;

import java.io.File;
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

        if(uriIsFile(URI)) addFile(URI);

        if(routes.containsKey(URI)) {
            Responder responder = routes.get(URI);
            response = responder.respond(req);
        } else {
            Hashtable<String, Object> header = new Hashtable<String, Object>();
            response.put("status-line", new ResponseStatusLine("404", req.get("HTTP-Version")).getStatusLine());
            header.put("Content-Type", "text/html");
            response.put("message-header", header);
            response.put("message-body", "".getBytes(Charset.forName("utf-8")));
        }
        return response;
    }

    public String getDir() {
        return rootDir;
    }

    public Hashtable<String, Responder> getRoutes() {
        return routes;
    }

    private void setUpRoutes() {
        BasicAuth basicAuth = new BasicAuth();
        routes = new Hashtable<String, Responder>();
        addRedirect("/redirect");
        routes.put("/form", new PutPost());
        routes.put("/parameters", new ParameterDecode());
        routes.put("/", new Root());
        routes.put("/file1", new FileHandler(rootDir));
        routes.put("/method_options", new Root());
        routes.put("/logs", basicAuth);
        routes.put("/log", basicAuth);
        routes.put("/these", basicAuth);
        routes.put("/requests", basicAuth);
    }

    private boolean uriIsFile(String URI) {
        File file = new File(rootDir, URI.substring(1));
        return file.isFile();
    }

    public void addFile(String URI) {
        routes.put(URI, new FileHandler(rootDir));
    }

    public void addRedirect(String URI) {
        routes.put(URI, new Redirect());
    }
}
