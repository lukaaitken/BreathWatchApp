public class DataConnection {
    private static DataConnection instance;

    private DataConnection() {
        // Initialize connection
    }

    public static DataConnection getInstance() {
        if (instance == null) {
            instance = new DataConnection();
        }
        return instance;
    }

    public void connect() {
        // Connection logic
        System.out.println("Connected to the data source.");
    }
}
