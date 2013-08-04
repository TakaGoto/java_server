package com.server.Responses;

import java.util.Hashtable;

public class ResponseStatusLine {
    Hashtable<String, String> status = new Hashtable<String, String>();
    private String code;
    private String httpVersion = "HTTP/1.0";

    public ResponseStatusLine(String code) {
        this.code = code;
        setUpStatus();
    }

    public ResponseStatusLine(String code, Object httpVersion) {
        this.httpVersion = (String) httpVersion;
        this.code = code;
        setUpStatus();
    }

    public String getCode() {
        return code;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getReasonPhrase() {
        return status.get(code);
    }

    public String getStatusLine() {
        return httpVersion + " " + code + " " + getReasonPhrase();
    }

    public void addStatus(String code, String phrase) {
        status.put(code, phrase);
    }

    private void setUpStatus() {
        status.put("200", "OK");
        status.put("301", "Moved Permanently");
        status.put("404", "Not Found");
        status.put("405", "Method Not Allowed");
    }
}
