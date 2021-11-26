package Eval;

public class Log extends Noeud {
	
	Noeud value;
	public Log(Noeud value) {
		this.value = value;
	}
	public float eval(float x) {
		return (float) Math.log10((double) value.eval(x));
	}
	@Override
	public String toString() {
		return "log("+value.toString()+")";
	}

}
