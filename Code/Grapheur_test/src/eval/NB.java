package eval;

public abstract class NB implements Noeud {
    protected Noeud fg, fd;

    public NB( Noeud fg, Noeud fd ) {
        this.fd = fd;
        this.fg = fg;
    }
}