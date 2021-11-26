package Eval;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Evaluateur {
    private Noeud node;
	static ArrayList<Noeud> l = new ArrayList<Noeud>();
	int indice = 0;
	
	public Evaluateur( String s ) throws Exception {
        this.node = Analyse(s);
        System.out.println(this.node);
        l.clear();
    }
	
	/*
	 * La m�thode Analuyse est la m�thode la plus importante de cette classe.
	 * En effet, c'est elle qui va parcourir la cha�ne de caract�re en entr�e afin de rendre le r�sultat.
	 */
	public Noeud Analyse(String s) {
		int index, index2;
		String s1, s2;
		Noeud fg, fd;
		
		/*
		 * On commence par chercher les parenth�ses. Pour �a, on va utilise indexOf ainsi
		 * qu'une m�thode qui va chercher la parenth�se fermante associer
		 */
		
		index = s.indexOf("(");
		index2 = indexClose(s);
		
		if((index != -1)&&(index2 != -1)) {		//Si les deux indexes sont diff�rents de -1, alors on va analyser ce qu'ellle contienne.
			Noeud n = Analyse(s.substring(index+1, index2)); // On fait un appel r�cursif de la m�thode pour analyser le contenu des parenth�se
			l.add(n);	//On rajoute ensuite la sortie � notre liste qui contientle r�sultat de ce qui est en parenth�se
			s1 = s.substring(0, index);
			s2 = s.substring(index2+1, s.length());
			String s3 = s1+"@"+indice+s2; 	//ici, on renvoie notre cha�ne de caract�re en remplacant lec ontenue 
			indice += 1;					//des parenth�ses par @i, i �tant l'indice dans la liste
			return Analyse(s3);				//Et on renvoie l'appel r�cursif d'Analyse, cette fois avec notre String modifi�.
		}

		Noeud n = fct_parenthese(s);	//Cette m�thode va �tre utilis� pour la recherche de fonction parenth�s�
		if(n!= null) return n;
		
		n = chercheop(s);				//Et celle ci s'occupe des op�rateurs
		if(n!= null) return n;
		
		if(s.contains("@")) {			//Comme nous rempla�ons nos parenth�se et fonction par des @i, il faut bien les rempla�� par ceux � quoi il corresponde
			return findNoeud(s);		//On va donc utiliser cette m�thode qui va nous permettre de le remplacer par ce qui a �t� stock� dans l
		}
		
		index = s.indexOf("x");			//On v�rifie la pr�sence de variable dans la fonction
	if (index !=-1) {					//Et si c'est le cas
			if((s.equals("x"))||(s.equals("+x"))) return new Variable(false); //Il va cr�er une variable positive
			if((s.equals("-x"))) return new Variable(true);	//Ou cr�er une variable n�gative
		}
		
		try {	//En arrivant ici, soit notre s est soit une valeur, soit une erreur
			float f = new Float(s).floatValue();
			return new Valeur(f);
		} catch(Exception e) {
			System.out.println("Expression invalide "+s);
			return null;
		}
		
	}
	
	/*
	 * Cette m�thode permer de chercher l� parenth�se fermante.
	 */
	
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
	
	/*
	 * Cette m�thode permet de trouver le noeud dans la liste gr�ce � son indice
	 */

	public static Noeud findNoeud(String s) {
		String indice = s.substring(1, s.length());
		try {
			int i = Integer.parseInt(indice);
			return l.get(i);
		} catch(Exception e) {
			return null;
		}
	}
	
	/*
	 * Cette m�thode permet de s'ocuper des fonctions parenth�s�
	 */
	
	public Noeud fct_parenthese(String s) {
		int index;

		index = s.indexOf("sin");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Sinus(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}

		index = s.indexOf("cos");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Cosinus(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}

		index = s.indexOf("tan");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Tangente(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}

		index = s.indexOf("log");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Log(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}

		index = s.indexOf("ln");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+2,index+4);
			String s3 = s.substring(index+4, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Ln(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}
		
		index = s.indexOf("exp");
		if (index !=-1) {
			String s1 = s.substring(0, index);
			String s2 = s.substring(index+3,index+5);
			String s3 = s.substring(index+5, s.length());
			Noeud res = findNoeud(s2);
			Noeud n = new Exp(res);
			l.set(l.indexOf(res), n);
			return Analyse(s1+s2+s3);
		}
		
		return null;		
	}

	/*
	 * cette m�thode s'occupe des op�rateurs
	 */
	
	public Noeud chercheop(String s) {
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
		
		index = s.indexOf("^");
		if (index !=-1) {
			s1 = s.substring(0,index);
			s2 = s.substring(index+1,s.length());
			fg = Analyse(s1);
			fd = Analyse(s2);
			Noeud n = new Puissance(fg,fd);			
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
		return null;
	}
	
	public float eval( float x ) {
		return node.eval(x);
    }
}
