package com.server.Handlers;

import com.server.Responses.ResponseStatusLine;

import java.io.*;
import java.util.Hashtable;

public class FileHandler implements Responder {
    private String body;
    private String rootDir;
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    public FileHandler(String rootDir) {
        this.rootDir = rootDir;
        generateBody();
    }

    public Hashtable<String, Object> respond(Hashtable<String, Object> req) throws IOException {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();
        File file = new File(rootDir, ((String) req.get("Request-URI")).substring(1));

        if(!req.get("Method").equals("GET")) {
            resp.put("status-line", new ResponseStatusLine("405", req.get("HTTP-Version")).getStatusLine());
            messageHeader.put("Allow", "GET");
        } else if(file.isFile()) {
            resp.put("status-line", new ResponseStatusLine("206", req.get("HTTP-Version")).getStatusLine());
            readFile(file);
        } else {
            resp.put("status-line", new ResponseStatusLine("404", req.get("HTTP-Version")).getStatusLine());
        }

        messageHeader.put("Content-Type", "text/html");
        messageHeader.put("Content-Length", "4");
        messageHeader.put("Accept-Ranges", "bytes");
        messageHeader.put("Content-Range", "bytes 0-4/3980");
        messageHeader.put("Connection", "close");
        resp.put("message-header", messageHeader);
        resp.put("message-body", body);

        return resp;
    }

    private void generateBody() {
        body = "<html><head><title></title></head><body> Empty </body></html>";
    }

    private void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            body = sb.toString();
        } finally {
            br.close();
        }
    }

    public String getRootDir() {
        return rootDir;
    }
}
