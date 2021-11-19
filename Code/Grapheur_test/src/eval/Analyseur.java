package eval;

public class Analyseur {
    private Noeud arbre;

    public Analyseur( String s ) throws Exception {
        this.arbre = parse( s );
        System.out.println( arbre.toString() );
    }

    private Noeud parse( String s ) throws Exception {

        // On commence par supprimer les parenthèses inutiles si elles existent.
        int nb = s.indexOf( '(' );
        if ( nb != -1 ) {
            int nb2 = indexClosingBracket( s, nb );
            if ( nb == 0 && nb2 == s.length() - 1 ) {
                return parse( s.substring( 1, s.length() - 1 ) );
            }
        }

        // On calcule l'index du premier opérateur binaire s'il existe.
        nb = nextOp( s );
        if ( nb != -1 ) {
            // System.out.println( "operateur detecté: " + s.charAt( nb ) + "
            // fg: " + s.substring( 0, nb ) + " fd: "
            // + s.substring( nb + 1, s.length() ) );
            Noeud fg = parse( s.substring( 0, nb ) );
            Noeud fd = parse( s.substring( nb + 1, s.length() ) );
            // On crée le noeud correspondant à l'opérateur puis on applique la
            // méthode parse recursivement de part et d'autre de l'opérateur.
            return createNode( fg, fd, s.charAt( nb ) );
        }

        // S'il n'existe plus d'opérateurs binaires, on vérifie la présence
        // d'opérateurs unaires.
        if ( s.matches( "ln\\(.*\\)" ) ) {
            Noeud f = this.parse( s.substring( 3, s.length() - 1 ) );
            Noeud res = new NU( f, "ln" );
            return res;
        }

        if ( s.matches( "(abs|exp|log|sin|cos|tan)\\(.*\\)" ) ) {
            Noeud f = this.parse( s.substring( 4, s.length() - 1 ) );
            Noeud res = new NU( f, s.substring( 0, 3 ) );
            return res;
        }

        if ( s.matches( "sqrt\\(.*\\)" ) ) {
            Noeud f = this.parse( s.substring( 5, s.length() - 1 ) );
            Noeud res = new NU( f, "sqrt" );
            return res;
        }

        // On vérifie la présence de l'opérateur unaire négatif, à ne pas
        // confondre avec l'opérateur binaire de soustraction.
        nb = s.indexOf( '-' );
        if ( nb != -1 ) {
            String sub = s.substring( 0, nb );
            if ( sub.isEmpty() || String.valueOf( sub.charAt( sub.length() - 1 ) ).matches( "[-+*/]" ) ) {
                Noeud f = this.parse( s.substring( nb + 1, s.length() ) );
                Noeud res = new NU( f, "-" );
                return res;
            }
        }

        // Opérateur unaire permettant d'élever une expression au carré.
        nb = s.indexOf( '�' );
        if ( nb != -1 ) {
            Noeud f = this.parse( s.substring( 0, nb ) );
            Noeud res = new NU( f, "²" );
            return res;
        }

        // S'il n'existe plus d'opérateurs, on verifie la présence de variables
        // ou de nombres. On commence par verifier la présence de la variable x.
        // Ici on n'utilise pas: nb = s.indexOf( 'x' ); afin d'éviter qu'une
        // phrase du type "xyz" soit évaluée comme une fonction "x".
        if ( s.equals( "x" ) ) {
            VarX res = new VarX();
            return res;
        }

        // Enfin, on verifie la présence de nombre dans la phrase
        if ( s.matches( "^\\d+$" ) ) {
            Const res = new Const( Float.valueOf( s ) );
            return res;
        }

        else {
            throw new Exception(
                    "L'expression ne peut être évaluée. Veuillez vérifier la syntaxe de la fonction." );
        }
    }

    // Méthode permettant de calculer l'index d'un opérateur binaire contenu
    // dans une phrase. Les opérateurs binaires sont évalués selon leur ordre de
    // priorité lors de la construction de l'abre d'expression.
    // Les parenthèses ainsi que leur contenu sont masquées afin de retarder
    // leurs évaluation.
    private int nextOp( String s ) {
        System.out.println( s );
        // Le contenu des parenthèses est remplacé par des #.
        int nb = s.indexOf( '(' );
        if ( nb != -1 ) {
            int nb2 = indexClosingBracket( s, nb );
            if ( nb != 0 || nb2 != s.length() - 1 ) {
                String replace = "";
                for ( int i = 0; i < nb2 - nb + 1; i++ ) {
                    replace = replace + "#";
                }
                return nextOp( s.substring( 0, nb ) + replace + s.substring( nb2 + 1, s.length() ) );
            }
        }

        nb = s.indexOf( '+' );
        if ( nb != -1 )
            return nb;

        nb = s.indexOf( '-' );
        if ( nb != -1 && !s.substring( 0, nb ).isEmpty() )
            return nb;

        nb = s.indexOf( '*' );
        if ( nb != -1 )
            return nb;

        nb = s.indexOf( '/' );
        if ( nb != -1 )
            return nb;

        nb = s.indexOf( '^' );
        if ( nb != -1 )
            return nb;

        else
            return -1;
    }

    // Crée le noeud binaire approprié.
    // On aurait également pu mettre ce code dans la classe NB...
    private Noeud createNode( Noeud fg, Noeud fd, char op ) {
        switch ( op ) {
        case '+':
            return new Plus( fg, fd );
        case '-':
            return new Moins( fg, fd );
        case '*':
            return new Mult( fg, fd );
        case '/':
            return new Div( fg, fd );
        case '^':
            return new Pow( fg, fd );
        default:
            return new Plus( fg, fd );
        }
    }

    // Méthode permettant de calculer l'index d'une parenthèse fermante à partir
    // de la position d'une parenthèse ouvrante donnée en paramètre.
    private int indexClosingBracket( String s, int index ) {
        int i, count = 0;
        // On compte le nombre de parenthèses ouvrante et fermante.
        // Si on detecte une parenthèse fermante et que le compteur est à 0
        // alors on retourne l'index i.
        for ( i = index + 1; i < s.length() && ( count != 0 || s.charAt( i ) != ')' ); i++ ) {
            if ( s.charAt( i ) == '(' )
                count++;
            else if ( s.charAt( i ) == ')' )
                count--;
        }
        // si i = s.length() alors on renverra une erreur dans le
        // parser
        return i;
    }

    // Méthode prenant en paramètre une valeur pour la variable x et retournant
    // le résultat de l'évaluation de l'arbre d'expression correspondant à la
    // fonction f(x).
    public float eval( float x ) {
        return arbre.eval( x );
    }
}
