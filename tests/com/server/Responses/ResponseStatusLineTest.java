package com.server.Responses;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ResponseStatusLineTest {
    ResponseStatusLine statusLine;

    @Test public void hasStatusAttributes() {
        statusLine = new ResponseStatusLine("200");
        assertEquals("OK", statusLine.getReasonPhrase());
        assertEquals("200", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void changeHttpVersion() {
        statusLine = new ResponseStatusLine("200", "HTTP/1.1");
        assertEquals("OK", statusLine.getReasonPhrase());
        assertEquals("200", statusLine.getCode());
        assertEquals("HTTP/1.1", statusLine.getHttpVersion());
    }

    @Test public void hasFourOhFourStatus() {
        statusLine = new ResponseStatusLine("404");
        assertEquals("Not Found", statusLine.getReasonPhrase());
        assertEquals("404", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void hasRedirectMovedPermanent() {
        statusLine = new ResponseStatusLine("301");
        assertEquals("Moved Permanently", statusLine.getReasonPhrase());
        assertEquals("301", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void getStatusLineRedirect() {
        statusLine = new ResponseStatusLine("301");
        assertEquals("HTTP/1.0 301 Moved Permanently", statusLine.getStatusLine());
    }

    @Test public void getStatusLineTwoHundred() {
        statusLine = new ResponseStatusLine("200");
        assertEquals("HTTP/1.0 200 OK", statusLine.getStatusLine());
    }

    @Test public void getStatusLineFourOhFive() {
        statusLine = new ResponseStatusLine("405");
        assertEquals("HTTP/1.0 405 Method Not Allowed", statusLine.getStatusLine());
    }

    @Test public void getDefaultStatusPhrase() {
        statusLine = new ResponseStatusLine("600");
        assertEquals("HTTP/1.0 600 null", statusLine.getStatusLine());
    }

    @Test public void addStatusCode() {
        statusLine = new ResponseStatusLine("900");
        statusLine.addStatus("900", "testing");
        assertEquals("testing", statusLine.getReasonPhrase());
    }
}
