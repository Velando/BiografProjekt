package com.company;

/**
 * Created by Philip on 09-12-2014.
 */

    //Bruges pt til at gemme række og sæde nr i forbindelse med Billet table i DB, som videre bliver brugt til
    //boolean[][] array i Forestilling, som skal tage vare på reservationer der er relevante for den pågældende
    //forestilling.
    //udivddelse: reservations_id og/eller telefonnummer, så vi kan tilgå reservationer via dem - bl.a. til
    //opdatering / rettelser i DB.
public class Reservation {
    private int forestil_id;
    private int række;
    private int sæde_nr;

    public Reservation(int forestil_id, int række, int sæde_nr){
        this.forestil_id = forestil_id;
        this.række = række;
        this.sæde_nr = sæde_nr;
    }

    public int getForestil_id(){
        return forestil_id;
    }

    public int getRække(){
        return række;
    }

    public int getSæde_nr(){
        return sæde_nr;
    }

}
