
import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class TCPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;

    public TCPMultiServerThread(Socket socket) {
        super("TCPMultiServerThread");
        clientTCPSocket = socket;
    }

    public void run() {

        try {

            PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
            BufferedReader cSocketIn = new BufferedReader(new InputStreamReader(clientTCPSocket.getInputStream()));

	    // get binary output stream to client (for requested data)
	    dataOut = new BufferedOutputStream(connect.getOutputStream());


            String getMethod = "GET";
            String url = "cs3700.html";

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

                //LocalDateTime myDateObj = LocalDateTime.now();
                //DateTimeFormatter myDateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            while (!cSocketIn.equals(getMethod)) {

                String toClient = ("HTTP/1.1  200 Ok".concat("\r\n")
                        + "date: ".concat(strDate).concat("\r\n")
                        + "server: ".concat("cs700a.msudenver.edu" + "\r\n"));
                cSocketOut.println(toClient);
                File file = new File("C:/Users/Desktop/msu.txt");
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {

                    cSocketOut.println(scan.nextLine());

                }

                //fromClient = cSocketIn.readLine();

                Scanner in = new Scanner(cSocketIn);
                while (in.hasNextLine()) {
                    System.out.println(in.nextLine());
                }

                clientTCPSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
