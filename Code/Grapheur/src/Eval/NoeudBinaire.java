package Eval;

public abstract class NoeudBinaire extends Noeud {

	Noeud fg, fd;
	public NoeudBinaire(Noeud fg, Noeud fd) {
		this.fg = fg;
		this.fd = fd;
	}
		
	public abstract float eval(float x);
	
	public abstract String toString();
}
