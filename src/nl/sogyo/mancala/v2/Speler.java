package nl.sogyo.mancala.v2;

/**
 * Created by jvdberg on 21/05/2014.
 */
public class Speler {
    private Speler tegenspeler;
    private boolean aanDeBeurt;

    public void setTegenspeler (Speler tegenspeler) {
        this.tegenspeler = tegenspeler;
    }

    public void beeindigBeurt() {
        aanDeBeurt = !aanDeBeurt;
        tegenspeler.krijgBeurt();
    }

    public void krijgBeurt() {
        aanDeBeurt = !aanDeBeurt;
    }

    public boolean isAanDeBeurt () {
        return aanDeBeurt;
    }
}
