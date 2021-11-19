package eval;

public class Mult extends NB {

    public Mult( Noeud fg, Noeud fd ) {
        super( fg, fd );
    }

    public float eval( float x ) {
        return ( fg.eval( x ) ) * ( fd.eval( x ) );
    }

    @Override
    public String toString() {
        return ( "*" + "(" + fg.toString() + " , " + fd.toString() + ")" );
    }
}
