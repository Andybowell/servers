*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * 
 */ 

import java.net.*;
import java.io.*;

public class TCPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;

    public TCPMultiServerThread(Socket socket) {
        super("TCPMultiServerThread");
        clientTCPSocket = socket;
    }

    // 1. Listen to the given port and wait for a connection request from a HTTP Client.
    // 2. Create a new thread for every incoming TCP connection request from a HTTP client.
    // 3. Read, display to the standard output, and interpret incoming HTTP request message line by line
    // o  If the method given in the request line is NOT “GET”, it is a “400 Bad Request” case
    // o  If the file specified in the URL does not exit/cannot be open, it is a “404 Not Found” case
    // o  Otherwise, it is a “200 OK” case
    // 4. According to the case discovered above

    public static void main(String[] args) throws IOException {

        try {
            PrintWriter cSocketOut = new PrintWriter(
                clientTCPSocket.getOutputStream(), true);

            BufferedReader cSocketIn = new BufferedReader(
                new InputStreamReader(clientTCPSocket.getInputStream()));

            String fromClient, toClient;
            int count = 0;

            while ((fromClient = cSocketIn.readLine()) != null) {
   
                System.out.println("Count = "+ (count++) + " Line = " + fromClient);

           }
                        
           cSocketOut.close();
           cSocketIn.close();
           clientTCPSocket.close();

       } catch (IOException e) {
           e.printStackTrace();
       }

    }



    public void run() {


    }
}