package com.server.Requests;

import java.util.Hashtable;

public class MessageHeader {
    private Hashtable<String, Object> messageHeader = new Hashtable<String, Object>();

    public Hashtable<String, Object> getMessageHeader() {
        return messageHeader;
    }

    public void put(String key, Object value) {
        messageHeader.put(key, value);
    }

    public String get(String key) {
        return (String) messageHeader.get(key);
    }
}
