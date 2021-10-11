package Eval;

public class Variable extends Noeud {
	public Variable() {
	}
	
	public float eval(float x) {
		return x;
	}
	
	public String toString() {
		return "x";
	}
}
