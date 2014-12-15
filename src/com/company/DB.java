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

    //Åbner connection til DB
    private void openConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Register driver
            connection = DriverManager.getConnection(DB_URL, USER, PASS); // Open connection
            statement = connection.createStatement(); // Create statement

        } catch (Exception e) {
            e.printStackTrace(); // handle errors
        }
    }

    //Lukker connection til DB
    private void closeConnection(){
        try {
            connection.close(); // close connection

        }catch (Exception e){
            e.printStackTrace(); // handle errors
        }
    }

    //Executer en Select statement på DB - returnerer int
    private ArrayList<Integer> sqlCommandSelectFromGetInt(String select,String from) {
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

    //Executer en Select statement på DB
    private ArrayList<Integer> sqlCommandSelectFromGetInt(String select,String from,String where) {
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

    //Executer SELECT statement på DB - returnerer String
    private ArrayList<String> sqlCommandSelectFromGetString(String select,String from) {
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

    //Executer SELECT statement på DB - returnerer String
    private ArrayList<String> sqlCommandSelectFromGetString(String select,String from, String where) {
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

    //Executer INSERT INTO statement på DB - bruges til nye reservationer (Billet)
    //Returnerer et reservations id som String, som bruges til at relationer.
    public String sqlCommandInsertInto(String v1, int v2, ArrayList<ArrayList<Integer>> v3){
        String res_id = "";
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

            for(ArrayList<Integer> list : v3){
                række = list.get(0);
                sæde = list.get(1);
                String sql2 = "INSERT INTO Billet(res_id, forestil_id, række, sæde_nr) VALUES ("+ res_id + ", " + v2 +", " + række +", " + sæde +")";
                statement.executeUpdate(sql2);

                //System.out.println("Reservation set: " + v1 + " : " + v2 + " : " + række + " " + sæde);
            }

            rs.close();
            closeConnection();

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
        return res_id;
    }

    //Executer DELETE statement på DB.
    //Bruges til at slette reservationer i DB (Reservation)
    public void sqlCommandDeleteReservation(String tlf_nr){
        openConnection();
        try {
            String sql = "DELETE FROM Reservation WHERE tlf_nr =(" + tlf_nr +")";
            statement.executeUpdate(sql);
            closeConnection();

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
    }

    //Executer SELECT statement på DB.
    //Bruges til at slette reservationer (Billet).
    public void sqlCommandDeleteReservation(int fore_id, String række, String sæde){
        openConnection();
        try {
            String sql = "DELETE FROM Billet WHERE forestil_id =(" + fore_id +") AND række =(" + række + ") AND sæde_nr =(" + sæde + ")";
            statement.executeUpdate(sql);
            closeConnection();

        } catch (Exception e){
            e.printStackTrace(); // handle errors
        }
    }





    //Download metoder
    //Henter data fra DB til at oprette objekter i Controller.
    public ArrayList<ArrayList<String>> downloadForestillinger(){
        ArrayList<ArrayList<String>> forestillinger = new ArrayList<ArrayList<String>>();

        // Først henter vi alle de arrays der skal bruges.
        openConnection();
        ArrayList<String> forestilling_id = sqlCommandSelectFromGetString("forstil_id","Forestilling");
        ArrayList<String> forestilling_film_id = sqlCommandSelectFromGetString("film_id", "Forestilling");
        ArrayList<String> forestilling_sal_nr = sqlCommandSelectFromGetString("sal_nr", "Forestilling");
        ArrayList<String> forestilling_tid = sqlCommandSelectFromGetString("tid", "Forestilling");
        ArrayList<String> forestilling_dag = sqlCommandSelectFromGetString("dag", "Forestilling");


        closeConnection();


        forestillinger.add(forestilling_id);
        forestillinger.add(forestilling_sal_nr);
        forestillinger.add(forestilling_film_id);
        forestillinger.add(forestilling_tid);
        forestillinger.add(forestilling_dag);

        return forestillinger;
    }


    public ArrayList<ArrayList<Integer>> downloadSal(){
        ArrayList<ArrayList<Integer>> sale = new ArrayList<ArrayList<Integer>>();

        // Først henter vi alle de arrays der skal bruges.
        openConnection();
        ArrayList<Integer> sal_nr = sqlCommandSelectFromGetInt("sal_nr","Sal");
        ArrayList<Integer> rækker = sqlCommandSelectFromGetInt("rækker", "Sal");
        ArrayList<Integer> sæder = sqlCommandSelectFromGetInt("sæder", "Sal");

        closeConnection();

        sale.add(sal_nr);
        sale.add(rækker);
        sale.add(sæder);

        return sale;
    }


    public ArrayList<ArrayList<String>> downloadFilm(){
        ArrayList<ArrayList<String>> film = new ArrayList<ArrayList<String>>();

        // Først henter vi alle de arrays der skal bruges.
        openConnection();
        ArrayList<String> film_id = sqlCommandSelectFromGetString("film_id", "Film");
        ArrayList<String> navn = sqlCommandSelectFromGetString("navn", "Film");

        closeConnection();

        film.add(film_id);
        film.add(navn);

        return film;
    }


    public ArrayList<ArrayList<Integer>> downloadBilletTest(){
        ArrayList<ArrayList<Integer>> reservationer = new ArrayList<ArrayList<Integer>>();

        // Først henter vi alle de arrays der skal bruges.
        openConnection();
        ArrayList<Integer> forestil_id = sqlCommandSelectFromGetInt("forestil_id","Billet");
        ArrayList<Integer> res_id = sqlCommandSelectFromGetInt("res_id","Billet");

        ArrayList<Integer> res_id2 = sqlCommandSelectFromGetInt("res_id", "Reservation");
        ArrayList<Integer> tlf_nr = sqlCommandSelectFromGetInt("tlf_nr","Reservation");

        ArrayList<Integer> række = sqlCommandSelectFromGetInt("række", "Billet");
        ArrayList<Integer> sæde = sqlCommandSelectFromGetInt("sæde_nr", "Billet");


        closeConnection();

        reservationer.add(forestil_id);
        reservationer.add(res_id);


        ArrayList billet_tlf_nr = new ArrayList<Integer>();
        for(int i = 0; i < res_id.size(); i++)
            for(int j = 0; j < res_id2.size(); j++)
                if(res_id.get(i) == res_id2.get(j))
                    billet_tlf_nr.add(tlf_nr.get(j));


        reservationer.add(billet_tlf_nr);
        reservationer.add(række);
        reservationer.add(sæde);

        return reservationer;
    }

}
