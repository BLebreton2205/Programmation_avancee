package Eval;

public abstract class Fonction extends Noeud{
	Noeud f;
	public Fonction(Noeud f) {
		this.f = f;
	}
	
	public abstract float eval(float x);
	
	public abstract String toString();
}
