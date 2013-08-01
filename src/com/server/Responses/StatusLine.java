package com.server.Responses;

public class StatusLine {
    private String code;
    private String httpVersion = "HTTP/1.0";

    public StatusLine(String code) {
        this.code = code;
    }

    public StatusLine(String code, Object httpVersion) {
        this.httpVersion = (String) httpVersion;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getReasonPhrase() {
        if(code.equals("200")) return "OK";
        if(code.equals("404")) return "Not Found";
        if(code.equals("301")) return "Moved Permanently";
        if(code.equals("405")) return "Method Not Allowed";
        return "";
    }

    public String getStatusLine() {
        return httpVersion + " " + code + " " + getReasonPhrase();
    }
}
