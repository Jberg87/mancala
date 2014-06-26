package nl.sogyo.mancala.v2;

/**
 * Created by jvdberg on 21/05/2014.
 */
public abstract class Vak {

    protected int stenen;
    protected Vak volgendVak;
    protected Speler eigenaar;

    public int getStenen() {
        return stenen;
    }

    public void setStenen(int stenen) {
        this.stenen = stenen;
    }

    public abstract void ontvang(int stenen);

    public void setVolgendVak(Vak volgendVak) {
        this.volgendVak = volgendVak;
    }

    public Vak getVolgendVak() {
        return volgendVak;
    }

    public abstract Vak getKalaha();

    public abstract Vak getOverkant();

    public void sla() {}

    public void zet() {

    }

}
