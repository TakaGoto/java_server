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
        Hashtable messageHeader = new Hashtable<String, String>();
        String statusLine;
        File file = new File(rootDir, ((String) req.get("Request-URI")).substring(1));

        if(!req.get("Method").equals("GET")) {
            statusLine = ResponseStatusLine.get("405", req.get("HTTP-Version"));
            messageHeader.put("Allow", "GET");
        } else if(file.isFile()) {
            statusLine = getStatusLine(req);
            readFile(file);
            messageHeader = getMessageHeader(req);
        } else {
            statusLine = ResponseStatusLine.get("404", req.get("HTTP-Version"));
        }

        messageHeader.put("Connection", "close");
        resp.put("status-line", statusLine);
        resp.put("message-header", messageHeader);
        resp.put("message-body", body);

        return resp;
    }

    private Hashtable getMessageHeader(Hashtable req) {
        Hashtable<String, Object> header = new Hashtable<String, Object>();
        if(req.get("Request-URI").equals("/partial_content.txt")) {
            String[] range = getRange((String) req.get("Range"));
            header.put("Content-Range", "bytes " + range[0] + "-" + range[1]);
            header.put("Content-Length", range[1]);
            header.put("Accept-Ranges", "bytes");
            header.put("Content-Type", "text/html");
        } else {
            header.put("Content-Length", String.valueOf(body.length));
            header.put("Content-Type", "image/jpeg");
        }
        return header;
    }

    private String[] getRange(String range) {
        String[] newRange = range.split("=");
        newRange = newRange[1].split("-");
        return newRange;
    }

    private String getStatusLine(Hashtable req) {
        if(!req.get("Request-URI").equals("/partial_content.txt")) {
            return ResponseStatusLine.get("200", req.get("HTTP-Version"));
        } else {
            return ResponseStatusLine.get("206", req.get("HTTP-Version"));
        }
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
