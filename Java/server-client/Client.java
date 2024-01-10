import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        try {
            System.out.print(ConsoleColors.BLUE_BOLD + "Inserisci il metodo che vuoi usare: \n"+ ConsoleColors.RED +"1)"+ ConsoleColors.BLUE +"Trasforma una stringa in UPPERCASE\n"+ ConsoleColors.RED +"2)"+ ConsoleColors.BLUE +"Exit\n"+ ConsoleColors.RESET);
            String resource = myObj.next();
            
            switch (resource) {
                case "1":
                    Socket client = new Socket("", 25565);

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
                    break;
                case "2":
                    System.out.println("Arrivederci è stato un piacere!!");
                    break;
                default:
                    break;
            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}