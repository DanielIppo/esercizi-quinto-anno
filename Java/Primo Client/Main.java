import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        try {
            System.out.print("Inserisci l'indirizzo del server: ");
            String serverAddress = myObj.nextLine();
            System.out.print("Inserisci la porta del server: ");
            int serverPort = myObj.nextInt();
            System.out.print("Inserisci la versione HTTP: ");
            String httpVersion = myObj.next();
            System.out.print("Inserisci la risorsa da chiedere al server: ");
            String resource = myObj.next();

            Socket client = new Socket(serverAddress, serverPort);

            DataOutputStream outputServer = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
            System.out.println("Richiesta della risorsa al server... \n\n");
            String request = "GET " + resource + " HTTP/" + httpVersion + "\n" + "Host: " + serverAddress + "\n\n";
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