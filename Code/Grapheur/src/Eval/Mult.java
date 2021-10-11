package Eval;

public class Mult extends NoeudBinaire {
		public Mult(Noeud fg, Noeud fd) {
			super(fg, fd);
		}
		
		public float eval(float x) {
			return fg.eval(x) * fd.eval(x);
		}
		
		public String toString() {
			return "("+fg.toString()  + " * " + fd.toString()+")";
		}

}
