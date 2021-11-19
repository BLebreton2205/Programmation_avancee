package grapher.control;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import Eval.Evaluateur;
import grapher.Panel_Coord;
import grapher.gui.Grapher;
import grapher.gui.MenuGUI;


public class Grapheur_Adaptateur implements MouseMotionListener, MouseWheelListener, MouseListener, ComponentListener {

    private Grapher  Centre;
    private Panel_Coord Nord;
    private GUI_Adaptateur OuestAdapt;
    private Point         lastMousePt;
    
    public Grapheur_Adaptateur( Grapher  Centre, Panel_Coord Nord, GUI_Adaptateur OuestAdapt ) {
        this.Centre = Centre;
        this.Nord = Nord;
        this.OuestAdapt = OuestAdapt;

        Centre.addMouseMotionListener( this );
        Centre.addMouseWheelListener( this );
        Centre.addComponentListener( this );
        Centre.addMouseListener( this );
    }
    
	public void componentResized(ComponentEvent e) {
        Dimension dim = e.getComponent().getSize();
        Centre.setDim( dim.width, dim.height );
		
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if ( notches < 0 ) {
            Centre.zoomPlus();
        } else {
            Centre.zoomMoins();
        }
        OuestAdapt.refreshInfos();
	}

	public void mouseDragged(MouseEvent e) {
		float dx = (float) (e.getX() - lastMousePt.getX());
		float dy = (float) (e.getY() - lastMousePt.getY());
		lastMousePt = e.getPoint();
		Centre.move( dx, dy );
		OuestAdapt.refreshInfos();
	}

	public void mouseMoved(MouseEvent e) {
        DecimalFormat df = new DecimalFormat( "#.##" );
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator( '.' );
        df.setDecimalFormatSymbols( dfs );
        float[] coords = Centre.getMathsCoords( e.getX(), e.getY() );
        String posX = df.format( coords[0] );
        String posY = df.format( coords[1] );
        Nord.setX( posX );
        Nord.setY( posY );

        Evaluateur f = Centre.getFct();
        String cursorFct = df.format( f.eval( (float) coords[0] ) );
        Nord.setFct(cursorFct );
	}

	public void mousePressed(MouseEvent e) {
        lastMousePt = e.getPoint();		
	}
	

	public void mouseEntered(MouseEvent e) {
        Centre.setCursor( Cursor.getPredefinedCursor( Cursor.CROSSHAIR_CURSOR ) );		
	}
	
	public void componentMoved(ComponentEvent e) {
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
