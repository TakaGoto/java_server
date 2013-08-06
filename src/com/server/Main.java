package com.server;


public class Main {
    private static int port = 5000;
    private static String rootDir = null;

    public static void main(String[] args) {
        parseArgs(args);
        Server server = new Server(port, rootDir);
        server.listen();
    }

    private static void parseArgs(String[] args) {
        try {
            port = Integer.parseInt(args[1]);
            rootDir = args[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Proper Usage: java -jar <your jar file> -p <port> -d <directory to serve>");
        }
    }
}
