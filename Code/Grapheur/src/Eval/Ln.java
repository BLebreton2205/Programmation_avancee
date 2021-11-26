package Eval;

public class Ln extends Noeud {
	
	Noeud value;
	public Ln(Noeud value) {
		this.value = value;
	}
	public float eval(float x) {
		return (float) Math.log((double) value.eval(x));
	}
	@Override
	public String toString() {
		return "ln("+value.toString()+")";
	}

}

