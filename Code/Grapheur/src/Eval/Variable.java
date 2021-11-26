package Eval;

public class Variable extends Noeud {
	
	public boolean moins = false;
	
	public Variable(boolean b) {
		this.moins = b;
	}
	
	public float eval(float x) {
		if (moins) return -x;
		return x;
	}
	
	public String toString() {
		if (moins) return "-x";
		return "x";
	}
}
