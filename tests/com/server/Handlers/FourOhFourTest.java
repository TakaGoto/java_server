package com.server.Handlers;

import org.junit.Test;

import java.io.IOException;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class FourOhFourTest {

    @Test public void returnsAFourOhFourResponse() throws IOException {
        Hashtable<String, Object> req = new Hashtable<String, Object>();
        req.put("HTTP-Version", "HTTP/1.0");
        FourOhFour fourOhFour = new FourOhFour();
        Hashtable<String, Object> response = fourOhFour.respond(req);
        assertEquals("HTTP/1.0 404 Not Found", response.get("status-line"));
    }
}
