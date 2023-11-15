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
            System.out.print("Inserisci il messaggio da inviare al server: ");
            String message = myObj.nextLine();

            Socket client = new Socket(serverAddress, serverPort);

            DataOutputStream outputServer = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            while((message = inputServer.readLine()) != null){
                System.out.println(message);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}