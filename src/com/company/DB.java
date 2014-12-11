package com.company;

import java.sql.*; // Import required packages
import java.util.ArrayList;
import java.util.List;

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
    private Connection connection;
    private Statement statement;

    public DB() {
    }

    public void openConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Register driver
            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open connection
            statement = connection.createStatement(); // Create statement

            // System.out.println("Connected");

        } catch (Exception e) {
            e.printStackTrace(); // handle errors
        }
    }

    public void closeConnection(){
        try {
            connection.close(); // close connection

            //System.out.println("Connection closed");

        }catch (Exception e){
            e.printStackTrace(); // handle errors
        }

    }

    public ArrayList<Integer> sqlCommandSelectFromGetInt(String select,String from) {
        ArrayList<Integer> listToBeReturned = new ArrayList<Integer>();
        try {
            String sql = "SELECT * FROM " + from;

            ResultSet rs = statement.executeQuery(sql); // this line executes the query

            // Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                listToBeReturned.add(rs.getInt(select));
            }
            rs.close();// close query

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
        return listToBeReturned;
    }

    public ArrayList<Integer> sqlCommandSelectFromGetInt(String select,String from,String where) {
        ArrayList<Integer> listToBeReturned = new ArrayList<Integer>();
        try {
            String sql = "SELECT * FROM " + from + " WHERE (" + where + ")";

            ResultSet rs = statement.executeQuery(sql); // this line executes the query

            // Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                listToBeReturned.add(rs.getInt(select));
            }
            rs.close();// close query

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
        return listToBeReturned;
    }

    public ArrayList<String> sqlCommandSelectFromGetString(String select,String from) {
        ArrayList<String> listToBeReturned = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM " + from;

            ResultSet rs = statement.executeQuery(sql); // this line executes the query

            // Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                listToBeReturned.add(rs.getString(select));
            }
            rs.close();// close query

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
        return listToBeReturned;
    }

    public ArrayList<String> sqlCommandSelectFromGetString(String select,String from, String where) {
        ArrayList<String> listToBeReturned = new ArrayList<String>();
        try {
            String sql = "SELECT " +select +" FROM " + from + " WHERE (" + where + ")";

            ResultSet rs = statement.executeQuery(sql); // this line executes the query

            // Extract data from result set
            while (rs.next()) { // Retrieve data by column name
                listToBeReturned.add(rs.getString(select));
            }
            rs.close();// close query

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
        return listToBeReturned;
    }

    public void sqlCommandInsertInto(String v1, int v2, ArrayList<ArrayList<Integer>> v3){
        String res_id;
        openConnection();
        try {
            String sql = "INSERT INTO Reservation(tlf_nr) VALUES(" + v1 +")";
            statement.executeUpdate(sql);

            String sql1 = "SELECT res_id FROM Reservation WHERE tlf_nr = " + v1;
            ResultSet rs = statement.executeQuery(sql1);
            rs.next();
            res_id = rs.getString("res_id");


            int sæde;
            int række;

            for(int j = 0; j < v3.get(0).size(); j++){
                sæde = v3.get(0).get(j);
                række = v3.get(1).get(j);
                String sql2 = "INSERT INTO Billet(res_id, forestil_id, række, sæde_nr) VALUES ("+ res_id + ", " + v2 +", " + sæde +", " + række +")";
                statement.executeUpdate(sql2);

                rs.close();
            }

            closeConnection();

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
    }

}

