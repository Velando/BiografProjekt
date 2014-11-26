package com.company;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Ordere {
    //fields
    // liste af alle film
    private String[] films;
    // liste af alle forestillinger
    private String[] forestillinger;
    // liste af tidspunkter til forestillinger som skal stemme overens med forstillingerne
    private int[] tidspunkterTilForestillinger;
    // sal til den enkelte forestilling (husk om vi skal hente dem eller ikke)
    private Boolean[][] seats;

    //the constructor
    public Ordere(){
        super();
        // her skal vi hente dataen fra databasen og ligge den ind i de fields vi har i
        // denne klasse
    }

    private void makeFilms(){

    }

    private void makeForestillinger(){

    }

    private void makeTidspukterTilForestilinger(){

    }

    private void makeSeats(){

    }
    // en specifik film
    public String getFilm(int nr_film){

    }
    // en specifik forestilling
    public String getForestiling(int nr_forstil){

    }
    // tidspunktet til en specifik forestilling
    public int getTidspunktTilForestilling(int nr_forstil){

    }
    // salens sæder til en specifik forestilling.
    public Boolean[][] getSeats(){

    }

}
