package com.server;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HtmlGeneratorTest {
    @Test public void hasDefaultHTML() {
        assertEquals("<html><head><title></title></head><body> Empty </body></html>", HtmlGenerator.getDefaultHTML());
    }

    @Test public void echoBody() {
        assertEquals("<html><head><title></title></head><body> echo </body></html>", HtmlGenerator.echoBody("echo"));
    }
}
