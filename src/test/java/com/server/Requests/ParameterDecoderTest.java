package com.server.Requests;

import org.junit.Test;

import java.io.IOException;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class ParameterDecoderTest {
    ParameterDecoder parameterDecoder = new ParameterDecoder();
    @Test public void decodesParameter() throws IOException {
        String params = "variable_1=Operators%20%3C%2C%20%3" +
                        "E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-" +
                        "%2C%20*%2C%20%26%2C%20%40%2C%20%23%2" +
                        "C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20" +
                        "that%20all%22%3F&variable_2=stuff";
        Hashtable<String, String> decodedParams =  ParameterDecoder.decode(params);
        assertEquals("Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\r\n", decodedParams.get("variable_1"));
        assertEquals("stuff\r\n", decodedParams.get("variable_2"));
    }

    @Test public void decodePlusOperator() throws IOException {
        String params = "taka+goto=japanese+man&hello+world=my+house";
        Hashtable<String, String> decodedParams =  ParameterDecoder.decode(params);
        assertEquals("japanese man\r\n", decodedParams.get("taka goto"));
        assertEquals("my house\r\n", decodedParams.get("hello world"));
    }
}
