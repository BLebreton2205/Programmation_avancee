package Eval;

public class Plus extends NoeudBinaire{

	public Plus(Noeud fg, Noeud fd) {
		super(fg, fd);
	}
	
	public float eval(float x) {
		return fg.eval(x) + fd.eval(x);
	}
	
	public String toString() {
		return new String(fd.toString() + " + " + fg.toString());
	}
}
