package nl.sogyo.mancala.v2;

/**
 * Created by jvdberg on 23/05/2014.
 */
public class Main {
    public static void main(String[] args) {
        Spel spel = new Spel();
        spel.zetSpelersOp();
        spel.maakSpeelBord();
        spel.makeActionListeners();
    }
}
