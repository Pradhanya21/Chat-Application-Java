import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;
    private String username;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String message;
            while ((message = in.readLine()) != null) {
                String[] parts = message.split(" ");
                String command = parts[0];
                username = parts[1];
                if (command.equals("LOGIN")) {
                    String password = parts[2];
                    if (UserDatabase.checkCredentials(username, password)) {
                        Server.addUser(username);
                        out.println("LOGIN_SUCCESS");
                        System.out.println(username + " logged in.");
                    } else {
                        out.println("LOGIN_FAILURE");
                        System.out.println("Login failed for " + username);
                    }
                } else if (command.equals("SIGNUP")) {
                    String password = parts[2];
                    UserDatabase.addUser(username, password);
                    out.println("SIGNUP_SUCCESS");
                    System.out.println(username + " signed up.");
                } else if (command.equals("CHAT")) {
                    String chatMessage = message.substring(message.indexOf(' ') + 1);
                    System.out.println(username + ": " + chatMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Server.removeUser(username);
            System.out.println(username + " disconnected.");
        }
    }
}
