package Eval;

public class Exp extends Noeud {
	
	Noeud value;
	public Exp(Noeud value) {
		this.value = value;
	}
	public float eval(float x) {
		return (float) Math.exp((double) value.eval(x));
	}
	@Override
	public String toString() {
		return "exp("+value.toString()+")";
	}

}
