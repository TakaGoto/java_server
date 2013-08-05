package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.util.Hashtable;

public class ParameterDecode implements Responder {
    private String body;
    private Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public ParameterDecode() {
        generateBody();
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();

        getParams((Hashtable<String, String>) req.get("Parameters"));

        resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
        resp.put("message-body", this.body);
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
        this.body = "<html><head><title></title></head><body> " + body + " </body></html>";
    }

    private void getParams(Hashtable body) {
        String newBody = " ";
        for(Object key: body.keySet()) {
            newBody = newBody.concat(key + " = " + body.get(key) + "\n");
        }
        echoBody(newBody);
    }
}
