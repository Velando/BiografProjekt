package com.company;
import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Ordere {

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

    public ArrayList<ArrayList<Boolean>> downloadSeatsForforestilling(int forestil_id){
        ArrayList<ArrayList<Boolean>> listToBeReturned = new ArrayList<ArrayList<Boolean>>();
        ArrayList<Boolean> listofSeatsOnOneRow = new ArrayList<Boolean>();

        db.openConnection();
        ArrayList<Integer> rowList = db.sqlCommandSelectFromGetInt("row","billet", "forestil_id =" + forestil_id);
        ArrayList<Integer> seatList= db.sqlCommandSelectFromGetInt("seat","billet", "forestil_id =" + forestil_id);
        db.closeConnection();

        int row = 1;
        int seat = 1;
        int currentPointOnLists = 0;

        while(rowList.size() > currentPointOnLists){
            if(rowList.get(currentPointOnLists) == row){
                if(seatList.get(currentPointOnLists) == seat){
                    listofSeatsOnOneRow.add(true);
                    currentPointOnLists++;
                    seat++;
                }else{
                    listofSeatsOnOneRow.add(false);
                    seat++;
                }
            }else{
                listofSeatsOnOneRow.add(null);
                listToBeReturned.add(listofSeatsOnOneRow);
                row++;
            }
        }
        return listToBeReturned;
    }
}
