package Eval;

public class Sinus extends Fonction {

	public Sinus(Noeud f) {
		super(f);
	}
		
	public float eval(float x) {
		return (float) Math.sin((double) f.eval(x));
	}
	
	public String toString() {
		return "(sin("+f.toString()+")";
	}
}
