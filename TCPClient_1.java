
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
        String dnsName = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the DNS name or ip of your HTTP server: ");


        try {
            dnsName = new Scanner(System.in).nextLine();
            tcpSocket = new Socket("localhost", 5190);
            socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: ");
            System.exit(1);
        }


        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer, methodType, htmFile, httpVersion, userAgent;
        String fromUser;

        System.out.println("Enter your HTTP method type: ");
        methodType = sysIn.readLine();

        System.out.println("Enter the name of htm file: ");
        htmFile = sysIn.readLine();

        System.out.println("Enter the name of HTTP Version: ");
        httpVersion = sysIn.readLine();

        System.out.println("Enter the name of User-Agent: ");
        userAgent = sysIn.readLine();
        System.out.println(" ");






        while (true) {
            fromUser = methodType + " /".concat(htmFile).concat("/" + httpVersion).concat("\r\n")
                    + "Host: " + dnsName.concat("\r\n")
                    + "User-Agent: " + userAgent + "\r\n";
                        socketOut.println(fromUser);

            Scanner input = new Scanner(socketIn);



            while (input.hasNext()) {

                System.out.println(input.nextLine());
              
            }



            System.out.println("Do you want to Continue?(Y/N) ");
            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Enter your HTTP method type: ");
                methodType = sysIn.readLine();

                System.out.println("Enter the name of htm file: ");
                htmFile = sysIn.readLine();

                System.out.println("Enter the name of HTTP Version: ");
                httpVersion = sysIn.readLine();

                System.out.println("Enter the name of User-Agent: ");
                userAgent = sysIn.readLine();
                System.out.println(" ");
            } 
		else if (choice.equalsIgnoreCase("N")) {

                break;
            }

    }
        sysIn.close();
        socketOut.close();
        scan.close();
        tcpSocket.close();
     }

}





