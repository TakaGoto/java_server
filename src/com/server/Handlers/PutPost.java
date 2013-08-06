package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.nio.charset.Charset;
import java.util.Hashtable;

public class PutPost implements Responder {
    private String body;
    private Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public PutPost() {
        generateBody();
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();

        if(req.containsValue("POST") || req.containsValue("PUT")) {
            if(req.containsKey("Body")) {
                Hashtable<String, String> body = (Hashtable<String, String>) req.get("Body");
                echoBody(body.get("data"));
            }
        }

        resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
        resp.put("message-body", this.body.getBytes(Charset.forName("utf-8")));
        messageHeader.put("Content-Length", String.valueOf(this.body.length()));
        messageHeader.put("Content-Type", "text/html");
        messageHeader.put("Connection", "close");
        resp.put("message-header", messageHeader);
        return resp;
    }

    private void generateBody() {
        body = "<html><head><title></title></head><body> Empty </body></html>";
    }

    private void echoBody(String body) {
        this.body = "<html><head><title></title></head><body> data = " + body + " </body></html>";
    }
}
