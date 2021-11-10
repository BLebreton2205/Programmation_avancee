package grapher.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import grapher.controllers.ActionAdapter;
import grapher.controllers.EvalAdapter;
import grapher.controllers.GrapherAdapter;

@SuppressWarnings( "serial" )
public class Window extends JFrame {

    private static EvalPanel     southView;
    private static PositionPanel northView;
    private static ActionPanel   westView;
    private static GrapherPanel  centerView;

    public Window() {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }

    private static void createAndShowGUI() {
        // Initialisation de la fenêtre
        JFrame frame = new JFrame( "Grapheur" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Ajout des 4 vues composant la fenêtre
        northView = new PositionPanel();
        frame.add( northView, BorderLayout.PAGE_START );

        westView = new ActionPanel();
        frame.add( westView, BorderLayout.WEST );

        centerView = new GrapherPanel();
        frame.getContentPane().add( centerView, BorderLayout.CENTER );

        southView = new EvalPanel();
        frame.add( southView, BorderLayout.PAGE_END );

        // On ajoute un panel panel vide à l'est pour eviter que le graphique
        // soit collé au bord droit de la fenêtre
        JPanel eastView = new JPanel();
        eastView.setBackground( Color.LIGHT_GRAY );
        frame.add( eastView, BorderLayout.EAST );

        // Controllers permettant de gérer les interactions entre les 4 vues
        EvalAdapter southAdapter = new EvalAdapter( centerView, southView, northView );
        ActionAdapter westAdapter = new ActionAdapter( centerView, westView );
        GrapherAdapter centerAdapter = new GrapherAdapter( centerView, northView, westAdapter );

        // La taille de la fenêtre fait 3/4 de la largeur et de la hauteur
        // totale de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.pack();
        frame.setSize( 3 * width / 4, 3 * height / 4 );
        // On centre la fenêtre au milieu de l'écran
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }
}