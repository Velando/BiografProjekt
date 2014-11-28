package com.company;


public class Billet {
    private int fore_id;
    private int res_id;
    private int række;
    private int sæde;
    private String film_navn;

    public Billet(int fore_id,String film_navn,int res_id,int række,int sæde){
        this.fore_id = fore_id;
        this.res_id = res_id;
        this.række = række;
        this.sæde = sæde;
        this.film_navn = film_navn;
    }

    public void printBillet(){
        System.out.println("fore_id = " + fore_id + ""
            + "film = " + film_navn + "" +
                "   res_id = " + res_id + "" +
            "   række = " + række + "" +
            "   sæde = " + sæde);
    }
}
