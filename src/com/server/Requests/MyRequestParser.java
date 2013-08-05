package com.server.Requests;

import java.io.*;
import java.util.Hashtable;

public class MyRequestParser implements RequestParsers {
    Hashtable<String, String> statusLine = new Hashtable<String, String>();
    Hashtable<String, Object> messageHeader = new Hashtable<String, Object>();
    Hashtable<String, Object> messageBody = new Hashtable<String, Object>();
    BufferedReader theReader;

    public MyRequestParser(InputStream in) throws IOException {
        theReader = new BufferedReader(new InputStreamReader(in));
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

    public void parseMessageHeader() throws IOException {
        String[] items;
        String line;

        while (!(line = theReader.readLine()).equals("")) {
            items = line.split(": ");
            messageHeader.put(items[0], items[1]);
        }
        parseMessageBody();
    }

    public void parseMessageBody() throws IOException {
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
        } catch(NumberFormatException ignored){} catch (ArrayIndexOutOfBoundsException e){}
    }

    public Hashtable<String,Object> getMessageBody() {
        return messageBody;
    }
}