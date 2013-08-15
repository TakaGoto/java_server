package com.server.Requests;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class MessageHeaderTest {
    MessageHeader messageHeader;

    @Test public void getMessageHeader() {
        messageHeader = new MessageHeader();
        messageHeader.put("hello", "world");
        assertEquals("world", messageHeader.get("hello"));
    }

    @Test public void addNewField() {
        messageHeader = new MessageHeader();
        messageHeader.put("Location", "localhost:5000/redirect");
        Hashtable<String, Object> result = messageHeader.getMessageHeader();
        assertEquals("localhost:5000/redirect", result.get("Location"));
    }

    @Test public void getMessageHeaderIVar() {
        Hashtable<String, Object> empty = new Hashtable<String, Object>();
        messageHeader = new MessageHeader();
        assertEquals(empty.size(), messageHeader.getMessageHeader().size());
    }
}
