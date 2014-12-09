package com.company;

/**
 * Created by Philip on 09-12-2014.
 */
    //Bruges kun til at knytte film objekt til forestilling, så den kan få film navn.
public class Film {
    private int film_id;
    private String film_navn;

    public Film(int film_id, String film_navn){
        this.film_id = film_id;
        this.film_navn = film_navn;
    }

    public String getFilm_navn(){
        return film_navn;
    }

    public int getFilm_id(){
        return film_id;
    }
}
