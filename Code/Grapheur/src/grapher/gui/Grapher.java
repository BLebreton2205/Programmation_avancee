package grapher;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Eval.Evaluateur;

public class Grapher extends JPanel {

	float minX = -8;
	float maxX = 8;
	float minY = -8;
	float maxY = -8;
	
	float pas = 0.01f;
	
	float rangeX = 0;
	float rangeY = 0;
	
	float Ox = 0;
	float Oy = 0;
	float gridX = 1.0f;
	float gridY = 1.0f;
	
	ActionPanel ap;
	
	boolean autoPas = false;
	
	public Grapher() {
		super();
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		/*evaluateur = e;
		G
		t
		t*/
	}
	
	public float fonct(float x) {
		float e = 1/*Eval.Evaluateur.Analyse(x)*/;
		return e;
	}
	
	public void setaActionpannel(ActionPanel ap) {
		this.ap = ap;
	}
	
	/*public void reInitCoord() {
		ap.txmin.setText(new Float(Math.round(minX * 10000) / 10000f.toString()));
		ap.txmax.setText(new Float(Math.round(maxX * 10000) / 10000f.toString()));
		ap.tymin.setText(new Float(Math.round(minY * 10000) / 10000f.toString()));
		ap.tymax.setText(new Float(Math.round(maxY * 10000) / 10000f.toString()));
	}*/
	
	public void initRapports() {
		int w = getSize().width;
		rangeX = (maxX - minX) / w;
		int h = getSize().height;
		rangeY = (maxY - minY) / h;
		
	}
	
	public void drawFonc(Graphics g) {
		g.setColor(Color.RED);
		if (pas == 0f)
			pas = 0.001f;
		for (float x = minX; x < maxX;) {
			double y = fonct(x);
			float xi = Ox + (x / rangeX);
			float yi = Oy - (((float) y) / rangeY);
			g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi));
		}
	}
	
	public void zoomPlus() {
		float dx = -(maxX - minX) / 5f;
		float dy = -(maxY - minY) / 5f;
		minX = minX + dx;
		maxX = maxX - dx;
		minY = minY + dy;
		maxY = maxY - dy;
		//reInitCoord();
		/*if (autoPas) {
			pas = rangeX / 3f;
			//ap.tpas/SetTex
		}*/
		repaint();		
	}
	
	public void zoomMoins() {
		float dx = -(maxX - minX) / 5f;
		float dy = -(maxY - minY) / 5f;
		minX = minX - dx;
		maxX = maxX + dx;
		minY = minY - dy;
		maxY = maxY + dy;
		//reInitCoord();
		/*if (autoPas) {
			pas = rangeX / 3f;
			//ap.tpas/SetTex
		}*/
		repaint();
		
	}
	
	public void paint(Graphics g) {
		if (true)//!drag)
			initRapports();
		initOrigine();
		drawAxes(g);
		drawFonc(g);
	}

	public void drawAxes(Graphics g) {
		g.setColor( Color.BLACK );
		drawAbscisse(g);
		drawOrdonnee(g);
	}
	
	public void drawAbscisse(Graphics g) {
		g.drawLine(0, Math.round(Oy), getSize().width, Math.round(Oy));
		int tailleX = 8 - Math.round((maxX - minX) / (gridX * 10));
		if (tailleX <= 0)
			tailleX = -1;
		for (float x = -gridX; x> minX;) {
			float xi = Ox + (x / rangeX);
			float yi = Oy;
			g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi)-tailleX);
			x += gridX;
		}
		for (float x = gridX; x < maxX;) {
			float xi = Ox + (x / rangeX);
			float yi = Oy;
			g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi)-tailleX);
			x += gridX;
		}
	}
	
	public void drawOrdonnee(Graphics g) {
		g.drawLine(Math.round(Ox), 0, Math.round(Ox), getSize().height);
		int tailleY = 8 - Math.round((maxY - minY) / (gridY * 10));
		if (tailleY <= 0)
			tailleY = -1;
		for (float y = -gridY; y> minY;) {
			float yi = -(y / rangeY) + Oy;
			float xi = Ox;
			g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi)+tailleY, Math.round(yi));
			y += gridY;
		}
		for (float y = gridY; y < maxY;) {
			float yi = -(y / rangeY) + Oy;
			float xi = Ox;
			g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi)+tailleY, Math.round(yi));
			y += gridY;
		}
	}

	public void initOrigine() {
		Ox = -minX / rangeX;
		Oy = maxY / rangeY;
	}
	
	/*public void setAutoPas( boolean b ) {
        autoPas = b;
        if ( autoPas )
            calculatePas();
        else
            pas = defaultPas;
        repaint();
    }
	
	private void calculatePas() {
        // La valeur du pas est proportionnelle à l'echelle
        pas = 1 / dx;
    }*/
}