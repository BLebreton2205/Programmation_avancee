package Eval;

public class Cosinus extends Noeud {

	Noeud value;
	public Cosinus(Noeud value) {
		this.value = value;
	}
		
	public float eval(float x) {
		return (float) Math.cos((double) value.eval(x));
	}
	
	public String toString() {
		return "cos("+value.toString()+")";
	}
}