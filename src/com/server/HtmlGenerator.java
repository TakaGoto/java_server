package com.server;

public class HtmlGenerator {
    private static String defaultHTML = "<html><head><title></title></head><body> Empty </body></html>";

    public static String getDefaultHTML() {
        return defaultHTML;
    }

    public static String echoBody(String body) {
        return "<html><head><title></title></head><body> " + body + " </body></html>";
    }

    public static String generateList() {
        String body;
        body = "<html><head><title></title></head><body>";
        body += "<a href=\"/file1\">file1</a></br>";
        body += "<a href=\"/file2\">file2</a></br>";
        body += "<a href=\"/image.gif\">image.gif</a></br>";
        body += "<a href=\"/image.jpeg\">image.jpeg</a></br>";
        body += "<a href=\"/image.png\">image.png</a></br>";
        body += "<a href=\"/text-file.txt\">text-file.txt</a></br>";
        body += "<a href=\"/partial_content.txt\">partial_content.txt</a></br>";
        body += "</body></html>";
        return body;
    }
}
