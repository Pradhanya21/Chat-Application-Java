import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static List<String> loggedInUsers = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addUser(String username) {
        loggedInUsers.add(username);
    }

    public static synchronized void removeUser(String username) {
        loggedInUsers.remove(username);
    }

    public static synchronized List<String> getLoggedInUsers() {
        return new ArrayList<>(loggedInUsers);
    }

    public static synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                // Debug: Print received message
                System.out.println("Received message: " + message);

                String[] parts = message.split(" ", 3);
                if (parts.length < 3) {
                    // Debug: Print error for invalid messages
                    System.out.println("Invalid message format: " + message);
                    continue;
                }

                String command = parts[0];
                String user = parts[1];
                String content = parts[2];

                if (command.equals("LOGIN")) {
                    username = user;
                    String password = content;
                    if (UserDatabase.checkCredentials(username, password)) {
                        Server.addUser(username);
                        out.println("LOGIN_SUCCESS");
                        System.out.println(username + " logged in.");
                    } else {
                        out.println("LOGIN_FAILURE");
                        System.out.println("Login failed for " + username);
                    }
                } else if (command.equals("SIGNUP")) {
                    username = user;
                    String password = content;
                    UserDatabase.addUser(username, password);
                    out.println("SIGNUP_SUCCESS");
                    System.out.println(username + " signed up.");
                } else if (command.equals("CHAT")) {
                    // Extract chat message correctly
                    String chatMessage = message.substring(message.indexOf(' ', message.indexOf(' ') + 1) + 1);
                    System.out.println(username + ": " + chatMessage);
                    Server.broadcast(username + ": " + chatMessage, this);
                } else {
                    // Debug: Print unrecognized command
                    System.out.println("Unrecognized command: " + command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Server.removeUser(username);
            System.out.println(username + " disconnected.");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
