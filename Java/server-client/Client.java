import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        try {
            Socket client = new Socket("0.0.0.0", 25565);
            while (true) {
            // System.out.print(ConsoleColors.BLUE_BOLD + "Inserisci il metodo che vuoi usare: \n" + ConsoleColors.RED +"1)" + ConsoleColors.BLUE + "Trasforma una stringa in UPPERCASE\n" + ConsoleColors.RED + "2)" + ConsoleColors.BLUE + "Exit\n" + ConsoleColors.RESET);
                System.out.print("Inserisci il metodo che vuoi usare: \n1)Colora una parola in rosso\n2)Exit\n");
                String resource = myObj.next();
                
                switch (resource) {
                    case "1":

                        System.out.print("Inserisci la parola che vuoi colorare: ");
                        String frase = myObj.next();

                        BufferedWriter outputServer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String request = frase + "\n\n";
                        outputServer.write(request);
                        outputServer.flush();
            
                        String line = inputServer.readLine();
                        
                        System.out.println("Risposta: " + line + "\n\n");

                        
                        break;
                    case "2":
                        client.close();
                        System.out.println("Arrivederci è stato un piacere!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Inserisci un numero valido!!");
                        break;
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}