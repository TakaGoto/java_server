package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class FourOhFour implements Responder{
    Hashtable<String, Object> response = new Hashtable<String, Object>();
    Hashtable<String, Object> header = new Hashtable<String, Object>();

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) throws IOException {
        response.put("status-line", ResponseStatusLine.get("404", req.get("HTTP-Version")));
        response.put("message-header", header);
        response.put("message-body", "404".getBytes(Charset.forName("utf-8")));
        return response;
    }
}
