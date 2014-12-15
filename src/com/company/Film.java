package com.company;

/**
 * Created by Philip on 09-12-2014.
 */
    //Bruges til at knytte film objekt til forestilling, så den kan få film navn.
public class Film {
    private int film_id;
    private String film_navn;

    public Film(int film_id, String film_navn){
        if(film_navn == null) //NOT NULL i DB, så måske overflødig?
            throw new IllegalArgumentException("Filmen skal have en titel!");
        this.film_id = film_id;
        this.film_navn = film_navn;
    }

    public String getFilm_navn(){
        return film_navn;
    }

    public int getFilm_id(){
        return film_id;
    }




    //Til test purposes
    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Film fi = (Film) o;
        if (this.film_id == fi.film_id && this.film_navn.equals(fi.film_navn)) {
            return true;
        }
        return false;
        }
    }

