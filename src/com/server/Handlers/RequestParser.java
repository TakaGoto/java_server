package com.server.Handlers;

import java.io.*;
import java.util.Hashtable;

public class RequestParser {
    InputStreamReader reader;
    Hashtable<String, String> statusLine = new Hashtable<String, String>();
    Hashtable<String, Object> messageHeader = new Hashtable<String, Object>();
    Hashtable<String, Object> messageBody = new Hashtable<String, Object>();
    BufferedReader theReader;

    public RequestParser(InputStreamReader reader) throws IOException {
        this.reader = reader;
        theReader = new BufferedReader(this.reader);
        parseStatusLine();
        parseMessageHeader();
    }

    public Hashtable<String, String> getStatusLine() {
        return statusLine;
    }

    public Hashtable<String, Object> getMessageHeader() {
        return messageHeader;
    }

    public void parseStatusLine() throws IOException {
        String line = theReader.readLine();
        String[] items;
        items = line.split("\\s");
        statusLine.put("Method", items[0]);
        statusLine.put("Request-URI", items[1]);
        statusLine.put("HTTP-Version", items[2]);
        statusLine.put("status-line", items[0] + " " + items[1] + " " + items[2]);
    }

    public InputStreamReader getReader() {
        return reader;
    }

    public void parseMessageHeader() throws IOException {
        String[] items;
        String line = theReader.readLine();

        while (!(line = theReader.readLine()).equals("")) {
            items = line.split(": ");
            messageHeader.put(items[0], items[1]);
        }

        Hashtable<String, String> params = new Hashtable<String, String>();

        try {
            int length = Integer.parseInt(String.valueOf(messageHeader.get("Content-Length")));
            char[] query = new char[length];
            theReader.read(query);
            params.put("length", String.valueOf(length));
            for(String param: String.valueOf(query).split("&")) {
                String[] theParams = param.split("=");
                params.put(theParams[0], theParams[1]);
                messageBody.put("Body", params);
            }
            int totalLength = 51 + length;
            messageBody.put("Content-Length", String.valueOf(totalLength));
        } catch(NumberFormatException e){}
    }

    public Hashtable<String,Object> getMessageBody() {
        return messageBody;
    }
}
