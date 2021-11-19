package grapher.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JOptionPane;

import grapher.views.ActionPanel;
import grapher.views.GrapherPanel;

public class ActionAdapter {
    private GrapherPanel centerView;
    private ActionPanel  westView;

    public ActionAdapter( GrapherPanel centerView, ActionPanel westView ) {
        this.centerView = centerView;
        this.westView = westView;
        this.westView.addRefreshListener( new RefreshListener() );
        this.westView.addResetListener( new ResetListener() );
        this.westView.addZoomMoinsListener( new ZoomMoinsListener() );
        this.westView.addZoomPlusListener( new ZoomPlusListener() );
        this.westView.addAutoPasListener( new AutoPasListener() );
        this.westView.addGridListener( new GridListener() );
        refreshInfos();
    }

    // Lors d'un clic sur le bouton Refresh, les nouvelles options sont envoyées
    // au panel central.
    private class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent arg0 ) {

            try {
                double xmin = Double.valueOf( westView.getTxtXmin() );
                double xmax = Double.valueOf( westView.getTxtXmax() );
                double ymin = Double.valueOf( westView.getTxtYmin() );
                double ymax = Double.valueOf( westView.getTxtYmax() );
                double pas = Double.valueOf( westView.getTxtPas() );
                int stroke = Integer.valueOf( westView.getTxtStroke() );

                if ( ymin >= ymax ) {
                    JOptionPane.showMessageDialog( null,
                            "ymin doit être inférieur à ymax.",
                            null, JOptionPane.WARNING_MESSAGE );
                } else if ( xmin >= xmax ) {
                    JOptionPane.showMessageDialog( null,
                            "xmin doit être inférieur à xmax.",
                            null, JOptionPane.WARNING_MESSAGE );
                } else
                    centerView.setInfos( xmin, xmax, ymin, ymax, pas, stroke );
            } catch ( NumberFormatException e ) {
                // e.getLocalizedMessage()
                JOptionPane.showMessageDialog( null,
                        "Veuillez vérifier les points suivants:\n- Aucun champs ne doit être vide"
                                + "\n- Tous les champs doivent correspondre à un nombre\n- L'épaisseur du tracé doit être une valeur entière",
                        null, JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    // Lors d'un clic sur le bouton Reset, les options d'affichage du graphique
    // sont réinitialisées à leurs valeurs par défaut.
    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            centerView.resetPos();
            refreshInfos();
        }
    }

    // Lors d'un clic sur le bouton "-", le panel central effectue un zoom
    // arrière et les données du graphique sont mis à jour dans le panel ouest.
    private class ZoomMoinsListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            centerView.zoomMoins();
            refreshInfos();
        }
    }

    // Lors d'un clic sur le bouton "+", le panel central effectue un zoom
    // avant et les données du graphique sont mis à jour dans le panel ouest.
    private class ZoomPlusListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            centerView.zoomPlus();
            refreshInfos();
        }
    }

    // Lorsque l'option "Auto pas" est selectionnée, on informe le panel central
    // que l'option est cochée puis on met à jour la valeur du champs "pas" avec
    // la nouvelle valeur du pas.
    private class AutoPasListener implements ItemListener {
        @Override
        public void itemStateChanged( ItemEvent e ) {
            centerView.setAutoPas( e.getStateChange() == ItemEvent.SELECTED );
            DecimalFormat df = new DecimalFormat( "#.#####" );
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator( '.' );
            df.setDecimalFormatSymbols( dfs );
            westView.setTxtPas( String.valueOf( df.format( centerView.getPas() ) ) );
        }
    }

    // Lorsque l'option "Grid" est selectionnée, on informe le panel central
    // qu'un quadrillage doit être affiché.
    private class GridListener implements ItemListener {
        @Override
        public void itemStateChanged( ItemEvent e ) {
            centerView.setGrid( e.getStateChange() == ItemEvent.SELECTED );
        }
    }

    // Méthode permettant de récupérer les données sur le graphique dans le
    // panel central puis de les afficher dans les champs appropriés du panel
    // ouest.
    public void refreshInfos() {
        // On formatte les données pour éviter qu'il ait trop de décimales
        // affichées à l'écran.
        DecimalFormat df = new DecimalFormat( "#.##" );
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator( '.' );
        df.setDecimalFormatSymbols( dfs );
        // df.setRoundingMode( RoundingMode.CEILING );
        westView.setTxtXmin( String.valueOf( df.format( centerView.getXmin() ) ) );
        westView.setTxtXmax( String.valueOf( df.format( centerView.getXmax() ) ) );
        westView.setTxtYmin( String.valueOf( df.format( centerView.getYmin() ) ) );
        westView.setTxtYmax( String.valueOf( df.format( centerView.getYmax() ) ) );
        westView.setTxtStroke( String.valueOf( df.format( centerView.getStroke() ) ) );

        // La valeur du pas étant faible on augmente le nombre de décimales
        // affichées.
        df = new DecimalFormat( "#.#####" );
        df.setDecimalFormatSymbols( dfs );
        westView.setTxtPas( String.valueOf( df.format( centerView.getPas() ) ) );
    }

}