package com.server.Responses;

import java.util.Hashtable;

public class MessageHeader {
    private Hashtable<String, String> messageHeader = new Hashtable<String, String>();

    public MessageHeader(String type , int size) {
        messageHeader.put("Content-Type", type);
        messageHeader.put("Content-Length", String.valueOf(size));
        messageHeader.put("connection", "close");
        messageHeader.put("server", "Totoro/1.0");
    }

    public String getContentType() {
        return messageHeader.get("Content-Type");
    }

    public String getContentLength() {
        return messageHeader.get("Content-Length");
    }

    public String getConnection() {
        return messageHeader.get("connection");
    }

    public Hashtable<String, String> getMessageHeader() {
        return messageHeader;
    }

    public String getServer() {
        return messageHeader.get("server");
    }

    public void add(String key, String value) {
        messageHeader.put(key, value);
    }
}
