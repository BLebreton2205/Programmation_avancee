package grapher.controllers;

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

import grapher.Function;
import grapher.PositionPanel;
import grapher.views.GrapherPanel;

public class GrapherAdapter implements MouseMotionListener, MouseWheelListener, MouseListener, ComponentListener {

    private GrapherPanel  centerView;
    private PositionPanel northView;
    private ActionAdapter westAdapter;
    private Point         lastMousePt;

    public GrapherAdapter( GrapherPanel centerView, PositionPanel northView, ActionAdapter westAdapter ) {
        this.centerView = centerView;
        this.northView = northView;
        this.westAdapter = westAdapter;

        centerView.addMouseMotionListener( this );
        centerView.addMouseWheelListener( this );
        centerView.addComponentListener( this );
        centerView.addMouseListener( this );
    }

    // Lorsque la fenêtre est redimentionnée, la hauteur et la largeur en pixels
    // de la zone de dessin sont mis à jour.
    @Override
    public void componentResized( ComponentEvent e ) {
        Dimension dim = e.getComponent().getSize();
        centerView.setDim( dim.width, dim.height );
    }

    // Le pointeur de la souris est transformé en cible lorsque la souris entre
    // dans la zone de dessin
    @Override
    public void mouseEntered( MouseEvent arg0 ) {
        centerView.setCursor( Cursor.getPredefinedCursor( Cursor.CROSSHAIR_CURSOR ) );
    }

    // Lorsque l'utilisateur clic sur une zone du dessin, on calcule les
    // coordonnées de la souris sur l'écran. Ce point servira de reference pour
    // calculer les valeurs du déplacement de la souris lors d'un drag & drop.
    @Override
    public void mousePressed( MouseEvent e ) {
        // Point de reference utilisé pour le drag & drop
        lastMousePt = e.getPoint();
    }

    // Un Scroll vers le haut declenche un zoom avant tandis qu'un scroll vers
    // le bas déclenche un zoom arrière.
    @Override
    public void mouseWheelMoved( MouseWheelEvent e ) {
        int notches = e.getWheelRotation();
        if ( notches < 0 ) {
            centerView.zoomPlus();
        } else {
            centerView.zoomMoins();
        }
        westAdapter.refreshInfos();
    }

    // Lors d'un drag & drop, on déplace le graphique en fonction du mouvement
    // de la souris. Ce déplacement est calculé en conservant les coordonnées de
    // la dernière position de la souris.
    @Override
    public void mouseDragged( MouseEvent e ) {
        double dx = e.getX() - lastMousePt.getX();
        double dy = e.getY() - lastMousePt.getY();
        lastMousePt = e.getPoint();
        centerView.move( dx, dy );
        westAdapter.refreshInfos();
    }

    // Lors d'un déplacement de la souris, on met à jour les coordonnées
    // affichées par le panel Nord (PositionPanel).
    @Override
    public void mouseMoved( MouseEvent e ) {
        DecimalFormat df = new DecimalFormat( "#.##" );
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator( '.' );
        df.setDecimalFormatSymbols( dfs );
        double[] coords = centerView.getMathsCoords( e.getX(), e.getY() );
        String posX = df.format( coords[0] );
        String posY = df.format( coords[1] );
        northView.setTxtX( posX );
        northView.setTxtY( posY );

        for ( int i = 0; i < 2; i++ ) {
            Function f = centerView.getFct( i );
            if ( f.isEnabled() ) {
                String cursorFct = df.format( f.eval( (float) coords[0] ) );
                northView.setTxtFct( i, cursorFct );
            }
        }
    }

    @Override
    public void componentHidden( ComponentEvent arg0 ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentMoved( ComponentEvent arg0 ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown( ComponentEvent arg0 ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked( MouseEvent arg0 ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited( MouseEvent arg0 ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased( MouseEvent arg0 ) {
        // TODO Auto-generated method stub

    }

}
