/*
 * Client App upon TCP
 *
 * Andy Ngollo
 *
 * Ahmed Hussein
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
        String dnsName = "";
        Scanner scan = new Scanner(System.in);

        // Display messages on the standard output to ask the user to input the 
        // DNS name/ip of your HTTP server
        System.out.println("Enter the DNS name or ip of your HTTP server: ");



        // 2. Buildup the TCP connection to your HTTP server with the Host Name input by 
        // User at the given port     
        try {

            // Start timer 
            long startTime = System.currentTimeMillis();

                dnsName = new Scanner(System.in).nextLine();

                tcpSocket = new Socket(dnsName, 5320);


            // Record time
            long stopTime = System.currentTimeMillis();

            // Display the RTT of establishing this TCP connection in millisecond     
            long RTT = stopTime - startTime ;

            System.out.println("RTT = " + RTT + " ms");
	
	    socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + dnsName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + dnsName);
            System.exit(1);
        }

        // Display messages on the standard output to ask the user to 
        // input the HTTP method type, name of the htm file requested, HTTP Version, and 	User-Agent, 
        // respectively (separately please!). (hint: all inputs can be strings.)

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer, methodType, htmFile, httpVersion, userAgent;
        String fromUser;


        System.out.println("Enter your HTTP method type: ");
        methodType = scan.nextLine();

        System.out.println("Enter the name of htm file: ");
        htmFile = scan.nextLine();

        System.out.println("Enter the name of HTTP Version: ");
        httpVersion = scan.nextLine();

        System.out.println("Enter the name of User-Agent: ");
        userAgent = scan.nextLine();
	System.out.println(" ");



       /* Use the above inputs from user to construct ONE HTTP request message and send it 	to the HTTP server program over the TCP
        connection. Your HTTP request message only needs to include the following lines. 	(Hint: At the end of each line including
        the last empty line, a “\r\n” is needed. The correctness of the format will be 		checked by the instructor.):
	The request line (hint: the URL should include a ‘/’ in front of the htm file 		name)
        The header line for the field “Host:”
        The header line for the field “User-Agent:”
        <empty line>*/


        String fromUser = methodType + " /" + htmFile +" "+  httpVersion + "\r\n"+
                             "Host: " + dnsName + "\r\n"+
                             "User-Agent: " + userAgent + "\r\n";

        // Send HTTP Request
	
	// Start timer 
            long startTime1 = System.currentTimeMillis();


                System.out.println("Client: " + fromUser);
                socketOut.println(fromUser);

       /* Receive and interpret the HTTP response message from the HTTP Server program 		line by line, display the RTT (File
        Transmission Time may be included) of HTTP query in millisecond (the difference 	between the moment right before HTTP
        request is sent and the moment right after HTTP response is received) as a single 	line (e.g., RTT = 1.089 ms), display the status
        line and header lines of the HTTP response message on the standard output, and 		save the data in the entity body to a .htm file
        to local directory if there is any. (Hint: (a) When one empty string “” (NOT a 		null string!) is read the FIRST TIME, it indicates
        the header lines are over and the entity body is going to start next line if the 	case is “200 OK”.)*/



        String fromServer = "";
        while ((fromServer = socketIn.readLine()) != null) {

            System.out.println(fromServer);
	    System.out.println(input.nextLine());
            

	 // Record time
            long stopTime1 = System.currentTimeMillis();

            // Display the RTT of establishing this TCP connection in millisecond     
            long RTT2 = stopTime1 - startTime1 ;

            System.out.println("RTT = " + RTT2 + " ms");

        /* Display a message on the standard output to ask the User whether to continue. 	If yes, repeat steps 3 through 6. Otherwise, close
        all i/o streams, TCP connection, and terminate the Client program.*/


 	System.out.println("Do you want to Continue?(Y/N) ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        //
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES"){

                   sysIn = new BufferedReader(new InputStreamReader(System.in));
                   System.out.println("Enter your HTTP method type: ");
                    methodType = scan.nextLine();

                   System.out.println("Enter the name of htm file: ");
                   htmFile = scan.nextLine();

                   System.out.println("Enter the name of HTTP Version: ");
                   httpVersion = scan.nextLine();

                   System.out.println("Enter the name of User-Agent: ");
                   userAgent = scan.nextLine();
		   System.out.println(" ");

                }
       else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO")){
            break;
        }
        }
        socketOut.close();
        socketIn.close();
        scan.close();
        tcpSocket.close();

    }
}

