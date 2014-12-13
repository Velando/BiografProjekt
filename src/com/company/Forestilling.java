package com.company;

import java.util.ArrayList;

/**
 * Created by Philip on 09-12-2014.
 */

    //the almighty forestillingsklasse, som måske laver lidt for meget? Lige nu har den alt vi skal bruge omkring
    //forestillingerne til at kunne få et GUI op at køre, med undtagelse af rettelser i reservationer.
public class Forestilling {

    private int forstil_id;
    private int sal_nr;
    private int film_id;
    private String tid;
    private String dag;
    private boolean[][] salSize;

    //test
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

    public void updateBilletter(int række, int sæde) {

        ArrayList<Billet> toBeRemoved = new ArrayList<Billet>();

        for(Billet billet : reservationer) {

            if (billet.getRække() == række && billet.getSæde_nr() == sæde){
                toBeRemoved.add(billet);
            }
        }

        for(Billet b: toBeRemoved)
            reservationer.remove(toBeRemoved);
        lavReservationer();
    }

    public void setFilm(Film film){
        this.film = film;
    }

    public Film getFilm(){
        return film;
    }

    public String getFilmNavn() {
        return film.getFilm_navn();
    }

    public String getDag(){
        return dag;
    }

    public String getTid(){return tid;}

    public int getFilm_id(){
        return film_id;
    }

    public int getForstil_id(){
        return forstil_id;
    }

    public void setReservationer(Billet res){
        reservationer.add(res);
    }

    public ArrayList<Billet> getReservationer(){
        return reservationer;
    }

    public int getSalRækker(){
        return sal.getRækker();
    }

    public int getSalSæder() {
        return sal.getSæder();
    }


    public void lavReservationer(){
        salSize = sal.lavSæder(salSize);

        for(Billet r: reservationer){
            salSize[r.getRække()-1][r.getSæde_nr()-1] = true;
        }
    }

    public boolean[][] getResSæder(){
        return salSize;
    }

    







    //midlertidigt ude af brug

    public int getSal_nr(){
        return sal_nr;
    }
    public void setSal(Sal sal){
        this.sal = sal;
    }

    public Sal getSal(){
        return sal;
    }

}
