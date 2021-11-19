package Eval;

public class Divise extends NoeudBinaire {
	public Divise(Noeud fg, Noeud fd) {
		super(fg, fd);
	}
	
	public float eval(float x) {
		return fg.eval(x) / fd.eval(x);
	}
	
	public String toString() {
		return "("+fg.toString()  + " / " + fd.toString()+")";
	}

}
