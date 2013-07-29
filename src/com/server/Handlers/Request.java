package com.server.Handlers;

import java.util.Hashtable;

public class Request implements Requests {
    Hashtable<String, String> params;
    Hashtable<String, String> header;
    private String method;
    private String path;
    private String httpVersion;
    private String body;

    public Request(Hashtable<String, String> params,
                   Hashtable<String, String> header,
                   String body) {

        this.params = params;
        this.body = body;
        this.header = header;

        method = params.get("method");
        path = params.get("path");
        httpVersion = params.get("httpVersion");
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getParam(String param) {
        return params.get(param);
    }

    public String getBody() {
        return body;
    }

    public String getHeader(String header) {
        return this.header.get(header);
    }

    public String toString() {
        return method + " " + path;
    }
}
