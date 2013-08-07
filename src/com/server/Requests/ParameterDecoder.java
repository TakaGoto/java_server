package com.server.Requests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterDecoder {
    private static Hashtable<String, String> decodedParams;

    public static Hashtable<String, String> decode(String params) throws IOException {
        decodedParams = new Hashtable<String, String>();
        for(String param: params.split("&")) {
            String[] splitParam = param.split("=");
            if(splitParam.length == 2) {
                decodedParams.put(splitParam[0], replaceHex(splitParam[1]));
            }
        }
        return decodedParams;
    }

    private static String replaceHex(String param) {
        Pattern pattern = Pattern.compile("%..");
        Matcher m = pattern.matcher(param);
        while(m.find()) {
            String hex = m.group().substring(1);
            int character = Integer.parseInt(hex, 16);
            param = param.replaceAll("%" + hex, "\\" + String.valueOf((char)(character)));
        }
        return param;
    }
}
