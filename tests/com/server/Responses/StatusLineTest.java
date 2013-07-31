package com.server.Responses;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StatusLineTest {
    StatusLine statusLine;
    @Test public void hasStatusAttributes() {
        statusLine = new StatusLine("200");
        assertEquals("OK", statusLine.getReasonPhrase());
        assertEquals("200", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void changeHttpVersion() {
        statusLine = new StatusLine("200", "HTTP/1.1");
        assertEquals("OK", statusLine.getReasonPhrase());
        assertEquals("200", statusLine.getCode());
        assertEquals("HTTP/1.1", statusLine.getHttpVersion());
    }

    @Test public void hasFourOhFourStatus() {
        statusLine = new StatusLine("404");
        assertEquals("Not Found", statusLine.getReasonPhrase());
        assertEquals("404", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void hasRedirectMovedPermanent() {
        statusLine = new StatusLine("301");
        assertEquals("Moved Permanently", statusLine.getReasonPhrase());
        assertEquals("301", statusLine.getCode());
        assertEquals("HTTP/1.0", statusLine.getHttpVersion());
    }

    @Test public void getStatusLineRedirect() {
        statusLine = new StatusLine("301");
        assertEquals("HTTP/1.0 301 Moved Permanently\r\n\r\n", statusLine.getStatusLine());
    }

    @Test public void getStatusLineTwoHundred() {
        statusLine = new StatusLine("200");
        assertEquals("HTTP/1.0 200 OK\r\n\r\n", statusLine.getStatusLine());
    }
}
