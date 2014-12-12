/**
 * Created by Philip on 12-12-2014.
 */

import com.company.Film;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FilmTest {
    private Film f1;
    private Film f2;
    private Film f3;
    private Film f4;

    @Before
    public void beforeTests(){
        // denne køre før hver test
        f1 = new Film(1, "Film1");
        f2 = new Film(2, "Film2");
        f3 = new Film(3, "Film3");
        f4 = new Film(1, "Film1");
    }

    @Test
    public void checkGet(){
        Assert.assertEquals(1, f1.getFilm_id());
        Assert.assertEquals(3, f3.getFilm_id());

        Assert.assertEquals("Film2", f2.getFilm_navn());
        Assert.assertEquals("Film3", f3.getFilm_navn());
        Assert.assertNotEquals("Kagemanden Returns", f4.getFilm_navn());
    }

    @Test
    public void compareName(){
        Assert.assertEquals(f1.getFilm_navn(),f4.getFilm_navn());
        Assert.assertNotEquals(f2.getFilm_navn(),f3.getFilm_navn());
    }

    @Test
    public void compareFilm(){
        Assert.assertEquals(f1,f4);
        Assert.assertNotEquals(f1,f2);
    }


    @Test (expected =  IllegalArgumentException.class)
    public void argumentCheck(){
        Film f1 = new Film(5, null);
    }



}
