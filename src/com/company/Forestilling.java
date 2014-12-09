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
    private boolean[][] reservationer123;

    //test
    private Film film;
    private ArrayList<Reservation> reservationer = new ArrayList<Reservation>();
    private Sal sal = new Sal(1,10,10);

    public Forestilling(int forstil_id, int sal_nr, int film_id, String tid, String dag){
        this.forstil_id = forstil_id;
        this.sal_nr = sal_nr;
        this.tid = tid;
        this.dag = dag;
        this.film_id = film_id;
        reservationer123 = sal.getAlleSæder();
    }

    public void test(){
        System.out.println(forstil_id);
        System.out.println(film_id);
        System.out.println(sal_nr);
        System.out.println(tid);
        System.out.println(dag);
    }



    public void setFilm(Film film){
        this.film = film;
    }

    public Film getFilm(){
        return film;
    }

    public int getFilm_id(){
        return film_id;
    }

    public int getForstil_id(){
        return forstil_id;
    }

    public void setReservationer(Reservation res){
        reservationer.add(res);
    }


    //useless?
    public ArrayList<Reservation> getReservationer(){
        return reservationer;
    }

    public void lavReservationer(){
        reservationer123 = sal.getAlleSæder();

        for(Reservation r: reservationer){
            reservationer123[r.getRække()-1][r.getSæde_nr()-1] = true;
        }
    }

    public boolean[][] getResSæder(){
        return reservationer123;
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
