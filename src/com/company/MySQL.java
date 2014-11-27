package com.company;

import java.sql.*; // Import required packages

public class MySQL {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String database_name = "KageMandBiograf";
    static final String DB_URL = "jdbc:mysql://mysql.itu.dk/" + database_name;

    // Database credentials
    static final String USER = "KageMand";
    static final String PASS = "kage100";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Register driver
            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open connection
            statement = connection.createStatement(); // Create statement

            System.out.println("Connected");

            String sql = "SELECT * FROM billet"; // NB: implicit semi-colon!
            ResultSet rs = statement.executeQuery(sql); // *** EXECUTE QUERY! ***
            // STEP 5: Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                int email = rs.getInt("forstil_id");
                int name = rs.getInt("række");
                // int id = rs.getInt("id");
                System.out.println("navn: '" + name + "', film_id '" + email + "'"); // Display data
            }
            rs.close(); // close query
            connection.close(); // close connection
        } catch(Exception e) {
            e.printStackTrace(); // handle errors
        }
    }
}
