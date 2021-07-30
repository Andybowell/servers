import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.net.ServerSocket;

public class SMTPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;


    public SMTPMultiServerThread(Socket socket) {
        super("SMTPMultiServerThread");
        clientTCPSocket = socket;
    }

    public void run() {

        try {


            // we get character output stream to client (for headers)
            PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
            // we read characters from the client via input stream on the socket
            BufferedReader cSocketIn = new BufferedReader(new InputStreamReader(clientTCPSocket.getInputStream()));
            //get binary output stream to client (for requested data)
            //BufferedOutStream dataOut = new BufferOutputStream(connect.getOutputStream());

            cSocketOut.println("220 cs3700b.msudenver.edu" + "\r\n");
            cSocketOut.flush();

            String fromClient = "", toClient="";
            String url = "CS3700.html";
            // int count = 0; 


            // a. Wait for, read, and display the “HELO …” command from the SMTP client. If the incoming command is NOT “HELO
            // …”, sends “503 5.5.2 Send hello first” response to the SMTP client and repeat step 3.a
            fromClient = cSocketIn.readLine();
            while (!fromClient.contains("HELO")) {
                cSocketOut.println("503 5.5.2 Send hello first");
                fromClient = cSocketIn.readLine();
            }
            System.out.println("fromClient = " + fromClient);

            // b. Send the “250 <server’s ip> Hello <client’s ip>” response to the SMTP client
            cSocketOut.println("250 147.153.10.87 Hello 147.153.10.69");

            
            // c.Wait for, read, and display the “MAIL FROM: …” command from the SMTP client. If the incoming command is NOT
            // “MAIL FROM: …”, sends “503 5.5.2 Need mail command” response to the SMTP client and repeat step 3.c.
            fromClient = cSocketIn.readLine();
            while (!fromClient.contains("MAIL FROM")) {
                cSocketOut.println("503 5.5.2 Need mail command");
                fromClient = cSocketIn.readLine();
            }
            System.out.println("fromClient = " + fromClient);

            cSocketOut.println("250 2.1.0 Sender OK");


            // 4. According to the case discovered above, construct ONE HTTP response message and send it to the HTTP client program over
            // the TCP connection. Your HTTP response message needs include the following lines using the HTTP message format. (Hint:
            // At the end of each line including those empty lines, a “\r\n” is needed.)
            // The status line
            // The header line for the field “Date:”
            // The header line for the field “Server: ”, you may use any value of your choice
            // <empty line>
            // Data read from the requested HTML file line by line … (hint: for the 200 OK case only)
            // <empty line>
            // <empty line>
            // <empty line>
            // <empty line>

            // if (!messsages[0].contains("GET")) {
            //     toClient = "HTTP/1.1 400 Bad Request" + "\r\n";
            // } else if (!messsages[0].contains(url)) {
            //     toClient = "HTTP/1.1 404 Not Found" + "\r\n";
            // } else {



            //}


            cSocketOut.println(toClient);

            cSocketOut.close();
            cSocketIn.close();
            clientTCPSocket.close();

            //while ((fromClient = cSocketIn.readLine()) != null){
            //terminate the server if client sends exit request


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}