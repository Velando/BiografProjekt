package com.company;

/**
 * Created by Philip on 09-12-2014.
 */

    //Klassen bruges lige nu kun i minimalt. Bruges til at gemme sal nr (som ikke bruges pt), samt antal sæder og
    // rækker der bruges i forbindelse med boolean[][] array i Forestilling, der har relevans for reservationer.
    //
public class Sal {
    private int sal_nr;
    private int rækker;
    private int sæder;
    private boolean[][] alleSæder; // arraylists i stedet?

    public Sal(int sal_nr, int rækker, int sæder){
        this.sal_nr = sal_nr;
        this.rækker = rækker;
        this.sæder = sæder;
        lavSæder();
    }

    private void lavSæder(){ //skal udvides med reservationer
        alleSæder = new boolean[rækker][sæder];

        for(boolean[] ba: alleSæder){
            for(boolean b: ba){
                b = false;
            }
        }
    }

    public boolean[][] getAlleSæder(){
        return alleSæder;
    }

    public int getSal_nr(){
        return sal_nr;
    }

    public int getRækker(){
        return rækker;
    }
}