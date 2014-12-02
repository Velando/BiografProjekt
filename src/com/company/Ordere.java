package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Ordere {
    //fields
    // liste af alle film
    private ArrayList<String> films;
    // liste af alle forestillinger
    private ArrayList<String> forestillinger;
    // liste af tidspunkter til forestillinger som skal stemme overens med forstillingerne
    private ArrayList<Double> tidspunkterTilForestillinger;
    // sal til den enkelte forestilling (husk om vi skal hente dem eller ikke)
    private Boolean[][] seats;

    private DB db;



    //the constructor
    public Ordere(){
        db = new DB();
    }

    public ArrayList<ArrayList<Integer>> downloadBillet(){
        db.openConnection();
        ArrayList<Integer> billet_nr = db.sqlCommandSelectFromGetInt("billet_nr", "Billet");
        ArrayList<Integer> res_id = db.sqlCommandSelectFromGetInt("res_id", "Billet");
        ArrayList<Integer> forestil_id = db.sqlCommandSelectFromGetInt("forestil_id", "Billet");
        ArrayList<Integer> række = db.sqlCommandSelectFromGetInt("række", "Billet");
        ArrayList<Integer> sæde = db.sqlCommandSelectFromGetInt("sæde_nr", "Billet");



        db.closeConnection();
        ArrayList<ArrayList<Integer>> billetList = new ArrayList<ArrayList<Integer>>();

        billetList.add(billet_nr);
        billetList.add(res_id);
        billetList.add(forestil_id);
        billetList.add(række);
        billetList.add(sæde);

        return billetList;



    }

    public ArrayList<String> downloadFilms(){
        db.openConnection();
        ArrayList<String> filmName = db.sqlCommandSelectFromGetString("navn", "film");
        db.closeConnection();
        return filmName;
    }

    public ArrayList<String[]> downloadForestillinger(){
        ArrayList<String[]> forestillings_list = new ArrayList<String[]>();

        // Først henter vi alle de arrays der skal bruges.
        db.openConnection();
        ArrayList<Integer> forestilling_film_id = db.sqlCommandSelectFromGetInt("film_id","Forestilling");
        ArrayList<String> forestilling_sal_nr = db.sqlCommandSelectFromGetString("sal_nr", "Forestilling");
        ArrayList<String> forestilling_tid = db.sqlCommandSelectFromGetString("tid", "Forestilling");
        ArrayList<String> forestilling_dag =db.sqlCommandSelectFromGetString("dag", "Forestilling");

        ArrayList<String> film_navn = db.sqlCommandSelectFromGetString("navn","Film");
        db.closeConnection();

        // Så ligger vi dem sammen i en forestillings Arrayliste med lister af strings.
        for (int i = 0; i < forestilling_film_id.size(); i++) {
            String[] forestilling = new String[]
            {film_navn.get(forestilling_film_id.get(i)-1),forestilling_sal_nr.get(i),forestilling_tid.get(i),forestilling_dag.get(i)};
            forestillings_list.add(forestilling);
        }


        return forestillings_list;
    }

    public ArrayList<Integer> downloadReservationer(){
        db.openConnection();
        ArrayList<Integer> reservationer = db.sqlCommandSelectFromGetInt("tlf_nr", "Reservation");
        db.closeConnection();
        return reservationer;
    }

    // virker ikke endnu har ikke en god ide.
    private ArrayList<Boolean[]> getSæderTilForestilling(int forestil_nr){

        ArrayList<Integer> række_list = db.sqlCommandSelectFromGetInt("række", "billet", "forstil_nr = " + forestil_nr);
        ArrayList<Integer> sæde_list = db.sqlCommandSelectFromGetInt("sæde","billet","forstil_nr = " + forestil_nr);

        ArrayList<Boolean[]> x = new ArrayList<Boolean[]>();

        for (Integer i: række_list) {
            række_list.get(i);

        }

        return x;
    }
}
