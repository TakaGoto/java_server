package com.server.Handlers;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class PutPostTest {
    PutPost putPost;
    Hashtable<String, Object> req = new Hashtable<String, Object>();
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    @Test public void testGetPutGetPost() {
        Hashtable<String, String> body = new Hashtable<String, String>();
        body.put("data", "cosby");
        body.put("length", "5");
        putPost = new PutPost();
        req.put("Request-URI", "/form");
        req.put("Method", "POST");
        req.put("Body", body);
        resp = putPost.respond(req);
        assertEquals("<html><head><title></title></head><body> data = cosby </body></html>", resp.get("message-body"));
    }
}
