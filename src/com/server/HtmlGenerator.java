package com.server;

public class HtmlGenerator {
    private static String defaultHTML = "<html><head><title></title></head><body> Empty </body></html>";

    public static String getDefaultHTML() {
        return defaultHTML;
    }

    public static String echoBody(String body) {
        return "<html><head><title></title></head><body> " + body + " </body></html>";
    }
}
