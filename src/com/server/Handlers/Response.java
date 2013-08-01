package com.server.Handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class Response {
    private String httpVersion;
    private String statusCode;

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpVersion() {
        return "HTTP/" + httpVersion;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void writeTo(Hashtable<String, Object> request, OutputStream out) throws IOException {
        out.write((request.get("status-line") + "").getBytes(Charset.forName("utf-8")));
        out.write("\r\n".getBytes(Charset.forName("utf-8")));

        Hashtable<String, String> headers = (Hashtable<String, String>) request.get("message-header");

        for (String key: headers.keySet()) {
            String line = String.format("%s: %s", key, headers.get(key));
            out.write(line.getBytes(Charset.forName("utf-8")));
            out.write("\r\n".getBytes(Charset.forName("utf-8")));
        }

        String body = (String) request.get("message-body");
        out.write("\r\n".getBytes(Charset.forName("utf-8")));
        out.write((body).getBytes(Charset.forName("utf-8")));
        out.close();
    }
}
