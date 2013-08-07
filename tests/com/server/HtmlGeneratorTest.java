package com.server;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HtmlGeneratorTest {
    @Test public void hasDefaultHTML() {
        assertEquals("<html><head><title></title></head><body> Empty </body></html>", HtmlGenerator.getDefaultHTML());
    }

    @Test public void echoBody() {
        assertEquals("<html><head><title></title></head><body> echo </body></html>", HtmlGenerator.echoBody("echo"));
    }

    @Test public void generateDirectoryList() {
        String body = HtmlGenerator.generateList();
        Assert.assertTrue(body.contains("/file1"));
        Assert.assertTrue(body.contains("/partial_content.txt"));
        Assert.assertTrue(body.contains("/image.jpeg"));
        Assert.assertTrue(body.contains("/image.png"));
        Assert.assertTrue(body.contains("/image.gif"));
    }
}
