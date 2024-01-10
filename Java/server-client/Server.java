import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.time.Duration;
import java.time.LocalDateTime;

public class Server {
    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Gestisci la connessione del client in un thread separato
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            System.out.println("Error stopping the server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 25565; // Porta su cui il server ascolta le connessioni
        Server server = new Server(port);
        server.start();
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        //Futuro Daniel implementare il codice del server qua
        @Override
        public void run() {
            System.out.println("New client connected");

            try {
                BufferedReader inputClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter outputClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String line = inputClient.readLine(); 
                System.out.println("Client request: " + line);
                outputClient.write("\033[0;31m" + line + "\033[0m\n");
                outputClient.flush();
            } catch (Exception e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (Exception e) {
                    System.out.println("Error closing the client socket: " + e.getMessage());
                }
            }
        }
    }
}
