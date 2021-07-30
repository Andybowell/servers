/*
 * Client App upon SMTP
 *
 * Andy Ngollo
 *
 * Ahmed Hussein
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class SMTPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
        String hostName = "";
        Scanner scan = new Scanner(System.in);

        // 1.Display a message to ask the user to input the Host Name (or ip-address) of your SMTP server. 
        // Host Name/ip of your SMTP server
        System.out.println("Enter the Host Name or ip of your SMTP server: ");



        // 2. Buildup the TCP connection to your SMTP server with the Host Name input by 
        // User at the given port     
        try {
            hostName = new Scanner(System.in).nextLine();
            tcpSocket = new Socket(hostName, 5320);

            socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostName);
            System.exit(1);
        }

        while (true) {

           /* Buildup the TCP connection to your SMTP server with the Host Name input by User at the given port. Catch the exception,
            terminate the program, and display error messages on the standard output if any. Wait for, read, and display the “220” response
            from the SMTP server.
            */

            BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer, senderEmail, receiverEmail, subject, emailContents;
            String fromUser;

            while ((fromServer = socketIn.readLine()) != null) {


                System.out.println("");
                System.out.println(fromServer);
                System.out.println("");

                /* Display prompt messages on the standard output to ask the user to input sender’s email address, receiver’s email address,
                subject, and email contents, respectively. Since there may be multiple lines in email contents, the prompt message displayed
                by your Client program MUST prompt the ending signature pattern like “.” on a line by itself.*/

                System.out.println("Enter Sender's email address: ");
                senderEmail = scan.nextLine();
                fromUser ="SENDER: " + senderEmail + "\r\n";

                System.out.println("Enter Receiver's email address: ");
                receiverEmail = scan.nextLine();
                fromUser = "RECEIVER: " + receiverEmail + "\r\n ";
                
                System.out.println("Enter the Email Contents: ");
                emailContents = scan.nextLine();
                fromUser = "EMAIL: " + emailContents + "\r\n" + "." ;
                System.out.println(" ");



            /*Use the user inputs collected above in Step 3 for the following 3-phase data transfer procedure (see step 3 on server side). In
                each of the following steps a. through e., display the RTT (round-trip-time) of each conversation in millisecond (e.g., RTT =
                212.08 ms).
                a. Send the “HELO <sender’s mail server domain name>” command (e.g., HELO xyz.com) to the SMTP server program,
                wait for server’s response and display it on the standard output.
                b. Send the “MAIL FROM: <sender’s email address>” command to SMTP server, wait for SMTP server’s response and
                display it on the standard output.
                c. Send the “RCPT TO: <receiver’s email address>” command to the SMTP server program, wait for SMTP server’s response
                and display it on the standard output.
                d. Send the “DATA” command to the SMTP server program, wait for SMTP server’s response and display it on the standard
                output.
                e. Send the Mail message to the SMTP server. The format of this Mail message MUST follow the format detailed on the
                slide titled “Mail message format”. Wait for SMTP server’s response and display it on the standard output.
            */

                
                // Step - 1
                fromUser = "HELO cs3700a.msudenver.edu";
                System.out.println("Client: " + fromUser);

                long startTime1 = System.currentTimeMillis();
                socketOut.println(fromUser);
                long startTime2 = System.currentTimeMillis();


                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                
                long RTT = startTime2 - startTime1;
                System.out.println("RTT = " + RTT + " ms");


                // Step - 2 
                fromUser = "MAIL FROM: "+ senderEmail;
                System.out.println("Client: " + fromUser);

                startTime1 = System.currentTimeMillis();
                socketOut.println(fromUser);
                startTime2 = System.currentTimeMillis();


                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                
                RTT = startTime2 - startTime1;
                System.out.println("RTT = " + RTT + " ms");


                // Step - 3
                fromUser = "RCPT TO: "+ receiverEmail;
                System.out.println("Client: " + fromUser);

                startTime1 = System.currentTimeMillis();
                socketOut.println(fromUser);
                startTime2 = System.currentTimeMillis();


                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                
                RTT = startTime2 - startTime1;
                System.out.println("RTT = " + RTT + " ms");

                // Step - 4
                fromUser = "DATA";
                System.out.println("Client: " + fromUser);

                startTime1 = System.currentTimeMillis();
                socketOut.println(fromUser);
                startTime2 = System.currentTimeMillis();


                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                
                RTT = startTime2 - startTime1;
                System.out.println("RTT = " + RTT + " ms");

                // Step - 5
                fromUser = emailContents;
                System.out.println("Client: " + fromUser);

                startTime1 = System.currentTimeMillis();
                socketOut.println(fromUser);
                startTime2 = System.currentTimeMillis();


                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                
                RTT = startTime2 - startTime1;
                System.out.println("RTT = " + RTT + " ms");


                // fromUser ="SENDER: " + senderEmail + "\r\n" +
                //            "RECEIVER: " + receiverEmail + "\r\n " +
                //            "SUBJECT: " subject + "\r\n" + 
                //            "EMAIL: " + emailContents + "\r\n" + 
                //            "." ;

                // Send HTTP Request

            }
            /* Display a prompt message to ask the User whether to continue. If yes, repeat steps 3 through 5. Otherwise, send a “QUIT”
            command to the SMTP server,.*/
            System.out.println("Do you want to Continue?(Y/N) ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            //
            if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO")) {
                socketOut.println("QUIT");
                fromServer = socketIn.readLine();
                System.out.println("\nfromServer: " + fromServer);
                break;
            }

        }

        socketOut.close();
        socketIn.close();
        scan.close();
        tcpSocket.close();


    }
}