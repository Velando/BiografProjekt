package com.company;

import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Controller {
    //private GUI gui = new GUI();
    // private Screen screen;
    private DB db = new DB();
    private Ordere ordere = new Ordere();
    //Objekter af typen Reservation, Film og Forestilling oprettes her via metoder i ((ordere)) klassen, der har tilgang til DB.

    private ArrayList<Billet> res = ordere.makeReservationer();
    private ArrayList<Film> film = ordere.makeFilm();
    private ArrayList<Forestilling> fore = ordere.makeForestillinger();

    public Controller(){

        // setupScreen();
        //tjekBillet("27289200");
        // tjekFilmList();
        //tjekForestillinger();
        // tjekReservationer();
        //tjekSeats(1);
        //testDownloadForestillingSpecifikdag();
        //test2();
        //tjekgetForesitillingerTilBestemtFilm();
        init();
    }

    //Hent en forestillings visningsdag og -tidspunkt ud fra filmtitel
    public ArrayList<String> getForestillingFilm(String filmNavn) {

        ArrayList<String> dagTidList = new ArrayList<String>();

        for(Forestilling f : fore) {

            if (f.getFilmNavn().equals(filmNavn)) {
                dagTidList.add(f.getDag() + " " + f.getTid());
            }
        }
        return dagTidList;
    }

    //Hent en forestillings filmtitel og visningstidspunkt ud fra en dag
    public ArrayList<String> getForestillingDag(String dag) {

        ArrayList<String> filmTidList = new ArrayList<String>();

        for(Forestilling f : fore){

            if(f.getDag().equals(dag)){
                filmTidList.add(f.getFilmNavn() + " " + f.getTid());
            }
        }
        return filmTidList;
    }

    public ArrayList<String> downloadFilms(){
        // her kan vi direkte hente alle film navnene
        db.openConnection();
        ArrayList<String> filmName = db.sqlCommandSelectFromGetString("navn", "Film");
        db.closeConnection();
        return filmName;
    }

    //returnerer et forestillings objekt udfra navn, visningsdag og visningstid
    public Forestilling getForestilling(String navn, String dag, String tid) {

        Forestilling toBeReturned = null;
        for(Forestilling f : fore) {

            if(navn.equals((f.getFilmNavn())) && dag.equals(f.getDag()) && tid.equals(f.getTid())) {
                toBeReturned = f;
                break;
            } else {
                toBeReturned = null;
            }

            if(toBeReturned != null) {
                break;
            }
        }
        return toBeReturned;
    }





    //*****NY METODE DER ORDNER KLASSE HALLØJET - INITIALISERE EN MASSE OBJEKTER OG MATCHER DEM*******



    // opretter film-, reservations- og forestillingsobjeker, samt tilknytter film og reservationer til forestilling.
    // omdøbes på senere tidspunkt.
    private void init(){


        //Film_id sammenlignes for alle forestillings- og filmobjekter, således at vi kan tilknytte et filmobjekt
        //til et forestillingsobjekt, så denne får relevante felter (relevant: navn på film)
        for(Forestilling fo: fore)
            for(Film fi: film)
                if(fi.getFilm_id() == fo.getFilm_id())
                    fo.setFilm(fi);


        //Forestillings id sammenlignes for alle reservations- og forestillingsobjekter, således at vi kan tilknytte et
        //reservationsobjekt til et forestillingsobjekt, så denne får relevante felter (relevant: række- og sædenr)
        //De "matchende" reservationsobjekter samles i en ArrayListe i forestillingsobjektet til senere brug.
        for(Forestilling fo: fore)
            for(Billet re: res)
                if(re.getForestil_id() == fo.getForstil_id())
                    fo.setReservationer(re);

        //Gennem lavReservationer() initialiseres et boolean[][] med antal rækker og sæder fra Sal klassen (vil altid
        //være 10x10 i vores tilfælde). Der itereres nu på alle reservationsobjekterne med et for each loop, der henter
        //række- og sædenr fra reservationsobjektet, og ændre boolean værdien i dobbeltarrayet til true på de
        //pågældende pladser. Altså ved vi nu, hvor der er lavet reservationer.
        for(Forestilling fo: fore) {
            fo.lavReservationer();
        }

    }

}
