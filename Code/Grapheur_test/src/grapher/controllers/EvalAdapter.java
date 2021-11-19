package grapher.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import eval.Analyseur;
import grapher.PositionPanel;
import grapher.views.EvalPanel;
import grapher.views.GrapherPanel;

public class EvalAdapter {

    private GrapherPanel  centerView;
    private EvalPanel     southView;
    private PositionPanel northView;

    public EvalAdapter( GrapherPanel centerView, EvalPanel southView, PositionPanel northView ) {
        this.centerView = centerView;
        this.northView = northView;
        this.southView = southView;
        this.southView.addEvalListener( new EvalListener() );
        for ( int i = 0; i < 2; i++ ) {
            this.southView.addColorListener( i, new ColorListener( i ) );
        }
    }

    // Lors d'un clic sur le bouton Eval, on met à jour la fonction tracée par
    // le panel central (GrapherPanel).
    private class EvalListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            Analyseur arbre;
            for ( int i = 0; i < 2; i++ ) {
                try {
                    String txt = southView.getFctText( i );
                    if ( !txt.isEmpty() ) {
                        arbre = new Analyseur( txt );
                        centerView.setFct( i, arbre );
                    } else {
                        centerView.disableFct( i );
                        northView.setTxtFct( i, "" );
                    }
                } catch ( Exception ex ) {
                    JOptionPane.showMessageDialog( null, "Fonction invalide.",
                            null, JOptionPane.WARNING_MESSAGE );
                    // ex.printStackTrace();
                }
            }
        }
    }

    // Lors d'un clic sur le bouton représentant une couleur, on propose un
    // choix de couleurs à l'utilisateur puis on met à jour la couleur de la
    // fonction dans le panel central.
    private class ColorListener implements ActionListener {
        private int   index;
        private Color defaultColor[] = { Color.RED, Color.BLUE };

        public ColorListener( int index ) {
            this.index = index;
        }

        public void actionPerformed( ActionEvent e ) {
            Color newColor = JColorChooser.showDialog( null, "Choose a color", defaultColor[index] );
            if ( newColor != null ) {
                centerView.setFctColor( index, newColor );
                southView.setBtColor( index, newColor );
            }
        }
    }

}
