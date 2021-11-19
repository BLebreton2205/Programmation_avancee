package eval;

public class VarX implements Noeud {

    public float eval( float x ) {
        return x;
    }

    @Override
    public String toString() {
        return ( "x" );
    }
}
