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
        super();
        db = new DB();

        // her skal vi hente dataen fra databasen og ligge den ind i de fields vi har i
        // denne klasse
        makeFilms();
        makeForestillinger();
        makeTidspukterTilForestilinger();
        makeSeats();
    }

    public ArrayList<Billet> makeBillet(){
        db.openConnection();
        ArrayList<Integer> fore_id = db.sqlCommandSelectFromGetInt("forstil_id", "billet");
        ArrayList<Integer> res_id = db.sqlCommandSelectFromGetInt("res_id", "billet");
        ArrayList<Integer> række = db.sqlCommandSelectFromGetInt("række", "billet");
        ArrayList<Integer> sæde = db.sqlCommandSelectFromGetInt("sæde_nr", "billet");


        ArrayList<Integer> film_id_billet = db.sqlCommandSelectFromGetInt("film_id","forestilling");
        ArrayList<String> film = db.sqlCommandSelectFromGetString("navn","film");

        db.closeConnection();

        ArrayList<Billet> billetList = new ArrayList<Billet>();
        for(int i = 0; i< fore_id.size();i++) {
            String film_navn = film.get(film_id_billet.get(i));
            Billet x = new Billet(fore_id.get(i),film_navn, res_id.get(i), række.get(i), sæde.get(i));
            billetList.add(x);
        }
        return billetList;
    }

    public ArrayList<String> downloadFilms(){
        db.openConnection();
        ArrayList<String> filmName = db.sqlCommandSelectFromGetString("navn","film");
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

    private void makeFilms(){
        // nu er der en dummy version med fake data i Listerne
        films = new ArrayList<String>();
        films.add("Hitler og kagen");
        films.add("Kagemanden Returns");
    }

    public int getFilmsLength(){
        return films.size();
    }

    private void makeForestillinger(){
        // dummy forestillinger
        forestillinger = new ArrayList<String>();

        forestillinger.add(films.get(1));
        forestillinger.add(films.get(1));
        forestillinger.add(films.get(0));
        forestillinger.add(films.get(1));
    }

    public int getForestillngerLength(){
        return forestillinger.size();
    }

    private void makeTidspukterTilForestilinger(){
        // dummy tidspunkter
        tidspunkterTilForestillinger = new ArrayList<Double>();

        tidspunkterTilForestillinger.add(0,1122014.1600);
        tidspunkterTilForestillinger.add(1,1122014.1700);
        tidspunkterTilForestillinger.add(2,1122014.1800);
        tidspunkterTilForestillinger.add(3,1122014.1930);
    }

    private void makeSeats(){
        // dummy sal (denne sal kommer nu her til at være for alle forestillingerne)
        seats = new Boolean[10][10];

        seats[4][2] = true;
        seats[4][3] = true;
        seats[4][4] = true;


        seats[9][6] = true;
        seats[9][7] = true;
        seats[9][8] = true;
        seats[9][9] = true;
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

    // en specifik film
    public String getFilm(int nr_film){
        return films.get(nr_film);
    }

    // en specific forestilling
    public String getForestiling(int nr_forstil){
        return forestillinger.get(nr_forstil);
    }

    // tidspunktet til en specifik forestilling
    public double getTidspunktTilForestilling(int nr_forstil){
        return tidspunkterTilForestillinger.get(nr_forstil);
    }

    // salens sæder til en specifik forestilling.
    public Boolean isThisSeatTaken(int row, int seat){
        return seats[row][seat];
    }

}
