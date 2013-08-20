package com.server.Mocks;

import com.server.Requests.IRequestParsers;
import com.server.Requests.MessageHeader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class MockRequestParser implements IRequestParsers {
    Hashtable<String, Object> statusLine = new Hashtable<String, Object>();
    Hashtable<String, Object> messageHeader = new Hashtable<String, Object>();
    Hashtable<String, Object> messageBody = new Hashtable<String, Object>();

    public MockRequestParser(InputStream in) {
    }

    public void parseStatusLine() throws IOException {
    }

    public void parseMessageHeader() throws IOException {
    }

    public Hashtable<String, Object> getStatusLine() {
        statusLine.put("Method", "GET");
        statusLine.put("Request-URI", "/");
        statusLine.put("HTTP-Version", "HTTP/1.0");
        statusLine.put("status-line", "GET / HTTP/1.0");
        return statusLine;
    }

    public Hashtable getMessageHeader() {
        messageHeader.put("Content-Length", "61");
        return messageHeader;
    }

    public Hashtable<String, Object> getMessageBody() {
        Hashtable<String, String> body = new Hashtable<String, String>();
        body.put("data", "cosby");
        messageBody.put("Body", body);
        return messageBody;
    }
}
