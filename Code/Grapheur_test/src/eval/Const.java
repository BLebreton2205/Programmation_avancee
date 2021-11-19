package eval;

public class Const implements Noeud {
    private float c;

    public Const( float c ) {
        this.c = c;
    }

    public float eval( float x ) {
        return c;
    }

    @Override
    public String toString() {
        return ( c + "" );
    }
}
