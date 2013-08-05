package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.util.Hashtable;

public class Root implements Responder {
    private String body;
    private Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public Root() {
        generateBody();
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) {
        if(req.containsValue("GET")) {
            resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
        } else {
            resp.put("status-line", new ResponseStatusLine("404", req.get("HTTP-Version")).getStatusLine());
        }
            Hashtable<String, String> messageHeader = new Hashtable<String, String>();
            messageHeader.put("Content-Type", "text/html");
            messageHeader.put("Connection", "close");
            resp.put("message-header", messageHeader);
            resp.put("message-body", body);
        return resp;
    }

    private void generateBody() {
        body = "<html><head><title></title></head><body> Empty </body></html>";
    }
}
