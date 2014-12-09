package com.company;

import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Controller {
    //private GUI gui = new GUI();
    // private Screen screen;
    private Ordere ordere;
    public Controller(){

        ordere = new Ordere();
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
    private void setupScreen(){

    }

    private void tjekForestillinger(){
        ArrayList<String[]> forestillinger = ordere.downloadForestillinger();

        for (String[] string: forestillinger) {
            System.out.print(string[0] + "   ");
            System.out.print(string[1]+ "   ");
            System.out.print(string[2]+ "   ");
            System.out.println(string[3]);
        }

    }

    private void tjekgetForesitillingerTilBestemtFilm(){
        ArrayList<String[]> forestillinger = ordere.downloadForestillingerBestemtFilm("Kagezilla");

        for (String[] string: forestillinger) {
            System.out.print(string[0] + "   ");
            System.out.println(string[1]+ "   ");

        }

    }

    private void tjekReservationer(){
        ArrayList<String> reservationer = ordere.downloadReservationer();
        System.out.print(reservationer);
    }

    // meningen med denne er at finde ud af hvilke pladser i salen er reserveret.
    private void tjekSeats(int forestil_id){
        ArrayList<Boolean> seatList = ordere.downloadSeatsForforestilling(forestil_id);


        for (int i = 0 ;i < seatList.size();i++) {
            if(seatList.get(i) == null){
                System.out.println(" null");

            }else if(seatList.get(i) == false){
                System.out.print(". ");
            }else{
                System.out.print("X ");
            }
        }
    }

    private void tjekFilmList(){
        ArrayList<String> x = ordere.downloadFilms();

        for(int i = 0; i < x.size(); i++){
           System.out.println(x.get(i));
        }
    }

    //test til billet print.
    private void tjekBillet(String tlf_nr) {
        ArrayList<String[]> billetList = ordere.downloadBillet(tlf_nr);

        for (String[] string: billetList) {
            System.out.print(string[0] + "   ");
            System.out.print(string[1]+ "   ");
            System.out.print(string[2]+ "   ");
            System.out.print(string[3] + "   ");
            System.out.print(string[4]+ "   ");
            System.out.println(string[5]);
        }
    }

    private void testDownloadForestillingSpecifikdag(){
        ArrayList<String[]> forestillinger = ordere.downloadForestillingerBestemtDag("Mandag");

        for (String[] string: forestillinger) {
            System.out.print(string[0] + "   ");
            System.out.print(string[1]+ "   ");
            System.out.println(string[2]);
        }
    }








    //*****NY METODE DER ORDNER KLASSE HALLØJET - INITIALISERE EN MASSE OBJEKTER OG MATCHER DEM*******



    // opretter film-, reservations- og forestillingsobjeker, samt tilknytter film og reservationer til forestilling.
    // omdøbes på senere tidspunkt.
    private void init(){
        //Objekter af typen Reservation, Film og Forestilling oprettes her via metoder i ((ordere)) klassen, der har tilgang til DB.
        ArrayList<Reservation> res = ordere.makeReservationer();
        ArrayList<Film> film = ordere.makeFilm();
        ArrayList<Forestilling> fore = ordere.makeForestillinger();


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
            for(Reservation re: res)
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
