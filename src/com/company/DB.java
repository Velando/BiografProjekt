package com.company;

import java.sql.*; // Import required packages

/**
 * Created by Sebastian on 26-11-2014.
 */
public class DB {
    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String database_name = "KageMandBiograf";
    private final String DB_URL = "jdbc:mysql://mysql.itu.dk/" + database_name;

    // Database credentials
    private final String USER = "KageMand";
    private final String PASS = "kage100";

    // The connection situation and the statement to send.
    private Connection connection = null;
    private Statement statement = null;

    public DB() {

    }

    private void openConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Register driver
            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open connection
            statement = connection.createStatement(); // Create statement

            System.out.println("Connected");

        } catch (Exception e) {
            e.printStackTrace(); // handle errors
        }
    }

    private void closeConnection(){
        try {
            connection.close(); // close connection

            System.out.println("Connection closed");

        }catch (Exception e){
            e.printStackTrace(); // handle errors
        }

    }

    private void sqlCommand(String sqlCommand) {
        try {
            String sql = "SELECT * FROM film"; // NB: implicit semi-colon!
            ResultSet rs = statement.executeQuery(sql); // *** EXECUTE QUERY! ***
            // STEP 5: Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                String email = rs.getString("film_id");
                String name = rs.getString("navn");
                // int id = rs.getInt("id");
                System.out.println("navn: '" + name + "', film_id '" + email + "'"); // Display data
            }
            rs.close();// close query
        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
    }
    public static String[] getReservations(){

    }

    public static String[] getFilms(){
        String[] x = {"fish", "hi"};
        return x;
    }

    public void addReservations(String reservation){

    }

    public void removeReservation(int res_id){

    }

    public Boolean[][] generateSal(int forestilling){
        Boolean[][] y = {{true}};
        return y;
    }
}

