package com.server.Handlers;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestTest {
    Requests req;
    Hashtable<String, String> header = new Hashtable<String, String>();
    Hashtable<String, String> params = new Hashtable<String, String>();

    @Test public void requestReturnsGetMethod() {
        params.put("method", "GET");
        req = new Request(params, header, "");
        assertEquals("GET", req.getMethod());
    }

    @Test public void returnsPostMethod() {
        params.put("method", "POST");
        req = new Request(params, header, "");
        assertEquals("POST", req.getMethod());
    }

    @Test public void returnsPath() {
        params.put("path", "/");
        req = new Request(params, header, "");
        assertEquals("/", req.getPath());
    }

    @Test public void returnsPathAndMethod() {
        params.put("path", "/");
        params.put("method", "GET");
        req = new Request(params, header, "");
        assertEquals("/", req.getPath());
        assertEquals("GET", req.getMethod());
    }

    @Test public void returnsHTTPVersion() {
        params.put("httpVersion", "HTTP/1.0");
        req = new Request(params, header, "");
        assertEquals("HTTP/1.0", req.getHttpVersion());
    }

    @Test public void returnQueryStringParameter() {
        params.put("query", "Nihongo Wakaru?");
        req = new Request(params, header, "");
        assertEquals("Nihongo Wakaru?", req.getParam("query"));
    }

    @Test public void returnsMoveStringParam() {
        params.put("move", "five");
        req = new Request(params, header, "");
        assertEquals("five", req.getParam("move"));
    }

    @Test public void shouldPrintMethodAndPost() {
        params.put("path", "/");
        params.put("method", "GET");
        req = new Request(params, header, "");
        assertEquals("GET /", req.toString());
    }

    @Test public void returnsBody() {
        req = new Request(params, header, "");
        assertEquals("", req.getBody());
    }

    @Test public void returnsHeader() {
        header.put("Accept", "text/plain");
        req = new Request(params, header, "");
        assertEquals("text/plain", req.getHeader("Accept"));
    }
}
