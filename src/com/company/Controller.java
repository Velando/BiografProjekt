package com.company;

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
        setupScreen();
        tjekBillet();
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

    private void tjekBillet(){
        ordere.makeBillet();
        for(int i = 0; i < ordere.billetList.size(); i++){
        ordere.billetList.get(i);
        }
    }
}
