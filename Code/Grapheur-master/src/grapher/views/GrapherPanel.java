package grapher.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import eval.Analyseur;
import grapher.Function;

// Panel central
@SuppressWarnings( "serial" )
public class GrapherPanel extends JPanel {

    private int                 width, height, stroke;
    private double              pas, xmin, xmax, ymin, ymax, offsetX, offsetY, pxlX, pxlY;
    private ArrayList<Function> fcts;
    private boolean             autoPas, grid;
    private final double        defaultXmin   = -5;
    private final double        defaultXmax   = 5;
    private final double        defaultYmin   = -5;
    private final double        defaultYmax   = 5;
    private final double        defaultPas    = 0.01;
    private final int           defaultStroke = 1;
    private final Color[]       defaultColors = { Color.RED, Color.BLUE };
    private final String        defaultFct    = "x*x";

    public GrapherPanel() {
        super();
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );

        xmin = defaultXmin;
        xmax = defaultXmax;
        ymin = defaultYmin;
        ymax = defaultYmax;
        pas = defaultPas;
        stroke = defaultStroke;
        autoPas = false;
        grid = false;
        fcts = new ArrayList<Function>( 2 );

        try {
            fcts.add( new Function( new Analyseur( defaultFct ), defaultColors[0] ) );
            fcts.add( new Function( defaultColors[1] ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        // width et height sont à 0 lors de l'initialisation du JPanel
        if ( width != 0 || height != 0 ) {
            // On affiche la grille si l'utilisateur à coché l'option
            if ( grid ) {
                g.setColor( Color.LIGHT_GRAY );
                drawGrid( g );
            }
            // On affiche les axes des abscisses et des ordonnéess
            g.setColor( Color.BLACK );
            drawAxes( g );
            // On affiche la courbe de la fonction
            drawFct( g );
        }
    }

    // Méthode permettant d'afficher les axes des abscisses et des ordonnées
    // ainsi que leurs graduations respectives
    private void drawAxes( Graphics g ) {
        // La longueur des graduations dépend de l'échelle
        int gradLength = (int) Math.min( 9, 60 / ( xmax - xmin ) );
        // Axe des abscisses
        if ( ymin <= 0 && ymax >= 0 ) {
            g.drawLine( 0, (int) offsetY, width, (int) offsetY );
            // Graduations
            for ( double i = offsetX; i > 0; i -= pxlX ) {
                g.drawLine( (int) i, (int) ( offsetY - gradLength ), (int) i, (int) offsetY );
            }
            for ( double i = offsetX + pxlX; i < width; i += pxlX ) {
                g.drawLine( (int) i, (int) ( offsetY - gradLength ), (int) i, (int) offsetY );
            }
        }
        // Axe des ordonnées
        if ( xmin <= 0 && xmax >= 0 ) {
            g.drawLine( (int) offsetX, 0, (int) offsetX, height );
            // Graduations
            for ( double i = offsetY; i > 0; i -= pxlY ) {
                g.drawLine( (int) ( offsetX + gradLength ), (int) i, (int) offsetX, (int) i );
            }
            for ( double i = offsetY + pxlY; i < height; i += pxlY ) {
                g.drawLine( (int) ( offsetX + gradLength ), (int) i, (int) offsetX, (int) i );
            }
        }
    }

    // Méthode permettant d'afficher la courbe de la fonction
    private void drawFct( Graphics g ) {
        for ( double i = xmin; i < xmax; i += pas ) {
            // On récupère les coordonnées en pixel du point sur la courbe
            for ( int j = 0; j < fcts.size(); j++ ) {
                Function f = fcts.get( j );
                if ( f.isEnabled() ) {
                    g.setColor( f.getColor() );
                    double[] coords = getPxlCoords( i, f.eval( (float) i ) );
                    // On affiche le point sur le graphique uniquement s'il ne
                    // dépasse pas de l'écran
                    if ( coords[1] >= 0 && coords[1] <= height )
                        g.fillRect( (int) coords[0], (int) coords[1], stroke, stroke );
                }
            }
        }
    }

    // Méthode permettant d'afficher une grille sur le graphique
    private void drawGrid( Graphics g ) {
        // Lignes verticales
        for ( int i = (int) offsetX; i < width; i += pxlX ) {
            g.drawLine( i, height, i, 0 );
        }
        for ( int i = (int) ( offsetX - pxlX ); i > 0; i -= pxlX ) {
            g.drawLine( i, height, i, 0 );
        }
        // Lignes horizontales
        for ( int i = (int) offsetY; i < height; i += pxlY ) {
            g.drawLine( 0, i, width, i );
        }
        for ( int i = (int) ( offsetY - pxlY ); i > 0; i -= pxlY ) {
            g.drawLine( 0, i, width, i );
        }
    }

    // Méthode permettant de convertir des coordonnées mathématique en
    // coordonnées de pixel sur l'écran.
    private double[] getPxlCoords( double x, double y ) {
        double[] coords = new double[2];
        coords[0] = offsetX + x * pxlX;
        coords[1] = offsetY - y * pxlY;
        return coords;
    }

    // Méthode permettant de convertir des coordonnées de pixel en coordonnées
    // mathématique. Cette méthode sert principalement à afficher les
    // coordonnées en abscisse et en ordonnée de la souris dans le panel nord.
    public double[] getMathsCoords( double x, double y ) {
        double[] coords = new double[2];
        coords[0] = ( x - offsetX ) / pxlX;
        coords[1] = ( offsetY - y ) / pxlY;
        return coords;
    }

    // Méthode permettant de calculer le nombre de pixels correspondant à une
    // unité mathématique ainsi que les coordonnées en pixel du point d'origine.
    private void updateRatio() {
        // nombre de pixels correspondant à une unité en abscisse.
        pxlX = width / ( xmax - xmin );
        // nombre de pixels correspondant à une unité en ordonnée.
        pxlY = height / ( ymax - ymin );
        // coordonnées en pixel du point d'origine (décalage par rapport au coin
        // supérieur gauche de l'écran)
        offsetX = ( width - xmax * pxlX );
        offsetY = ( height + ymin * pxlY );
    }

    // Méthode permettant de modifier l'arbe d'évaluation de la fonction
    public void setFct( int index, Analyseur arbre ) {
        Function f = fcts.get( index );
        f.setArbre( arbre );
        f.setEnable( true );
        repaint();
    }

    public void disableFct( int index ) {
        Function f = fcts.get( index );
        f.setEnable( false );
        repaint();
    }

    // Méthode permettant de calculer le pas automatiquement si l'utilisateur a
    // coché cette option
    private void calculatePas() {
        // La valeur du pas est proportionnelle à l'echelle
        pas = 1 / pxlX;
    }

    // Le Zoom avant reduit l'intervalle d'affichage de la fonction d'un tiers
    // en abscisse comme en ordonnée
    public void zoomPlus() {
        double xrange = ( xmax - xmin );
        double yrange = ( ymax - ymin );
        xmin += xrange / 6;
        xmax -= xrange / 6;
        ymin += yrange / 6;
        ymax -= yrange / 6;
        updateRatio();
        if ( autoPas )
            calculatePas();
        repaint();
    }

    // Le Zoom arrière augmente l'intervalle d'affichage de la fonction de
    // moitié abscisse comme en ordonnée. De cette manière un zoom avant suivi
    // d'un zoom arrière ne change pas l'affichage de départ: (2/3)*x*(3/2) = x
    public void zoomMoins() {
        double xrange = ( xmax - xmin );
        double yrange = ( ymax - ymin );
        xmin -= xrange / 4;
        xmax += xrange / 4;
        ymin -= yrange / 4;
        ymax += yrange / 4;
        updateRatio();
        if ( autoPas )
            calculatePas();
        repaint();
    }

    // Méthode permettant de réinitialiser les bornes d'affichage de la
    // fonction à leurs valeurs par défaut.
    public void resetPos() {
        this.xmin = defaultXmin;
        this.xmax = defaultXmax;
        this.ymin = defaultYmin;
        this.ymax = defaultYmax;
        this.stroke = defaultStroke;
        updateRatio();
        if ( autoPas )
            calculatePas();
        else
            this.pas = defaultPas;
        repaint();
    }

    // Méthode permettant de déplacer l'affichage du graphique lors d'un drag &
    // drop.
    public void move( double dx, double dy ) {
        xmin -= dx / pxlX;
        xmax -= dx / pxlX;
        ymin += dy / pxlY;
        ymax += dy / pxlY;
        updateRatio();
        repaint();
    }

    // on utilise un setter general pour les attributs modifiés par le panel
    // ouest.
    public void setInfos( double xmin, double xmax, double ymin, double ymax, double pas, int stroke ) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.pas = pas;
        this.stroke = stroke;
        updateRatio();
        repaint();
    }

    // Setter pour la hauteur et la largeur en pixel de la zone de dessin du
    // graphique. Cette méthode est appelée par le controlleur lorsque la
    // fenêtre est redimensionnée ou lors de son initialisation.
    public void setDim( int width, int height ) {
        this.width = width;
        this.height = height;
        updateRatio();
        if ( autoPas )
            calculatePas();
    }

    // Setter pour l'auto pas
    public void setAutoPas( boolean b ) {
        autoPas = b;
        if ( autoPas )
            calculatePas();
        else
            pas = defaultPas;
        repaint();
    }

    // Setter pour la couleur de la fonction affichée à l'écran
    public void setFctColor( int index, Color color ) {
        Function f = fcts.get( index );
        f.setColor( color );
        repaint();
    }

    // Setter pour le quadrillage du graphique
    public void setGrid( boolean b ) {
        grid = b;
        repaint();
    }

    // Setter pour l'épaisseur du tracé de la courbe
    public void setStroke( int n ) {
        stroke = n;
        repaint();
    }

    public Function getFct( int index ) {
        return fcts.get( index );
    }

    public double getPas() {
        return pas;
    }

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }

    public int getStroke() {
        return stroke;
    }
}
