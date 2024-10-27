// Singleton class for DataConnection
class DataConnection {
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
        System.out.println("Connected to the data source.");
    }
}
