package com.server;

import com.server.Responses.MessageHeader;
import com.server.Responses.StatusLine;

import java.util.Hashtable;

public class Router {
    private Hashtable<String, String> routes;
    private StatusLine statusLine;
    private MessageHeader messageHeader;

    public Router() {
        routes = new Hashtable<String, String>();
        addRoute("/form");
        addRoute("/");
        addRoute("/redirect");
    }
    public Hashtable<String, Object> route(Hashtable<String, String> req) {
        Hashtable<String, Object> response = new Hashtable<String, Object>();
        Hashtable body = new Hashtable();
        String URI = req.get("Request-URI");

        if(routes.get(URI) != null) {
            statusLine = new StatusLine("200", req.get("HTTP-Version"));
            messageHeader = new MessageHeader("text/html", req.get("Body").length());
            response.put("Body", "hello");
            response.put("status-line", statusLine.getStatusLine());
            response.put("message-header", messageHeader);
            response.put("message-body", body);
        } else {
            statusLine = new StatusLine("404", req.get("HTTP-Version"));
            messageHeader = new MessageHeader("text/html", req.get("Body").length());
            response.put("Body", "that sucks");
            response.put("status-line", statusLine.getStatusLine());
            response.put("message-header", messageHeader.getMessageHeader());
            response.put("message-body", body);
        }
        return response;
    }

    public void addRoute(String route) {
        routes.put(route, " ");
    }
}
