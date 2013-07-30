package com.server.Handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Hashtable;

public class Response implements Responses {
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

    public void write(Hashtable<String, String> request, OutputStream out) throws IOException {
        out.write((request.get("HTTP-Version") + " ").getBytes(Charset.forName("utf-8")));
        out.write((request.get("Status-Code") + " ").getBytes(Charset.forName("utf-8")));
        out.write((request.get("Reason-Phrase") + " ").getBytes(Charset.forName("utf-8")));
        out.write("\r\n".getBytes(Charset.forName("utf-8")));
        out.write("\r\n".getBytes(Charset.forName("utf-8")));
        out.write((request.get("Body")).getBytes(Charset.forName("utf-8")));
        out.close();
    }
}
