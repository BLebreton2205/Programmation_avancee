package eval;

public class Plus extends NB {

    public Plus( Noeud fg, Noeud fd ) {
        super( fg, fd );
    }

    public float eval( float x ) {
        return ( fg.eval( x ) ) + ( fd.eval( x ) );
    }

    @Override
    public String toString() {
        return ( "+" + "(" + fg.toString() + " , " + fd.toString() + ")" );
    }
}
