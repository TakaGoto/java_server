package com.server.Mocks;

import com.server.Handlers.Responder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class MockResponder implements Responder {
    Hashtable<String, Object> resp = new Hashtable<String, Object>();
    Hashtable<String, Object> header = new Hashtable<String, Object>();

    @Override
    public Hashtable<String, Object> respond(Hashtable<String, Object> req) throws IOException {
        resp.put("status-line", "HTTP/1.0 200 OK");
        if(req.get("Request-URI").equals("/redirect")) {
            header.put("Location", "http://localhost:5000/");
            resp.put("status-line", "HTTP/1.0 301 Moved Permanently");
            resp.put("message-header", header);
        }

        if(req.get("Method").equals("POST")) {
            resp.put("message-body", "data = cosby\r\n".getBytes(Charset.forName("utf-8")));
        }

        if(req.get("Request-URI").equals("/file1")) {
            resp.put("status-line", "HTTP/1.0 405 Method Not Allowed");
            if(req.get("Method").equals("POST")) {
                resp.put("message-body", "".getBytes(Charset.forName("utf-8")));
            }
        }
        return resp;
    }
}
