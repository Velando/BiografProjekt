package com.company;

/**
 * Created by Philip on 09-12-2014.
 */

    //Bruges til at gemme række og sæde nr i forbindelse med Billet table i DB (og tlf nr fra Reservation),
    //som videre bliver brugt til boolean[][] array i Forestilling, som skal tage vare på reservationer
    //der er relevante for den pågældende forestilling.

public class Billet {
    private int forestil_id;
    private int res_id;
    private int tlf_nr;
    private int række;
    private int sæde_nr;

    public Billet(int forestil_id, int res_id, int tlf_nr, int række, int sæde_nr){
        this.forestil_id = forestil_id;
        this.res_id = res_id;
        this.tlf_nr = tlf_nr;
        this.række = række;
        this.sæde_nr = sæde_nr;
    }

    public int getForestil_id(){return forestil_id;}

    public int getRes_id(){return res_id;}

    public int getTlf_nr(){return tlf_nr;}

    public int getRække(){return række;}

    public int getSæde_nr(){return sæde_nr;}

}
