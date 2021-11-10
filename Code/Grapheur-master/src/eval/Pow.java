package eval;

public class Pow extends NB {

    public Pow( Noeud fg, Noeud fd ) {
        super( fg, fd );
    }

    public float eval( float x ) {
        return (float) Math.pow( fg.eval( x ), fd.eval( x ) );
    }

    @Override
    public String toString() {
        return ( "^" + "(" + fg.toString() + " , " + fd.toString() + ")" );
    }
}
