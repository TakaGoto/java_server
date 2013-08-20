package com.server.Requests;

import java.io.*;
import java.util.Hashtable;

public class MyRequestParser implements IRequestParsers {
    RequestType statusLine = new RequestType();
    RequestType messageHeader = new RequestType();
    RequestType messageBody = new RequestType();
    BufferedReader theReader;

    public MyRequestParser(InputStream in) throws IOException {
        theReader = new BufferedReader(new InputStreamReader(in));
        parseStatusLine();
        parseMessageHeader();
    }

    public void parseStatusLine() throws IOException {
        String line = theReader.readLine();
        String[] items;
        items = line.split("\\s");
        statusLine.put("Method", items[0]);
        statusLine.put("HTTP-Version", items[2]);

        items = items[1].split("\\?");
        statusLine.put("Request-URI", items[0]);
        if(items.length > 1) statusLine.put("Parameters", ParameterDecoder.decode(items[1]));

        statusLine.put("status-line", createStatusLine(statusLine.getData()));
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
            createMessageBody(params, query);
            int totalLength = 51 + length;
            messageBody.put("Content-Length", String.valueOf(totalLength));
        } catch(NumberFormatException ignored){} catch (ArrayIndexOutOfBoundsException e){}
    }

    private void createMessageBody(Hashtable params, char[] query) {
        for(String param: String.valueOf(query).split("&")) {
            String[] theParams = param.split("=");
            params.put(theParams[0], theParams[1]);
            messageBody.put("Body", params);
        }
    }

    private String createStatusLine(Hashtable statusLine) {
        return statusLine.get("Method") + " " +
                statusLine.get("Request-URI") + " " +
                statusLine.get("HTTP-Version");
    }

    public Hashtable<String,Object> getMessageBody() {
        return messageBody.getData();
    }

    public Hashtable getStatusLine() {
        return statusLine.getData();
    }

    public Hashtable getMessageHeader() {
        return messageHeader.getData();
    }

}
