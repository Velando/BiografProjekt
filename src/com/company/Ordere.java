package com.company;
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

    public ArrayList<Billet> billetList;

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

    public void makeBillet(){


        ArrayList<Integer> fore_id = db.sqlCommandSelectFrom("forstil_id", "billet");
        ArrayList<Integer> res_id = db.sqlCommandSelectFrom("res_id", "billet");
        ArrayList<Integer> række = db.sqlCommandSelectFrom("række", "billet");
        ArrayList<Integer> sæde = db.sqlCommandSelectFrom("sæde_nr", "billet");
        try {
            Thread.sleep(100);
        } catch(Exception e){
            e.printStackTrace();
        }
        billetList = new ArrayList<Billet>();
        for(int i = 0; i< 4;i++) {
            Billet x = new Billet(fore_id.get(i), res_id.get(i), række.get(i), sæde.get(i));
            billetList.add(x);
        }


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
