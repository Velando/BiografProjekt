package com.company.tests;


import com.company.Forestilling;
import com.company.Film;
import com.company.Billet;
import com.company.Sal;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForestillingTest {
    private Forestilling f1;
    private Forestilling f2;
    private Forestilling f3;

    @Before
    public void beforeTests(){
        f1 = new Forestilling(1,2,3,"1000","Mandag");
        f2 = new Forestilling(2,2,3,"1000","Mandag");
        f3 = new Forestilling(3,2,2,"1200","Mandag");
    }

    @Test
    public void forestillingGetTests(){
        Assert.assertEquals(1,f1.getForstil_id());
        Assert.assertEquals(2,f1.getSal_nr());
        Assert.assertEquals(3,f1.getFilm_id());
        Assert.assertEquals("1000",f1.getTid());
        Assert.assertEquals("Mandag",f1.getDag());
    }

    @Test
    public void forestillingCompareElementsTest(){
        Assert.assertEquals(f1.getFilm_id(),f2.getFilm_id());
        Assert.assertNotEquals(f1.getFilm_id(),f3.getFilm_id());
    }

    @Test
    public void forestillingFilmInput(){

        //her kan der bruges mocking objecter
        Film film = new Film(2,"Kagemand");

        f1.setFilm(film);
        Assert.assertEquals("Kagemand",f1.getFilmNavn());
    }

    @Test
    public void forestillingBilletInput(){

        //her kan der buges mocking objecter
        Billet billet = new Billet(1,1,10000000,1,1);
        Sal sal = new Sal(1,10,10);


        f1.setSal(sal);
        f1.setReservationer(billet);
        f1.lavReservationer();

        Assert.assertEquals(true,f1.getResSæder()[0][0]);
        Assert.assertNotEquals(true,f1.getResSæder()[0][1]);
        Assert.assertNotEquals(true,f1.getResSæder()[1][0]);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void forestillingSal(){
        Sal sal = new Sal(1,1,1);
        f1.setSal(sal);
        f1.lavReservationer();

        Assert.assertEquals(false,f1.getResSæder()[0][0]);

        // this one should throws an ArrayIndexOutOfBoundsExcetion.
        Assert.assertEquals(false,f1.getResSæder()[1][0]);
    }

}
