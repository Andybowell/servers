import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.net.ServerSocket;

public class TCPMultiServerThread2 extends Thread {
  private Socket clientTCPSocket = null;


 public TCPMultiServerThread2(Socket socket) {super("TCPMultiServerThread2");
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

 String fromClient, toClient;
 String url = "CS3700.html";
// int count = 0; 




 // 3. Read, display to the standard output, and interpret incoming HTTP request message line by line
 // o If the method given in the request line is NOT “GET”, it is a “400 Bad Request” case
 // o If the file specified in the URL does not exit/cannot be open, it is a “404 Not Found” case
 // o Otherwise, it is a “200 OK” case



 String[] messsages = new String[15];
 int i = 0;
 while (true) {
     fromClient = cSocketIn.readLine();
 messsages[i++] = fromClient;

 if (fromClient.equals(""))
 break;
 }
// System.out.println("count = " + (count++)); 
 System.out.println("fromClient = "+fromClient);



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

 if (!messsages[0].contains("GET")) {
 toClient = "HTTP/1.1 400 Bad Request" + "\r\n";
 }
 else if(!messsages[0].contains(url)){
 toClient = "HTTP/1.1 404 Not Found" + "\r\n";
 }
 else{


 toClient = ("HTTP/1.1 200 Ok".concat("\r\n")
 + "date: ".concat(new Date().toString()).concat("\r\n")
 + "server: ".concat("cs700a.msudenver.edu" + "\r\n"));
 System.out.println("");

 File file = new File("CS3700.html");
 Scanner scan = new Scanner(file);
 while (scan.hasNextLine()) {
 toClient = toClient + scan.nextLine()+ "\r\n";
 }
 }
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

