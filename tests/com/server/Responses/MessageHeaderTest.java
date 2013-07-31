package com.server.Responses;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class MessageHeaderTest {
    MessageHeader messageHeader;

    @Test public void hasDefaultTypeLengthAndConnection() {
        messageHeader = new MessageHeader("text/html");
        assertEquals("text/html", messageHeader.getContentType());
        assertEquals("0", messageHeader.getContentLength());
        assertEquals("close", messageHeader.getConnection());
        assertEquals("Totoro/1.0", messageHeader.getServer());
    }

    @Test public void changeLength() {
        messageHeader = new MessageHeader("text/html", 5);
        assertEquals("text/html", messageHeader.getContentType());
        assertEquals("Totoro/1.0", messageHeader.getServer());
        assertEquals("5", messageHeader.getContentLength());
        assertEquals("close", messageHeader.getConnection());
    }

    @Test public void getMessageHeader() {
        messageHeader = new MessageHeader("text/html", 5);
        Hashtable<String, String> result = messageHeader.getMessageHeader();
        assertEquals("text/html", result.get("Content-Type"));
        assertEquals("5", result.get("Content-Length"));
        assertEquals("close", result.get("connection"));
    }
}
