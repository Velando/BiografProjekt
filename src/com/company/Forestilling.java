package com.company;

import java.util.ArrayList;

/**
 * Created by Philip on 09-12-2014.
 */

    //Forestilling klassen har den alt vi skal bruge omkring forestillingerne til at
    //kunne få et GUI op at køre.
public class Forestilling {

    private int forstil_id;
    private int sal_nr;
    private int film_id;
    private String tid;
    private String dag;
    private boolean[][] salSize; //Repræsenterer salen. False = ledigt sæde, true = reserveret.

    private Film film;
    private ArrayList<Billet> reservationer = new ArrayList<Billet>();
    private Sal sal;

    public Forestilling(int forstil_id, int sal_nr, int film_id, String tid, String dag){
        this.forstil_id = forstil_id;
        this.sal_nr = sal_nr;
        this.tid = tid;
        this.dag = dag;
        this.film_id = film_id;
    }


    //Når der er tilføjet eller slettet reservationer, skal Billet arraylisten på hver forestilling opdateres.
    public void updateBilletter(int række, int sæde) {

        ArrayList<Billet> toBeRemoved = new ArrayList<Billet>();

        for(Billet billet : reservationer) {

            if (billet.getRække() == række && billet.getSæde_nr() == sæde){
                toBeRemoved.add(billet);
            }
        }

        for(Billet b: toBeRemoved)
            reservationer.remove(b);
        lavReservationer();
    }


    //Henter størrelse på salen, og sætter true for alle reserverede sæder i "reservationer" listen.
    public void lavReservationer(){
        salSize = sal.lavSæder(salSize);

        for(Billet r: reservationer){
            salSize[r.getRække()-1][r.getSæde_nr()-1] = true;
        }
    }



    //Forestilling
    public String getDag(){
        return dag;
    }

    public String getTid(){return tid;}

    public boolean[][] getResSæder(){
        return salSize;
    }

    public int getForstil_id(){
        return forstil_id;
    }

    public ArrayList<Billet> getReservationer(){
        return reservationer;
    }

    //Tilføjer Billet objekter til reservationslisten.
    public void setReservationer(Billet res){
        reservationer.add(res);
    }


    //Film
    public void setFilm(Film film){
        this.film = film;
    }

    public Film getFilm(){
        return film;
    }

    public String getFilmNavn() {
        return film.getFilm_navn();
    }

    public int getFilm_id(){
        return film_id;
    }

    //Sal
    public int getSalRækker(){
        return sal.getRækker();
    }

    public int getSalSæder() {
        return sal.getSæder();
    }

    public int getSal_nr(){
        return sal_nr;
    }

    public void setSal(Sal sal){
        this.sal = sal;
    }

}
