package com.server.Handlers;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class ResponseTest {
    Response resp = new Response();

    @Test public void getHttpVersion() {
        resp.setHttpVersion("1.0");
        assertEquals("HTTP/1.0", resp.getHttpVersion());
    }

    @Test public void getStatusCode() {
        resp.setStatusCode("200");
        assertEquals("200", resp.getStatusCode());
    }

    @Test public void testWrite() throws IOException {
        Hashtable<String, Object> req = new Hashtable<String, Object>();
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Status-Code", "200");
        req.put("Reason-Phrase", "OK");
        req.put("Body", "<html><body>Tonorino Totoro</body></html>");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resp.write(req, out);
        assertEquals("HTTP/1.0 200 OK \r\n\r\n<html><body>Tonorino Totoro</body></html>", out.toString());
    }
}
