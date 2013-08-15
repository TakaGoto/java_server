package com.server.Requests;

import java.util.Hashtable;

public class RequestType {
    private Hashtable<String, Object> requestType = new Hashtable<String, Object>();

    public Hashtable<String, Object> getData() {
        return requestType;
    }

    public void put(String key, Object value) {
        requestType.put(key, value);
    }

    public String get(String key) {
        return (String) requestType.get(key);
    }
}
