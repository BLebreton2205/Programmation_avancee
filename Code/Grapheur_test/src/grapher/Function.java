package grapher;

import java.awt.Color;

import eval.Analyseur;

public class Function {

    private Analyseur arbre;
    private Color     color;
    private boolean   enabled;

    public Function( Analyseur arbre, Color color ) {
        this.arbre = arbre;
        this.color = color;
        this.enabled = true;
    }

    public Function( Color color ) {
        this.color = color;
        this.enabled = false;
    }

    public float eval( float x ) {
        return arbre.eval( x );
    }

    public void setArbre( Analyseur arbre ) {
        this.arbre = arbre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnable( boolean enabled ) {
        this.enabled = enabled;
    }

}