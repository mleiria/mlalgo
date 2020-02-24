package pt.mleiria.ai;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VacuumCleanerTest extends TestCase {

    public void testSize(){
        final Universe u = new VacuumCleaner(5);
        Assert.assertEquals(5, u.getSize());
    }

    public void testIsFrontier(){
        final Universe u = new VacuumCleaner(4);
        Assert.assertEquals(true, u.isFrontier(0,0));
        Assert.assertEquals(false, u.isFrontier(1,1));
        Assert.assertEquals(false, u.isFrontier(2,1));
        Assert.assertEquals(true, u.isFrontier(3,3));
        Assert.assertEquals(true, u.isFrontier(3,1));
    }

    public void testCurrentPosition(){
        final Universe u = new VacuumCleaner(4);
        u.setPosition(2, 2);
        Assert.assertEquals(u.getPosition().getX() == 2, u.getPosition().getY() == 2);
    }
}
