package Eval;
import java.util.Scanner;

public class Evaluateur {

	public static double f(double x) {
		return 3.0 + x;
		
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

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer une fonction :");
		//String s = "3*x+2";
		String s = sc.nextLine();
		System.out.println("x = ");
		float x = sc.nextFloat();
		Noeud n = Analyse(s);
		System.out.println(n.toString());
		System.out.println(n.eval(x));
	}
}
