package com.company;

import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Controller {
    //private GUI gui = new GUI();
    // private Screen screen;
    private DB db = new DB();
    //Objekter af typen Reservation, Film og Forestilling oprettes her via metoder i ((ordere)) klassen, der har tilgang til DB.

    private ArrayList<Billet> res = makeReservationer();
    private ArrayList<Film> film = makeFilm();
    private ArrayList<Forestilling> fore = makeForestillinger();
    private ArrayList<Sal> sal = makeSale();

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

    //Hent en forestillings visningsdag ud fra et film_id
    public String getForestillingsDag(int film_id) {

        String dag = null;

        for(Forestilling f : fore){

            if(f.getForstil_id() == film_id){
                dag = f.getDag();
            }
        }
        return dag;
    }

    //Hent en forestillings visningstidspunkt ud fra et film_id
    public String getForestillingsTid(int film_id ) {

        String tid = null;

        for(Forestilling f : fore){

            if(f.getForstil_id() == film_id){
                tid = f.getTid();
            }
        }
        return tid;
    }

    //Hent en forestillings filmtitel ud fra et film_id
    public String getForestillingsNavn(int film_id ) {

        String film = null;

        for(Forestilling f : fore){

            if(f.getForstil_id() == film_id){
                film = f.getFilmNavn();
            }
        }
        return film;
    }

    public ArrayList<String> getFilms(){
        ArrayList<String> listToBeReturned = new ArrayList<String>();

        for(Film f: film){
            listToBeReturned.add(f.getFilm_navn());

        }
        return listToBeReturned;
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

    public DB getDb(){
        return db;
    }



    //*****NY METODE DER ORDNER KLASSE HALLØJET - INITIALISERE EN MASSE OBJEKTER OG MATCHER DEM*******



    // opretter film-, reservations- og forestillingsobjeker, samt tilknytter film og reservationer til forestilling.
    // omdøbes på senere tidspunkt.
    private void init() {


        //Film_id sammenlignes for alle forestillings- og filmobjekter, således at vi kan tilknytte et filmobjekt
        //til et forestillingsobjekt, så denne får relevante felter (relevant: navn på film)
        for (Forestilling fo : fore)
            for (Film fi : film)
                if (fi.getFilm_id() == fo.getFilm_id())
                    fo.setFilm(fi);


        //Forestillings id sammenlignes for alle reservations- og forestillingsobjekter, således at vi kan tilknytte et
        //reservationsobjekt til et forestillingsobjekt, så denne får relevante felter (relevant: række- og sædenr)
        //De "matchende" reservationsobjekter samles i en ArrayListe i forestillingsobjektet til senere brug.
        for (Forestilling fo : fore)
            for (Billet re : res)
                if (re.getForestil_id() == fo.getForstil_id())
                    fo.setReservationer(re);


        for (Forestilling fo : fore) {
            for (Sal s : sal) {
                if (fo.getSal_nr() == s.getSal_nr())
                    fo.setSal(s);
            }
        }

        //Gennem lavReservationer() initialiseres et boolean[][] med antal rækker og sæder fra Sal klassen (vil altid
        //være 10x10 i vores tilfælde). Der itereres nu på alle reservationsobjekterne med et for each loop, der henter
        //række- og sædenr fra reservationsobjektet, og ændre boolean værdien i dobbeltarrayet til true på de
        //pågældende pladser. Altså ved vi nu, hvor der er lavet reservationer.
        for (Forestilling fo : fore) {
            fo.lavReservationer();
        }
    }

    //til nye reservationer
    public void newReservation(int forestil_id, int res_id, int tlf_nr, ArrayList<ArrayList<Integer>> rs){
        int række;
        int sæde;
        for(ArrayList<Integer> l: rs){
            række = l.get(0);
            sæde = l.get(1);
            Billet temp = new Billet(forestil_id, res_id, tlf_nr, række, sæde);
            res.add(temp);
            fore.get(forestil_id).setReservationer(temp);
            init();
        }
    }

    public void sletReservationer(String tlfNr) {

        ArrayList<Billet> toBeRemoved = new ArrayList<Billet>();

        db.sqlCommandDeleteReservation(tlfNr);
        for(Billet billet : res) {

            if(Integer.toString(billet.getTlf_nr()).equals(tlfNr)) {

                toBeRemoved.add(billet);
            }
        }

        for(Billet b : toBeRemoved) {
            res.remove(b);
        }

        init();
    }

    public ArrayList<Billet> getReservation(String tlfNr) {

        ArrayList<Billet> toBeReturned = new ArrayList<Billet>();
        for(Billet billet : res) {

            if(Integer.toString(billet.getTlf_nr()).equals(tlfNr)) {
                toBeReturned.add(billet);
            }
        }
        return toBeReturned;
    }


    //Make metoder
    public ArrayList<Forestilling> makeForestillinger(){
        ArrayList<Forestilling> forestillinger = new ArrayList<Forestilling>();
        ArrayList<ArrayList<String>> x = db.downloadForestillinger();

        for(int i = 0; i < x.get(0).size(); i++){
            forestillinger.add(new Forestilling(Integer.parseInt(x.get(0).get(i)), Integer.parseInt(x.get(1).get(i)), Integer.parseInt(x.get(2).get(i)), x.get(3).get(i), x.get(4).get(i)));
        }
        return forestillinger;
    }

    public ArrayList<Sal> makeSale(){
        ArrayList<Sal> sale = new ArrayList<Sal>();
        ArrayList<ArrayList<Integer>> x = db.downloadSal();

        for(int i = 0; i < x.get(0).size(); i++){
            sale.add(new Sal(x.get(0).get(i), x.get(1).get(i), x.get(2).get(i)));
        }
        return sale;
    }



    public ArrayList<Film> makeFilm(){
        ArrayList<Film> film = new ArrayList<Film>();
        ArrayList<ArrayList<String>> x = db.downloadFilm();

        for(int i = 0; i < x.get(0).size(); i++){
            film.add(new Film(Integer.parseInt(x.get(0).get(i)), x.get(1).get(i)));
        }
        return film;
    }



    public ArrayList<Billet> makeReservationer(){
        ArrayList<Billet> billet = new ArrayList<Billet>();
        ArrayList<ArrayList<Integer>> x = db.downloadBilletTest();

        for(int i = 0; i < x.get(0).size(); i++){
            billet.add(new Billet(x.get(0).get(i), x.get(1).get(i), x.get(2).get(i), x.get(3).get(i), x.get(4).get(i)));
        }
        return billet;
    }
}
