package com.server.Handlers;

import java.io.*;
import java.util.Hashtable;

public class RequestParser {
    public Hashtable<String, Object> parseHeader(InputStream inputStream) throws IOException {
        Hashtable<String, Object> requestHeader = new Hashtable<String, Object>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        String[] items;

        items = line.split("\\s");
        requestHeader.put("Method", items[0]);
        requestHeader.put("Request-URI", items[1]);
        requestHeader.put("HTTP-Version", items[2]);

        while (!(line = reader.readLine()).equals("")) {
            items = line.split(": ");
            requestHeader.put(items[0], items[1]);
        }

        try {
            int length = Integer.parseInt((String) requestHeader.get("Content-Length"));
            char[] query = new char[length];
            reader.read(query);
            Hashtable<String, String> params = new Hashtable<String, String>();
            for(String param: String.valueOf(query).split("&")) {
                String[] theParams = param.split("=");
                params.put(theParams[0], theParams[1]);
                requestHeader.put("Body", params);
            }
        } catch(NumberFormatException e){}

        return requestHeader;
    }
}
