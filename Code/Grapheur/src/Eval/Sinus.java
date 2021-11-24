package Eval;

public class Sinus extends Noeud {

	Noeud value;
	public Sinus(Noeud value) {
		this.value = value;
	}
		
	public float eval(float x) {
		return (float) Math.sin((double) value.eval(x));
	}
	
	public String toString() {
		return "sin("+value.toString()+")";
	}
}
