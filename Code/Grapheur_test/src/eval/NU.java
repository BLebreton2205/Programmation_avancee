package eval;

public class NU implements Noeud {
    private Noeud  f;
    private String op;

    public NU( Noeud f, String op ) {
        this.f = f;
        this.op = op;
    }

    @Override
    public float eval( float x ) {
        switch ( op ) {
        case "ln":
            return (float) Math.log( f.eval( x ) );
        case "log":
            return (float) Math.log10( f.eval( x ) );
        case "exp":
            return (float) Math.exp( f.eval( x ) );
        case "abs":
            return (float) Math.abs( f.eval( x ) );
        case "cos":
            return (float) Math.cos( f.eval( x ) );
        case "sin":
            return (float) Math.sin( f.eval( x ) );
        case "tan":
            return (float) Math.tan( f.eval( x ) );
        case "sqrt":
            return (float) Math.sqrt( f.eval( x ) );
        case "-":
            return -1 * f.eval( x );
        case "²":
            return (float) Math.pow( f.eval( x ), 2 );
        default:
            return 0;
        }
    }

    @Override
    public String toString() {
        return ( op + "(" + f.toString() + ")" );
    }
}
