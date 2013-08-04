package com.server.Responses;

import com.server.Responses.ResponseStatusLine;

import java.util.Hashtable;

public class Router {
    private Hashtable<String, String> routes;
    private ResponseStatusLine statusLine;

    public Router() {
        routes = new Hashtable<String, String>();
        addRoute("/form");
        addRoute("/");
        addRoute("/redirect");
        addRoute("/file1");
        addRoute("/text-file.txt");
    }

    public Hashtable<String, Object> route(Hashtable<String, Object> req) {
        Hashtable<String, Object> response = new Hashtable<String, Object>();
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();
        String URI = (String) req.get("Request-URI");

        if(routes.containsKey(URI)) {
            if(URI.equals("/redirect")) {
                statusLine = new ResponseStatusLine("301", req.get("HTTP-Version"));
                messageHeader.put("Location", "http://localhost:5000/");
            } else statusLine = new ResponseStatusLine("200", req.get("HTTP-Version"));

            if(URI.equals("/file1") || URI.equals("/text-file.txt")) {
                statusLine = new ResponseStatusLine("405", req.get("HTTP-Version"));
                messageHeader.put("Allow", "GET");
            }

            messageHeader.put("Content-Type", "text/html");
            messageHeader.put("Connection", "close");
            response.put("status-line", statusLine.getStatusLine());
            response.put("message-header", messageHeader);

            if(req.get("Method").equals("POST") || req.get("Method").equals("PUT")) {
                if(req.containsKey("Body")) {
                    Hashtable<String, String> body = (Hashtable<String, String>) req.get("Body");
                    addBodyToRoute("data = " + body.get("data"), URI);
                }
            }
            messageHeader.put("Content-Length", String.valueOf(getBody(URI).length()));
            response.put("message-body", routes.get(URI));
        } else {
            statusLine = new ResponseStatusLine("404", req.get("HTTP-Version"));
            messageHeader.put("Content-Type", "text/html");
            messageHeader.put("Content-Length", "0");
            messageHeader.put("Connection", "close");
            response.put("status-line", statusLine.getStatusLine());
            response.put("message-header", messageHeader);
            response.put("message-body", "oh oh");
        }
        return response;
    }

    public void addRoute(String route) {
        routes.put(route, "<html><head><title></title></head><body> Empty </body></html>");
    }

    public void addBodyToRoute(String body, String URI) {
        routes.put(URI, "<html><head><title></title></head><body> " + body + " </body></html>");
    }

    public String getBody(String URI) {
        return routes.get(URI);
    }
}
