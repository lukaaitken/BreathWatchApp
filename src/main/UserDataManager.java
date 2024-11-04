/*package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private static final String USER_FILE = "src/data/users.txt"; // Path to user data file

    public UserDataManager() {
        // Ensure the user data file exists
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it does not exist
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    // Method to add a user to the file
    public void addUser(String username, String hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + ":" + hashedPassword);
            writer.newLine(); // Write new line after each entry
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    // Method to check if a user exists
    public boolean userExists(String username, String hashedPassword) {
        List<String> users = readUsersFromFile(); // Read existing users
        return users.contains(username + ":" + hashedPassword); // Check if the user exists
    }

    // Method to read all users from the file
    private List<String> readUsersFromFile() {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line); // Add each user to the list
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
        return users; // Return the list of users
    }
}*/