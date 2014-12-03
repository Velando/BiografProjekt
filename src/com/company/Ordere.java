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
        // her kan vi direkte hente alle film navnene
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
        // de vil ligge således
        // (film navn , sal nr, tidspunkt, dag)
        for (int i = 0; i < forestilling_film_id.size(); i++) {
            String[] forestilling = new String[]
            {film_navn.get(forestilling_film_id.get(i)-1),forestilling_sal_nr.get(i),forestilling_tid.get(i),forestilling_dag.get(i)};
            forestillings_list.add(forestilling);
        }
        return forestillings_list;
    }

    public ArrayList<Integer> downloadReservationer(){
        // her kan vi direkte hente alle reservations telefonnumre.
        db.openConnection();
        ArrayList<Integer> reservationer = db.sqlCommandSelectFromGetInt("tlf_nr", "Reservation");
        db.closeConnection();
        return reservationer;
    }

    public ArrayList<Boolean> downloadSeatsForforestilling(int forestil_id){
        ArrayList<Boolean> listToBeReturned = new ArrayList<Boolean>();

        // først heter vi de lister der skal bruges
        // diss er hentet med en where condition hvor man kun henter dem der høre til en bestemt forestilling.
        db.openConnection();
        ArrayList<Integer> rowList = db.sqlCommandSelectFromGetInt("række","Billet", "forestil_id =" + forestil_id);
        ArrayList<Integer> seatList= db.sqlCommandSelectFromGetInt("sæde_nr","Billet", "forestil_id =" + forestil_id);
        db.closeConnection();

        // på en eller anden måde skal vi sortere disse så de kommer i en rækkefølge hvor row og seat altid stiger

        // !=!)")#)(!"¤Y(%

        // her opretter vi variabler der bruges til at ordne det array der skal indeholde true og false alt efter om
        // sædet er optaget.
        int row = 1;
        int seat = 1;
        int currentPointOnLists = 0;

        while(rowList.size() > currentPointOnLists){
            // først besluttes om der er flere reservationer på denne række.
            if(rowList.get(currentPointOnLists) == row){
                if(seatList.get(currentPointOnLists) == seat){
                    // true bliver tilføjet hvis sædet er reserveret
                    listToBeReturned.add(true);
                    // da dette punkt på listen over reserverede pladser er oprettet. går man videre til den næste reservation.
                    currentPointOnLists++;
                    // og husker at gå videre til næste sæde (ellers vil den lave en eksra gennemløbning af loopet unødvendigt)
                    seat++;
                }else{
                    // false bliver tilfjet hvis der stadig er flere pladser der er reserveret på rækken
                    listToBeReturned.add(false);
                    seat++;
                }
            }else{
                // null bliver tilføjet til at vise at der ikke er flere reservtioner på denne række.
                listToBeReturned.add(null);
                row++;
                // når den skifter row så går man tilbage og kigger på sæde nr 1.
                seat = 1;
            }
        }
        return listToBeReturned;
    }
}
