package Eval;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Evaluateur {
    private Noeud node;
    protected static boolean good = false;
	static ArrayList<Noeud> l = new ArrayList<Noeud>();
	
	public Evaluateur( String s ) throws Exception {
        this.node = Analyse(s);
        System.out.println(this.node);
        l.clear();
    }
	
	public static Noeud Analyse(String s) {
		int index, index2, ouvert, ferme; int indice = 0;
		String s1, s2;
		Noeud fg, fd;
		
		index = s.indexOf("(");
		index2 = indexClose(s);
		//System.out.println(s);
		
		if((index != -1)&&(index2 != -1)) {
			System.out.println("(");
			Noeud n = Analyse(s.substring(index+1, index2)); // L'expression entre ()
			l.add(n);
			System.out.println(l);
			s1 = s.substring(0, index);
			s2 = s.substring(index2+1, s.length());
			String s3 = s1+"@"+indice+s2;
			indice += 1;
			return Analyse(s3);
		}

		index = s.indexOf("sin");
		if (index !=-1) {
			System.out.println("Sinus");
			
			s1 = s.substring(0, index);
			s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Sinus(res);
			System.out.println(l);
			l.set(l.indexOf(res), n);
			System.out.println(l);
			return Analyse(s1+s2+s3);
		}
		
		index = s.indexOf("+");
		if (index !=-1) {
			System.out.println("Plus");
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Plus(fg,fd);
			System.out.println(n.getClass());			
			return n;
		}
		
		index = s.indexOf("-");
		if (index !=-1) {
			System.out.println("Moins");
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Moins(fg,fd);
			return n;
		}
		
		index = s.indexOf("/");
		if (index !=-1) {
			System.out.println("Divise");
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Divise(fg,fd);
			return n;
		}

		index = s.indexOf("*");
		if (index !=-1) {
			System.out.println("Fois");
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Mult(fg,fd);
			return n;
		}
		
		if(s.contains("@")) {
			return findNoeud(s);
		}
		
		index = s.indexOf("x");
		if (index !=-1) {
			System.out.println("X");
			if((s.equals("x"))||(s.equals("+x"))) return new Variable();
			if((s.equals("-x"))) return new Variable();
		}
		
		try {
			System.out.println("val");
			float f = new Float(s).floatValue();
			return new Valeur(f);
		} catch(Exception e) {
			System.out.println("Expression invalide "+s);
			return null;
		}
		
	}
	
	public static int indexClose(String s)
	{
		int nb = 0;   
		for (int i=0; i < s.length(); i++)
		{
			if (s.charAt(i) == '(') nb++;
			if (s.charAt(i) == ')') {
				nb--;
				if(nb == 0) return i;
			}
		}
		return -1;
	}

	public static Noeud findNoeud(String s) {
		System.out.println("ok");
		String indice = s.substring(1, s.length());
		try {
			int i = Integer.parseInt(indice);
			return l.get(i);
		} catch(Exception e) {
			return null;
		}
	}
	
	public float eval( float x ) {
		return node.eval(x);
    }
}
