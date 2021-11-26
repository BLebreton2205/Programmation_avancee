package Eval;

public class Tangente extends Noeud {
	
	Noeud value;
	public Tangente(Noeud value) {
		this.value = value;
	}
	public float eval(float x) {
		return (float) Math.tan((double) value.eval(x));
	}
	@Override
	public String toString() {
		return "tan("+value.toString()+")";
	}

}
