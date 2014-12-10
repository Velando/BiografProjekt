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
     // arraylists i stedet?

    public Sal(int sal_nr, int rækker, int sæder){
        this.sal_nr = sal_nr;
        this.rækker = rækker;
        this.sæder = sæder;
    }

    public boolean[][] lavSæder(boolean[][] sal){ //skal udvides med reservationer
        sal = new boolean[rækker][sæder];

        for(boolean[] ba: sal){
            for(boolean b: ba){
                b = false;
            }
        }
        return sal;
    }



    public int getSal_nr(){
        return sal_nr;
    }

    public int getRækker(){
        return rækker;
    }

    public int getSæder() {return sæder;}
}
