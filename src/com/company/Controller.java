package com.company;

import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Controller {
    // private Screen screen;
    private Ordere ordere;
    public Controller(){
        ordere = new Ordere();
        // setupScreen();
        // tjekBillet();
        // tjekFilmList();
        // tjekForestillinger();
        // tjekReservationer();
        tjekSeats(1);
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

    private void tjekReservationer(){
        ArrayList<Integer> reservationer = ordere.downloadReservationer();
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
    private void tjekBillet() {
        ArrayList<ArrayList<Integer>> billetList = ordere.downloadBillet();
        for (int i = 0; i < billetList.size(); i++) {
            System.out.println();
            for (int j = 0; j < billetList.get(i).size(); j++) {
                System.out.print(billetList.get(j).get(i) + " ");
            }
        }
    }
}
