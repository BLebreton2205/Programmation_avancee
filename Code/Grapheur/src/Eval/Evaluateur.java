package Eval;
import java.util.Scanner;

public class Evaluateur {
    private Noeud node;
	
	public Evaluateur( String s ) throws Exception {
        this.node = Analyse(s);
        System.out.println( node.toString() );
    }
	
	public static Noeud Analyse(String s) {
		int index;
		String s1, s2;
		Noeud fg, fd;
		index = s.indexOf("+");
		if (index !=-1) {
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Plus(fg,fd);
			return n;
		}
		
		index = s.indexOf("-");
		if (index !=-1) {
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Moins(fg,fd);
			return n;
		}
		
		index = s.indexOf("/");
		if (index !=-1) {
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Divise(fg,fd);
			return n;
		}

		index = s.indexOf("*");
		if (index !=-1) {
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Mult(fg,fd);
			return n;
		}
		
		index = s.indexOf("x");
		if (index !=-1) {
			if((s.equals("x"))||(s.equals("+x"))) return new Variable();
			if((s.equals("-x"))) return new Variable();
		}
		
		try {
			float f = new Float(s).floatValue();
			return new Valeur(f);
		} catch(Exception e) {
			System.out.println("Expression invalide "+s);
			return null;
		}
		
	}

	public float eval( float x ) {
        return node.eval( x );
    }
	
	/*public static void main(String[] args) {
		int index;
		float x = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer une fonction :");
		String s = sc.nextLine();
		index = s.indexOf("x");
		if (index !=-1) {
			System.out.println("x = ");
			x = sc.nextFloat();
		}
		Noeud n = Analyse(s);
		System.out.println(n.toString());
		System.out.println(n.eval(x));
	}*/
}
