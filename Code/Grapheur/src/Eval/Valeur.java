package Eval;

public class Valeur extends Noeud{

	Float value;
	public Valeur(float value) {
		this.value = value;
	}
	
	public float eval(float x) {
		return value;
	}
	
	public String toString() {
		return value.toString();
	}
}
