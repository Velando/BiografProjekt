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
        //tjekBillet();
        // tjekFilmList();
        //tjekForestillinger();
        //tjekReservationer();
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
    // meningen med denn er at finde ud salen.
    private void tjekSeats(int forestil_id){
        ArrayList<Boolean> list1 = ordere.downloadSeatsForforestilling(forestil_id);

        System.out.println(list1.size());


        for (int i = 0 ;i < list1.size();i++) {
            if(list1.get(i) == null){
                System.out.println(" null");

            }else if(list1.get(i) == false){
                System.out.print(". ");
            }else{
                System.out.print("X ");
            }
        }


        System.out.println("this is to tjek where we are");

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
