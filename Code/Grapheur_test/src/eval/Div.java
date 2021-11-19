package eval;

public class Div extends NB {

    public Div( Noeud fg, Noeud fd ) {
        super( fg, fd );
    }

    public float eval( float x ) {
        return ( this.fg.eval( x ) ) / ( fd.eval( x ) );
    }

    @Override
    public String toString() {
        return ( "/" + "(" + fg.toString() + " , " + fd.toString() + ")" );
    }
}
