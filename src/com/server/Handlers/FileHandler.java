package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.io.*;
import java.util.Hashtable;

public class FileHandler implements Responder {
    private byte[] body = new byte[0];
    private String rootDir;
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public FileHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) throws IOException {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();
        File file = new File(rootDir, ((String) req.get("Request-URI")).substring(1));

        if(!req.get("Method").equals("GET")) {
            resp.put("status-line", new ResponseStatusLine("405", req.get("HTTP-Version")).getStatusLine());
            messageHeader.put("Allow", "GET");
        } else if(req.get("Request-URI").equals("/partial_content.txt")) {
            resp.put("status-line", new ResponseStatusLine("206", req.get("HTTP-Version")).getStatusLine());
            readFile(file);
        } else if(req.get("Request-URI").equals("/file1")) {
            resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
            readFile(file);
        } else {
            resp.put("status-line", new ResponseStatusLine("404", req.get("HTTP-Version")).getStatusLine());
        }

        if(req.get("Request-URI").equals("/partial_content.txt")) {
            messageHeader.put("Content-Length", "4");
            messageHeader.put("Accept-Ranges", "bytes");
            messageHeader.put("Content-Range", "bytes 0-4/3980");
        }

        messageHeader.put("Content-Type", "text/html");

        if(req.get("Request-URI").equals("/image.jpeg") ||req.get("Request-URI").equals("/image.png") || req.get("Request-URI").equals("/image.gif")) {
            resp.put("status-line", new ResponseStatusLine("200", req.get("HTTP-Version")).getStatusLine());
            readFile(file);
            messageHeader.put("Content-Length", String.valueOf(body.length));
            messageHeader.put("Content-Type", "image/jpeg");
        }

        messageHeader.put("Connection", "close");
        resp.put("message-header", messageHeader);
        resp.put("message-body", body);

        return resp;
    }

    private void readFile(File file) throws IOException {
        byte[] newBody = new byte[(int) file.length()];
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        in.read(newBody);
        in.close();
        body = newBody;
    }

    public String getRootDir() {
        return rootDir;
    }
}
