package com.server.Requests;

import com.server.HtmlGenerator;
import com.server.Responses.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class ResponseTest {
    Response resp = new Response();
    String body;

    @Before public void init() {
        body = HtmlGenerator.echoBody("Tonarino Totoro");
    }

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
        Hashtable<String, Object> header = new Hashtable<String, Object>();

        req.put("status-line", "HTTP/1.0 200 OK");
        req.put("message-header", header);
        req.put("message-body", body.getBytes(Charset.forName("utf-8")));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resp.writeTo(req, out);
        assertEquals("HTTP/1.0 200 OK\r\n\r\n<html><head><title></title></head><body> Tonarino Totoro </body></html>", out.toString());
    }

    @Test public void writeContainsMessageHeader() throws IOException {
        Hashtable<String, Object> req = new Hashtable<String, Object>();
        Hashtable<String, Object> header = new Hashtable<String, Object>();

        header.put("Content-Type", "text/html");
        req.put("status-line", "HTTP/1.0 200 OK");
        req.put("message-header", header);
        req.put("message-body", body.getBytes(Charset.forName("utf-8")));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resp.writeTo(req, out);
        assertEquals("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<html><head><title></title></head><body> Tonarino Totoro </body></html>", out.toString());
    }
}
