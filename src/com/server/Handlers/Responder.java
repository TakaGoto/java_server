package com.server.Handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

public interface Responder {
    Hashtable<String,Object> respond(Hashtable<String, Object> req) throws IOException;
}
