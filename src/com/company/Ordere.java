package com.company;
import java.util.ArrayList;

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
    private ArrayList<Integer> tidspunkterTilForestillinger;
    // sal til den enkelte forestilling (husk om vi skal hente dem eller ikke)
    private Boolean[][] seats;

    //the constructor
    public Ordere(){
        super();
        // her skal vi hente dataen fra databasen og ligge den ind i de fields vi har i
        // denne klasse
        makeFilms();
        makeForestillinger();
        makeTidspukterTilForestilinger();
        makeSeats();
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
        tidspunkterTilForestillinger = new ArrayList<Integer>();

        tidspunkterTilForestillinger.add(0,1600);
        tidspunkterTilForestillinger.add(1,1700);
        tidspunkterTilForestillinger.add(2,1800);
        tidspunkterTilForestillinger.add(3,1930);
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
    public int getTidspunktTilForestilling(int nr_forstil){
        return tidspunkterTilForestillinger.get(nr_forstil);
    }

    // salens sæder til en specifik forestilling.
    public Boolean isThisSeatTaken(int row, int seat){
        return seats[row][seat];
    }
}
