import com.company.Sal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SalTest {
    private Sal s1;
    private Sal s2;
    private Sal s3;
    private boolean[][] sæder;

    @Before
    public void before(){
        s1 = new Sal(1, 10, 10);
        s2 = new Sal(2, 10, 15);
        s3 = new Sal(3, 7, 9);
    }

    @Test
    public void checkGet(){
        Assert.assertEquals(1 ,s1.getSal_nr());
        Assert.assertEquals(2 ,s2.getSal_nr());

        Assert.assertEquals(10 ,s1.getRækker());
        Assert.assertEquals(7 ,s3.getRækker());

        Assert.assertEquals(15, s2.getSæder());
        Assert.assertEquals(9, s3.getSæder());

        Assert.assertNotEquals(33, s1.getSæder());
    }

    @Test
    public void checkSæder(){
        //tjekker, at der på alle rækker (10) er det korrekte (15) antal sæder.
        sæder = new boolean[10][15];
        for(int i = 0; i < sæder.length; i++){
            Assert.assertEquals(15, s2.lavSæder(sæder)[i].length);
        }

        //tjekker, at alle pladser er ledige (false)
        for(int i = 0; i < sæder.length; i++){
            for(int j = 0; j < sæder.length; j++) {
                Assert.assertEquals(false, s2.lavSæder(sæder)[i][j]);
            }
        }
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void checkGrænse1(){
        sæder = new boolean[10][10];

        //forsøger at reservere et sæde (=true) på sæde nummer 11, som ikke burde eksistere.
        s1.lavSæder(sæder)[0][11] = true;
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void checkGrænse2(){
        sæder = new boolean[10][10];

        //forsøger at reservere et sæde (=true) på række nummer 11, som ikke burde eksistere.
        s1.lavSæder(sæder)[11][00] = true;
    }



}
