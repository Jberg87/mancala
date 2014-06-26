package nl.sogyo.mancala.v2;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GrootVakTest extends TestCase {

    public void testKleinVakMaken() {
        GrootVak grootVak = new GrootVak(new Speler());
        Assert.assertEquals(0, grootVak.getStenen());
    }

    public void testOnvangEenSteen() {
        GrootVak grootVak = new GrootVak(new Speler());
        grootVak.ontvang(1);
        Assert.assertEquals(1, grootVak.getStenen());
    }

    public void testOnvangMeerStenen() {
        GrootVak grootVak = new GrootVak(new Speler());
        GrootVak grootVak2 = new GrootVak();
        grootVak.setVolgendVak(grootVak2);
        grootVak.ontvang(2);
        Assert.assertEquals(1, grootVak.getStenen());
        Assert.assertEquals(1, grootVak2.getStenen());
    }

    public void testGetKalaha() {
        KleinVak kleinVak = new KleinVak(2);
        GrootVak grootVak = new GrootVak(new Speler());
        kleinVak.setVolgendVak(grootVak);
        Assert.assertEquals(grootVak, kleinVak.getKalaha());
    }

    public void testZetOverKalaha() {
        KleinVak kleinVak = new KleinVak(2);
        GrootVak grootVak = new GrootVak(new Speler());
        KleinVak kleinVak1 = new KleinVak(4);
        kleinVak.setVolgendVak(grootVak);
        grootVak.setVolgendVak(kleinVak1);
        kleinVak.zet();
        Assert.assertEquals(0, kleinVak.getStenen());
        Assert.assertEquals(1, grootVak.getStenen());
        Assert.assertEquals(5, kleinVak1.getStenen());
    }


}