package nl.sogyo.mancala.v2;

import junit.framework.Assert;
import junit.framework.TestCase;

public class KleinVakTest extends TestCase {

    public void testKleinVakMaken() {
        KleinVak kv = new KleinVak(4);
        Assert.assertEquals(4, kv.getStenen());
    }

    public void testSetStenen() {
        KleinVak kv = new KleinVak(4);
        kv.setStenen(1);
        Assert.assertEquals(1, kv.getStenen());
    }

    public void testMaakLeeg() {
        KleinVak kv = new KleinVak(4);
        kv.maakLeeg();
        Assert.assertEquals(0, kv.getStenen());
    }

    public void testOntvangEenSteen() {
        KleinVak kv = new KleinVak(4);
        kv.ontvang(1);
        Assert.assertEquals(5, kv.getStenen());
    }

    public void testSetEnGetVolgendVak() {
        KleinVak kv = new KleinVak(1);
        KleinVak kv2 = new KleinVak(4);
        kv.setVolgendVak(kv2);
        Assert.assertEquals(kv2, kv.getVolgendVak());
    }

    public void testZetVakMetEenSteen() {
        KleinVak kv = new KleinVak(1);
        KleinVak kv2 = new KleinVak(4);
        kv.setVolgendVak(kv2);
        kv.zet();
        Assert.assertEquals(0, kv.getStenen());
        Assert.assertEquals(5, kv2.getStenen());
    }

    public void testZetVakMetMeerStenen() {
        KleinVak kv = new KleinVak(2);
        KleinVak kv2 = new KleinVak(4);
        KleinVak kv3 = new KleinVak(4);
        kv.setVolgendVak(kv2);
        kv2.setVolgendVak(kv3);
        kv.zet();
        Assert.assertEquals(0, kv.getStenen());
        Assert.assertEquals(5, kv2.getStenen());
        Assert.assertEquals(5, kv3.getStenen());
    }

    public void testVindOverkant() {
        KleinVak kleinVak = new KleinVak(4);
        GrootVak grootVak = new GrootVak();
        KleinVak kleinVak1 = new KleinVak(4);
        kleinVak.setVolgendVak(grootVak);
        grootVak.setVolgendVak(kleinVak1);
        Assert.assertEquals(kleinVak1, kleinVak.getOverkant());
    }

    public void testVindOverkantVerder() {
        KleinVak kleinVak = new KleinVak(4);
        KleinVak kleinVak1 = new KleinVak(4);
        KleinVak kleinVak2 = new KleinVak(4);
        KleinVak kleinVak3 = new KleinVak(4);
        GrootVak grootVak = new GrootVak();
        kleinVak.setVolgendVak(kleinVak1);
        kleinVak1.setVolgendVak(grootVak);
        grootVak.setVolgendVak(kleinVak2);
        kleinVak2.setVolgendVak(kleinVak3);
        Assert.assertEquals(kleinVak3, kleinVak.getOverkant());
    }

    public void testGeefAanKalahaStandAlone(){
        KleinVak kleinVak = new KleinVak(5);
        GrootVak grootVak = new GrootVak();
        kleinVak.setVolgendVak(grootVak);
        kleinVak.geefAanKalaha(kleinVak.getStenen());
        Assert.assertEquals(5, kleinVak.getStenen());
        Assert.assertEquals(5, grootVak.getStenen());
    }

    public void testSlaOverkant() {
        KleinVak kleinVak = new KleinVak(0);
        GrootVak grootVak = new GrootVak();
        KleinVak kleinVak1 = new KleinVak(4);
        kleinVak.setVolgendVak(grootVak);
        grootVak.setVolgendVak(kleinVak1);
        kleinVak.ontvang(1);
        Assert.assertEquals(0, kleinVak.getStenen());
        Assert.assertEquals(5, grootVak.getStenen());
        Assert.assertEquals(0, kleinVak.getStenen());

    }

}