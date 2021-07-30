/*
 * Server App upon TCP
 * A thread is created for each connection request from a client
 * So it can handle Multiple Client Connections at the same time
 * Andy Ngollo
 */

import java.net.*;
import java.io.*;

class TCPServer2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverTCPSocket = null;
        boolean listening = true;

        try {
            serverTCPSocket = new ServerSocket(5320);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5320.");
            System.exit(-1);
        }

        while (listening){
               new TCPMultiServerThread2(serverTCPSocket.accept()).start();
        }

        serverTCPSocket.close();
    }
}
