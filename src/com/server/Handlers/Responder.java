package com.server.Handlers;

import java.util.Hashtable;

public interface Responder {
    Hashtable<String,Object> respond(Hashtable<String, Object> req);
}
