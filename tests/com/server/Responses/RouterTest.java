package com.server.Responses;

import com.server.Handlers.Responder;
import com.server.Mocks.MockResponder;
import com.server.Responses.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    Router router = new Router();
    Hashtable<String, Object> request= new Hashtable<String, Object>();
    Hashtable<String, String> body = new Hashtable<String, String>();
    Hashtable<String, Object> response;
    Hashtable<String, String> header;
    Responder mockResponder = new MockResponder();

    @Before public void init() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Host", "http://localhost:5000");
        body.put("data", "cosby");
        body.put("length", "5");
        request.put("Body", body);
    }

    @Test public void routeNotFound() throws IOException {
        request.put("Request-URI", "/wrong_uri");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 404 Not Found", response.get("status-line"));
    }

    @Test public void addRoute() throws IOException {
        router.addRoute("/", mockResponder);
        request.put("Request-URI", "/");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
    }

    @Test public void routeFormShouldBeTwoHundred() throws IOException {
        router.addRoute("/form", mockResponder);
        request.put("Request-URI", "/form");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
    }

    @Test public void routeRedirectsToRoot() throws IOException {
        router.addRoute("/redirect", mockResponder);
        request.put("Request-URI", "/redirect");
        request.put("Method", "GET");
        response = router.route(request);
        header = (Hashtable<String, String>) response.get("message-header");
        assertEquals("HTTP/1.0 301 Moved Permanently", response.get("status-line"));
        assertEquals("http://localhost:5000/", header.get("Location"));
    }

    @Test public void postDataToBody() throws IOException {
        router.addRoute("/form", mockResponder);
        request.put("Request-URI", "/form");
        request.put("Method", "POST");
        response = router.route(request);
        String newBody = new String((byte[]) response.get("message-body"), "UTF-8");
        assertEquals("data = cosby\r\n", newBody);
    }

    @Test public void FourOhFiveMethodNotAllowed() throws IOException {
        router.addRoute("/file1", mockResponder);
        request.put("Request-URI", "/file1");
        request.put("Method", "PUT");
        response = router.route(request);
        assertEquals("HTTP/1.0 405 Method Not Allowed", response.get("status-line"));
    }

    @Test public void postRequestWithoutBody() throws IOException {
        router.addRoute("/file1", mockResponder);
        request.put("Request-URI", "/file1");
        request.put("Method", "POST");
        request.remove("Body");
        response = router.route(request);
        String body = new String((byte[]) response.get("message-body"), "UTF-8");
        assertEquals("", body);
    }

    @Test public void textFileIsFourOhFive() throws IOException {
        request.put("Request-URI", "/text-file.txt");
        request.put("Method", "PUT");
        request.remove("Body");
        response = router.route(request);
        String body = new String((byte[]) response.get("message-body"), "UTF-8");
        assertEquals("404", body);
    }

    @Test public void routerHasParameterDecode() {
        router.addRoute("/parameters", mockResponder);
        assertEquals(true, router.getRoutes().containsKey("/parameters"));
    }
}
