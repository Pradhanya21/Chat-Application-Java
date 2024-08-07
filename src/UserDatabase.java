import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, String> users = new HashMap<>();

    public static boolean checkCredentials(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static void addUser(String username, String password) {
        users.put(username, password);
    }
}
