package managers;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private static final Map<String, String> userCredentials = new HashMap<>();

    public boolean registerUser(String id, String password) {
        if (id == null || id.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.out.println("Invalid ID or password.");
            return false;
        }

        if (userCredentials.containsKey(id)) {
            System.out.println("User ID already exists. Please choose a different ID.");
            return false;
        }

        userCredentials.put(id, password);
        return true;
    }

    public boolean loginUser(String id, String password) {

        if (id == null || password == null) {
            System.out.println("Invalid login credentials. ");
            return false;
        }

        String storedPassword = userCredentials.get(id.trim());
        System.out.println("Stored password for " + id + ": " + storedPassword);

        if (storedPassword != null && storedPassword.equals(password.trim())) {
            return true;
        }

        System.out.println("Invalid ID or password. ");
        return false;
    }

    public static String getUserPassword(String id) {
        return userCredentials.get(id);
    }

    public boolean changePassword(String id, String oldPassword, String newPassword) {
        if (loginUser(id, oldPassword)) {
            userCredentials.put(id, newPassword);
            return true;
        }
        return false;
    }
}