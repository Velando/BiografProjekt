
import com.company.Billet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BilletTest {

    Billet b1;

    @Before
    public void before(){
        b1 = new Billet(1,2,3,4,5);
    }

    @Test
    public void billetGetTests1(){
        Assert.assertEquals(1,b1.getForestil_id());
        Assert.assertEquals(2,b1.getRes_id());
        Assert.assertEquals(3,b1.getTlf_nr());
        Assert.assertEquals(4,b1.getRække());
        Assert.assertEquals(5,b1.getSæde_nr());
        //Assert.assertEquals(6,b1.getBillet_nr());
    }

    @Test
    public void billetGetTests2(){
        Assert.assertNotEquals(3, b1.getForestil_id());
        Assert.assertNotEquals(3, b1.getRes_id());
        Assert.assertNotEquals(2, b1.getTlf_nr());
        Assert.assertNotEquals(3, b1.getRække());
        Assert.assertNotEquals(3, b1.getSæde_nr());
        //Assert.assertNotEquals(3, b1.getBillet_nr());
    }
}