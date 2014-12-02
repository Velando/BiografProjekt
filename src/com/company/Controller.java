package com.company;

import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Controller {
    // private Screen screen;
    private Ordere ordere;
    public Controller(){
        super();
         //screen = new Screen();
        ordere = new Ordere();
        // setupScreen();
        // tjekBillet();
        // tjekFilmList();
        tjekForestillinger();
    }
    private void setupScreen(){
        for(int i = 0; i < ordere.getFilmsLength(); i++){
            System.out.println(ordere.getFilm(i));
        }
        int i;
        for(i= 0; i< ordere.getForestillngerLength() ; i++) {
            System.out.print(ordere.getForestiling(i) + "   " + ordere.getTidspunktTilForestilling(i) + "   ");
        }
        for(i= 0; i<10;i++){
            System.out.println();
            for(int j = 0; j<10;j++){
                if(ordere.isThisSeatTaken(i,j) != null) System.out.print("X ");
                else System.out.print(". ");
            }
        }
    }

   // private void tjekBillet(){
     //   ArrayList<Billet> x = ordere.downloadBillet();
       // for(int i = 0; i < x.size(); i++){
         //   x.get(i).printBillet();
        //}
    //}

    private void tjekForestillinger(){
        ArrayList<String[]> forestillinger = ordere.downloadForestillinger();

        for (String[] string: forestillinger) {
            System.out.print(string[0] + "   ");
            System.out.print(string[1]+ "   ");
            System.out.print(string[2]+ "   ");
            System.out.println(string[3]);
        }

    }
    // meningen med denn er at finde ud salen.
    private void tjekSeats(int forestil_id){

    }

    private void tjekFilmList(){

        ArrayList<String> x = ordere.downloadFilms();

        for(int i = 0; i < x.size(); i++){
           System.out.println(x.get(i));
        }
    }

    //test til billet print.
    private void tjekBillet() {
        ArrayList<ArrayList<Integer>> billetList = ordere.downloadBillet();
        for (int i = 0; i < billetList.size(); i++)
            for (int j = 0; j < billetList.get(i).size(); j++){
                System.out.println(billetList.get(j));
            }
    }
}
