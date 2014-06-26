package nl.sogyo.mancala.v2;

/**
 * Created by jvdberg on 21/05/2014.
 */
public class KleinVak extends Vak{

    public KleinVak(int stenen) {
        setStenen(stenen);
    }

    public KleinVak(int stenen, Speler eigenaar) {
        setStenen(stenen);
        this.eigenaar = eigenaar;
    }

    public void maakLeeg() {
        setStenen(0);
    }
// !!!


    @Override
    public void ontvang(int stenen) {
        if (stenen == 1) {
            if (this.stenen == 0) {
                sla();
            }
            else {
                this.stenen++;
            } // end inner if
            wisselBeurt();
        }// end outer if
        else {
            this.stenen++;
            volgendVak.ontvang(stenen -1);
        }
    }

    private void wisselBeurt() {
        eigenaar.beeindigBeurt();
    }

    public void zet() {
        if (eigenaar.isAanDeBeurt() && stenen != 0) {
            volgendVak.ontvang(this.stenen);
            maakLeeg();
        } else {
            System.out.println("De speler is niet aan de beurt");
        }
    }

    public Vak getKalaha() {
        return volgendVak.getKalaha();
    }

    public void geefAanKalaha(int stenen) {
        getKalaha().setStenen(getKalaha().getStenen() + stenen);
    }

    public Vak getOverkant() {
        return volgendVak.getOverkant().getVolgendVak();
    }

    public void sla() {
        geefAanKalaha(1 + getOverkant().getStenen());
        getOverkant().setStenen(0);
        maakLeeg();
    }

}
