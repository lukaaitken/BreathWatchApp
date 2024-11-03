// Singleton class for DataConnection
package main;
class DataConnection {
    private static DataConnection instance;

    // Private constructor to prevent instantiation from outside the class
    private DataConnection() {
    }

    // Static method to provide access to the single instance of DataConnection
    public static DataConnection getInstance() {
        if (instance == null) {
            instance = new DataConnection();
        }
        return instance;
    }

    // Method to simulate connecting to a data source
    public void connect() {
        System.out.println("Connected to the data source.");
    }
}
