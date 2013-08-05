package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.util.Hashtable;

public class File implements Responder {
    private String body;
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public File() {
        generateBody();
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();

        if(!req.get("Method").equals("GET")) {
            resp.put("status-line", new ResponseStatusLine("405", req.get("HTTP-Version")).getStatusLine());
            messageHeader.put("Allow", "GET");
        } else {
            resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
        }

        messageHeader.put("Content-Type", "text/html");
        messageHeader.put("Content-Length", String.valueOf(body.length()));
        messageHeader.put("Connection", "close");
        resp.put("message-header", messageHeader);
        resp.put("message-body", body);

        return resp;
    }

    private void generateBody() {
        body = "<html><head><title></title></head><body> Empty </body></html>";
    }
}
