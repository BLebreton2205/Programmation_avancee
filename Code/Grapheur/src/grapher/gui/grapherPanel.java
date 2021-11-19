package grapher.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Eval.Evaluateur;

public class grapherPanel extends JPanel {
	private int                 longueur, largueur;
    private float              pas, Xmin, Xmax, Ymin, Ymax, offsetX, offsetY, pxlX, pxlY;
    private boolean             autoPas;
    private Evaluateur			fct;
    private final float        defaultXmin   = -8;
    private final float        defaultXmax   = 8;
    private final float        defaultYmin   = -8;
    private final float        defaultYmax   = 8;
    private final float        defaultPas    = 0.01f;
    private final String        defaultFct    = "x*x";
	
	public grapherPanel() {
        super();
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        
        this.longueur = getSize().width;
        this.largueur = getSize().height;
        
        Xmin = defaultXmin;
        Xmax = defaultXmax;
        Ymin = defaultYmin;
        Ymax = defaultYmax;
        pas = defaultPas;
        autoPas = false;
        
        try {
			fct = new Evaluateur(defaultFct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
        super.paintComponent( g );
        //if ( longueur != 0 || largueur != 0 ) {
        drawAxes( g );
        drawFonc( g );
        //}
	}	
	
	public void setFct( Evaluateur newFct ) {
		fct = newFct;
        repaint();
    }
	
	public void drawAxes(Graphics g) {
		g.setColor( Color.BLACK );
		drawAbscisse(g);
		drawOrdonnee(g);
	}
	
	public void drawAbscisse(Graphics g) {
        int gradLength = (int) Math.min( 9, 60 / ( Xmax - Xmin ) );
        if ( Ymin <= 0 && Ymax >= 0 ) {
            g.drawLine( 0, (int) offsetY, longueur, (int) offsetY );
            for ( double i = offsetX; i > 0; i -= pxlX ) {
                g.drawLine( (int) i, (int) ( offsetY - gradLength ), (int) i, (int) offsetY );
            }
            for ( double i = offsetX + pxlX; i < longueur; i += pxlX ) {
                g.drawLine( (int) i, (int) ( offsetY - gradLength ), (int) i, (int) offsetY );
            }
        }
	}
	
	public void drawOrdonnee(Graphics g) {
        int gradLength = (int) Math.min( 9, 60 / ( Xmax - Xmin ) );
        if ( Xmin <= 0 && Xmax >= 0 ) {
            g.drawLine( (int) offsetX, 0, (int) offsetX, largueur );
            for ( double i = offsetY; i > 0; i -= pxlY ) {
                g.drawLine( (int) ( offsetX + gradLength ), (int) i, (int) offsetX, (int) i );
            }
            for ( double i = offsetY + pxlY; i < largueur; i += pxlY ) {
                g.drawLine( (int) ( offsetX + gradLength ), (int) i, (int) offsetX, (int) i );
            }
        }
	}

	public void drawFonc(Graphics g) {
		g.setColor(Color.RED);
		for ( float i = Xmin; i < Xmax; i += pas ) {
			float[] coords = getPxlCoords( i, fct.eval( (float) i ) );
			if ( coords[1] >= 0 && coords[1] <= largueur )
                g.fillRect( (int) coords[0], (int) coords[1], 2, 2 );
        }
	}
	
	private float[] getPxlCoords( float x, float y ) {
		float[] coords = new float[2];
        coords[0] = offsetX + x * pxlX;
        coords[1] = offsetY - y * pxlY;
        return coords;
    }

	public float[] getMathsCoords( float x, float y ) {
		float[] coords = new float[2];
        coords[0] = ( x - offsetX ) / pxlX;
        coords[1] = ( offsetY - y ) / pxlY;
        return coords;
    }

	private void updateRatio() {
        pxlX = longueur / ( Xmax - Xmin );
        pxlY = largueur / ( Ymax - Ymin );
        offsetX = ( longueur - Xmax * pxlX );
        offsetY = ( largueur + Ymin * pxlY );
    }

	private void calculatePas() {
        pas = 1 / pxlX;
    }
	
	public void zoomPlus() {
		float dx = -(Xmax - Xmin) / 5f;
		float dy = -(Ymax - Ymin) / 5f;
		Xmin = Xmin + dx;
		Xmax = Xmax - dx;
		Ymin = Ymin + dy;
		Ymax = Ymax - dy;
        updateRatio();
        if ( autoPas )
            calculatePas();
		repaint();		
	}
	
	public void zoomMoins() {
		float dx = -(Xmax - Xmin) / 5f;
		float dy = -(Ymax - Ymin) / 5f;
		Xmin = Xmin - dx;
		Xmax = Xmax + dx;
		Ymin = Ymin - dy;
		Ymax = Ymax + dy;
        updateRatio();
        if ( autoPas )
            calculatePas();
		repaint();
		
	}
	
	public void resetPos() {
        this.Xmin = defaultXmin;
        this.Xmax = defaultXmax;
        this.Ymin = defaultYmin;
        this.Ymax = defaultYmax;
        updateRatio();
        if ( autoPas )
            calculatePas();
        else
            this.pas = defaultPas;
        repaint();
    }
	
    public void setInfos( float Xmin, float Xmax, float Ymin, float Ymax, float pas ) {
        this.Xmin = Xmin;
        this.Xmax = Xmax;
        this.Ymin = Ymin;
        this.Ymax = Ymax;
        this.pas = pas;
        updateRatio();
        repaint();
    }

    public void move( float dx, float dy ) {
        Xmin -= dx / pxlX;
        Xmax -= dx / pxlX;
        Ymin += dy / pxlY;
        Ymax += dy / pxlY;
        updateRatio();
        repaint();
    }
    
    public void setDim( int longueur, int largueur ) {
        this.longueur = longueur;
        this.largueur = largueur;
        updateRatio();
        if ( autoPas )
            calculatePas();
    }
    
    public void setAutoPas( boolean b ) {
        autoPas = b;
        if ( autoPas )
            calculatePas();
        else
            pas = defaultPas;
        repaint();
    }

    public Evaluateur getFct() {
        return fct;
    }
    public double getPas() {
        return pas;
    }

    public double getXmin() {
        return Xmin;
    }

    public double getXmax() {
        return Xmax;
    }

    public double getYmin() {
        return Ymin;
    }

    public double getYmax() {
        return Ymax;
    }
}
