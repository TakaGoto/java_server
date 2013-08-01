package com.server.Handlers;

import java.io.*;
import java.util.Hashtable;

public class RequestParser {
    InputStreamReader reader;
    Hashtable<String, String> statusLine = new Hashtable<String, String>();
    Hashtable<String, String> messageHeader = new Hashtable<String, String>();

    public RequestParser(InputStreamReader reader) {
        this.reader = reader;
    }

    public Hashtable<String, String> getStatusLine() {
        return statusLine;
    }

    public Hashtable<String, Object> parseHeader(InputStream inputStream) throws IOException {
        Hashtable<String, Object> requestHeader = new Hashtable<String, Object>();
        Hashtable<String, String> params = new Hashtable<String, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        String[] items;

        items = line.split("\\s");
        requestHeader.put("Method", items[0]);
        requestHeader.put("Request-URI", items[1]);
        requestHeader.put("HTTP-Version", items[2]);
        requestHeader.put("Body", params);

        while (!(line = reader.readLine()).equals("")) {
            items = line.split(": ");
            requestHeader.put(items[0], items[1]);
        }

        try {
            int length = Integer.parseInt((String) requestHeader.get("Content-Length"));
            char[] query = new char[length];
            reader.read(query);
            params.put("length", String.valueOf(length));
            for(String param: String.valueOf(query).split("&")) {
                String[] theParams = param.split("=");
                params.put(theParams[0], theParams[1]);
                requestHeader.put("Body", params);
            }
            int totalLength = 51 + length;
            requestHeader.put("Content-Length", totalLength);
        } catch(NumberFormatException e){}


        return requestHeader;
    }

    public void parseStatusLine() throws IOException {
        BufferedReader reader = new BufferedReader(this.reader);
        String line = reader.readLine();
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
}
