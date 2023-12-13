import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        try {
            System.out.print("Inserisci il metodo che vuoi usare: \n1)Trasforma una stringa in UPPERCASE\n2)Exit");
            String resource = myObj.next();

            Socket client = new Socket("", 6255);

            DataOutputStream outputServer = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
            System.out.println("Richiesta della risorsa al server... \n\n");
            String request = "GET " + resource + " HTTP/1.1" + "\n" + "Host: " + "\n\n";
            outputServer.writeBytes(request);
            outputServer.flush();
            
            // System.out.println(request);

            String line;
            while((line = inputServer.readLine()) != null){
                System.out.println(line);
            }
            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}