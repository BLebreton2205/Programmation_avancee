package Eval;

public class Puissance extends NoeudBinaire {

	public Puissance(Noeud fg, Noeud fd) {
		super(fg, fd);
	}

	public float eval(float x) {
		return new Float(Math.pow(fg.eval(x), fd.eval(x))).floatValue();
	}

	public String toString() {
		return "("+fg.toString() + "^" + fd.toString() + ")";
	}
}