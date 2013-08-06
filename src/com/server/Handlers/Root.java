package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.nio.charset.Charset;
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
            resp.put("message-body", body.getBytes(Charset.forName("utf-8")));
        return resp;
    }

    private void generateBody() {
        body = "<html><head><title></title></head><body>";
        body += "<a href=\"/file1\">file1</a></br>";
        body += "<a href=\"/file2\">file2</a></br>";
        body += "<a href=\"/image.gif\">image.gif</a></br>";
        body += "<a href=\"/image.jpeg\">image.jpeg</a></br>";
        body += "<a href=\"/image.png\">image.png</a></br>";
        body += "<a href=\"/text-file.txt\">text-file.txt</a></br>";
        body += "<a href=\"/partial_content.txt\">partial_content.txt</a></br>";
        body += "</body></html>";
    }

    public String getBody() {
        return body;
    }
}
