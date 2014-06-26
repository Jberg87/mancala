package nl.sogyo.mancala.v2;

/**
 * Created by jvdberg on 21/05/2014.
 */
public class GrootVak extends Vak {

    public GrootVak() {
        this.setStenen(0);
    }

    public GrootVak(Speler eigenaar) {
        this.setStenen(0);
        this.eigenaar = eigenaar;
    }

    @Override
    public void ontvang(int stenen) {
        if (this.eigenaar.isAanDeBeurt()){
            if (stenen == 1) {
                this.stenen++;
            }
            else {
                this.stenen++;
                volgendVak.ontvang(stenen -1);
            }
        } else {
            volgendVak.ontvang(stenen);
        }
    }

    public Vak getKalaha() {
        return this;
    }

    public Vak getOverkant() {
        return this;
    }
}
