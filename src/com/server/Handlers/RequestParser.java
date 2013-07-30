package com.server.Handlers;

import java.io.*;
import java.util.Hashtable;

public class RequestParser {
    public Hashtable<String, String> parseHeader(InputStream inputStream) throws IOException {
        Hashtable<String, String> requestHeader = new Hashtable<String, String>();
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

        return requestHeader;
    }
}
