package com.server.Requests;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Hashtable;

public class ParameterDecoder {
    private static Hashtable<String, String> decodedParams;
    public static Hashtable<String, String> decode(String params) throws IOException {
        decodedParams = new Hashtable<String, String>();
        for(String param: params.split("&")) {
            String[] splitParam = param.split("=");
            if(splitParam.length == 2) {
                decodedParams.put(splitParam[0], URLDecoder.decode(splitParam[1], "utf-8"));
            }
        }
        return decodedParams;
    }
}
