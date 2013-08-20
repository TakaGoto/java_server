package com.server.Requests;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RequestTypeTest {
    RequestType messageHeader;

    @Test public void getMessageHeader() {
        messageHeader = new RequestType();
        messageHeader.put("hello", "world");
        assertEquals("world", messageHeader.get("hello"));
    }

    @Test public void addNewField() {
        messageHeader = new RequestType();
        messageHeader.put("Location", "localhost:5000/redirect");
        Hashtable<String, Object> result = messageHeader.getData();
        assertEquals("localhost:5000/redirect", result.get("Location"));
    }

    @Test public void getMessageHeaderIVar() {
        Hashtable<String, Object> empty = new Hashtable<String, Object>();
        messageHeader = new RequestType();
        assertEquals(empty.size(), messageHeader.getData().size());
    }
}
