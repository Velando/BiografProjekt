package com.company;
import java.util.ArrayList;

/**
 * Created by Sebastian on 26-11-2014.
 */
public class Ordere {

    private DB db;

    //the constructor
    public Ordere(){
        db = new DB();
    }


    //Nedenstående metoder bliver brugt til at oprette klasser. download() henter, make() opretter (kaldes i controller).

    public ArrayList<ArrayList<String>> downloadForestillinger(){
        ArrayList<ArrayList<String>> forestillinger = new ArrayList<ArrayList<String>>();

        // Først henter vi alle de arrays der skal bruges.
        db.openConnection();
        ArrayList<String> forestilling_id = db.sqlCommandSelectFromGetString("forstil_id","Forestilling");
        ArrayList<String> forestilling_film_id = db.sqlCommandSelectFromGetString("film_id", "Forestilling");
        ArrayList<String> forestilling_sal_nr = db.sqlCommandSelectFromGetString("sal_nr", "Forestilling");
        ArrayList<String> forestilling_tid = db.sqlCommandSelectFromGetString("tid", "Forestilling");
        ArrayList<String> forestilling_dag = db.sqlCommandSelectFromGetString("dag", "Forestilling");




        db.closeConnection();

        for(int i = 0; i < forestilling_id.size(); i++) {
            forestillinger.add(forestilling_id);
            forestillinger.add(forestilling_sal_nr);
            forestillinger.add(forestilling_film_id);
            forestillinger.add(forestilling_tid);
            forestillinger.add(forestilling_dag);

        }

        return forestillinger;
    }

    public ArrayList<Forestilling> makeForestillinger(){
        ArrayList<Forestilling> forestillinger = new ArrayList<Forestilling>();
        ArrayList<ArrayList<String>> x = downloadForestillinger();

        for(int i = 0; i < x.get(0).size(); i++){
            forestillinger.add(new Forestilling(Integer.parseInt(x.get(0).get(i)), Integer.parseInt(x.get(1).get(i)), Integer.parseInt(x.get(2).get(i)), x.get(3).get(i), x.get(4).get(i)));
        }
        return forestillinger;
    }

    public ArrayList<ArrayList<Integer>> downloadSal(){
        ArrayList<ArrayList<Integer>> sale = new ArrayList<ArrayList<Integer>>();

        // Først henter vi alle de arrays der skal bruges.
        db.openConnection();
        ArrayList<Integer> sal_nr = db.sqlCommandSelectFromGetInt("sal_nr","Sal");
        ArrayList<Integer> rækker = db.sqlCommandSelectFromGetInt("rækker", "Sal");
        ArrayList<Integer> sæder = db.sqlCommandSelectFromGetInt("sæder", "Sal");

        db.closeConnection();

        for(int i = 0; i < sal_nr.size(); i++) {
            sale.add(sal_nr);
            sale.add(rækker);
            sale.add(sæder);
        }
        return sale;
    }

    public ArrayList<Sal> makeSale(){
        ArrayList<Sal> sale = new ArrayList<Sal>();
        ArrayList<ArrayList<Integer>> x = downloadSal();

        for(int i = 0; i < x.get(0).size(); i++){
            sale.add(new Sal(x.get(0).get(i), x.get(1).get(i), x.get(2).get(i)));
        }
        return sale;
    }

    public ArrayList<ArrayList<String>> downloadFilm(){
        ArrayList<ArrayList<String>> film = new ArrayList<ArrayList<String>>();

        // Først henter vi alle de arrays der skal bruges.
        db.openConnection();
        ArrayList<String> film_id = db.sqlCommandSelectFromGetString("film_id", "Film");
        ArrayList<String> navn = db.sqlCommandSelectFromGetString("navn", "Film");

        db.closeConnection();

        for(int i = 0; i < film_id.size(); i++) {
            film.add(film_id);
            film.add(navn);
        }
        return film;
    }

    public ArrayList<Film> makeFilm(){
        ArrayList<Film> film = new ArrayList<Film>();
        ArrayList<ArrayList<String>> x = downloadFilm();

        for(int i = 0; i < x.get(0).size(); i++){
            film.add(new Film(Integer.parseInt(x.get(0).get(i)), x.get(1).get(i)));
        }
        return film;
    }

    public ArrayList<ArrayList<Integer>> downloadBilletTest(){
        ArrayList<ArrayList<Integer>> reservationer = new ArrayList<ArrayList<Integer>>();

        // Først henter vi alle de arrays der skal bruges.
        db.openConnection();
        ArrayList<Integer> forestil_id = db.sqlCommandSelectFromGetInt("forestil_id","Billet");
        ArrayList<Integer> res_id = db.sqlCommandSelectFromGetInt("res_id","Billet");

        ArrayList<Integer> res_id2 = db.sqlCommandSelectFromGetInt("res_id", "Reservation");
        ArrayList<Integer> tlf_nr = db.sqlCommandSelectFromGetInt("tlf_nr","Reservation");

        ArrayList<Integer> række = db.sqlCommandSelectFromGetInt("række", "Billet");
        ArrayList<Integer> sæde = db.sqlCommandSelectFromGetInt("sæde_nr", "Billet");


        db.closeConnection();

        reservationer.add(forestil_id);
        reservationer.add(res_id);


        ArrayList billet_tlf_nr = new ArrayList<Integer>();
        for(int i = 0; i < res_id.size(); i++)
            for(int j = 0; j < res_id2.size(); j++)
                if(res_id.get(i) == res_id2.get(j))
                    billet_tlf_nr.add(tlf_nr.get(j));


        reservationer.add(billet_tlf_nr);
        reservationer.add(række);
        reservationer.add(sæde);

        return reservationer;
    }

    public ArrayList<Billet> makeReservationer(){
        ArrayList<Billet> billet = new ArrayList<Billet>();
        ArrayList<ArrayList<Integer>> x = downloadBilletTest();

        for(int i = 0; i < x.get(0).size(); i++){
            billet.add(new Billet(x.get(0).get(i), x.get(1).get(i), x.get(2).get(i), x.get(3).get(i), x.get(4).get(i)));
        }
        return billet;
    }
}
