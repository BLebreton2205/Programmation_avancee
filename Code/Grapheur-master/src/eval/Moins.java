package eval;

public class Moins extends NB {

    public Moins( Noeud fg, Noeud fd ) {
        super( fg, fd );
    }

    public float eval( float x ) {
        return ( fg.eval( x ) ) - ( fd.eval( x ) );
    }

    @Override
    public String toString() {
        return ( "-" + "(" + fg.toString() + " , " + fd.toString() + ")" );
    }
}
