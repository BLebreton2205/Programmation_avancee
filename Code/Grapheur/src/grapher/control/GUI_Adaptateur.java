package grapher.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import grapher.Panel_Coord;
import grapher.gui.Grapher;
import grapher.gui.Grapher_Frame;
import grapher.gui.MenuGUI;
import grapher.gui.Panel_Est;
import grapher.gui.Panel_Eval;

public class GUI_Adaptateur {
    private Grapher Centre;
    private MenuGUI Ouest;
    private Panel_Coord Nord;
    private Panel_Eval Sud;
    private Panel_Est Est;
    
   
    public GUI_Adaptateur(Grapher Centre, MenuGUI Ouest, Panel_Coord Nord, Panel_Eval Sud, Panel_Est Est) {
        this.Centre = Centre;
        this.Ouest = Ouest;
        this.Sud = Sud;
        this.Nord = Nord;
        this.Est = Est;
        this.Ouest.addRefreshListener( new RefreshListener() );
        this.Ouest.addResetListener( new ResetListener() );
        this.Ouest.addZoomMoinsListener( new ZoomMoinsListener() );
        this.Ouest.addZoomPlusListener( new ZoomPlusListener() );
        this.Ouest.addAutoPasListener( new AutoPasListener() );
        this.Ouest.addDarkThemeListener( new DarkThemeListener() );
        refreshInfos();
    	
    }
    
    private class RefreshListener implements ActionListener {
        public void actionPerformed( ActionEvent arg0 ) {

            try {
                float Xmin = Float.parseFloat( Ouest.getXmin() );
                float Xmax = Float.parseFloat( Ouest.getXmax() );
                float Ymin = Float.parseFloat( Ouest.getYmin() );
                float Ymax = Float.parseFloat( Ouest.getYmax() );
                float pas = Float.parseFloat( Ouest.getPas() );

                if ( Ymin >= Ymax ) {
                    JOptionPane.showMessageDialog( null,
                            "ymin doit être inférieur à  ymax.",
                            null, JOptionPane.WARNING_MESSAGE );
                } else if ( Xmin >= Xmax ) {
                    JOptionPane.showMessageDialog( null,
                            "Xmin doit être inférieur à xmax.",
                            null, JOptionPane.WARNING_MESSAGE );
                } else
                    Centre.setInfos( Xmin, Xmax, Ymin, Ymax, pas );
            } catch ( NumberFormatException e ) {;}
        }
    }
    
    private class ResetListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            Centre.resetPos();
            refreshInfos();
        }
    }
    
    private class ZoomMoinsListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            Centre.zoomMoins();
            refreshInfos();
        }
    }
    
    private class ZoomPlusListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            Centre.zoomPlus();
            refreshInfos();
        }
    }
    
    private class AutoPasListener implements ItemListener {
        public void itemStateChanged( ItemEvent e ) {
            Centre.setAutoPas( e.getStateChange() == ItemEvent.SELECTED );
            DecimalFormat df = new DecimalFormat( "#.#####" );
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator( '.' );
            df.setDecimalFormatSymbols( dfs );
            Ouest.setPas( String.valueOf( df.format( Centre.getPas() ) ) );
        }
    }
    
    private class DarkThemeListener implements ItemListener {
        public void itemStateChanged( ItemEvent e ) {
        	if (e.getStateChange() == ItemEvent.SELECTED) {
        		Centre.setBackground(Color.LIGHT_GRAY);
        		Sud.setBackground(Color.DARK_GRAY);
        		Sud.fx.setForeground(Color.WHITE);
        		Sud.fct_panel.setBackground(Color.DARK_GRAY);
        		Nord.setBackground(Color.DARK_GRAY);
        		Nord.fct_label.setForeground(Color.WHITE);
        		Nord.X_label.setForeground(Color.WHITE);
        		Nord.Y_label.setForeground(Color.WHITE);
        		Est.setBackground(Color.DARK_GRAY);
        		Ouest.setBackground(Color.DARK_GRAY);
        		Ouest.dark_theme.setBackground(Color.DARK_GRAY);
        		Ouest.AutoPas_check.setBackground(Color.DARK_GRAY);
        		Ouest.Pas_label.setForeground(Color.WHITE);
        		Ouest.Xmax_label.setForeground(Color.WHITE);
        		Ouest.Xmin_label.setForeground(Color.WHITE);
        		Ouest.Ymax_label.setForeground(Color.WHITE);
        		Ouest.Ymin_label.setForeground(Color.WHITE);
        		Ouest.AutoPas_check.setForeground(Color.WHITE);
        		Ouest.dark_theme.setForeground(Color.WHITE);
        	}
        	else {
	    		Centre.setBackground(Color.WHITE);
	    		Sud.setBackground(Color.LIGHT_GRAY);
        		Sud.fct_panel.setBackground(Color.LIGHT_GRAY);
        		Sud.fx.setForeground(Color.BLACK);
	    		Nord.setBackground(Color.LIGHT_GRAY);
        		Nord.fct_label.setForeground(Color.BLACK);
        		Nord.X_label.setForeground(Color.BLACK);
        		Nord.Y_label.setForeground(Color.BLACK);
	    		Est.setBackground( Color.LIGHT_GRAY );
	    		Ouest.setBackground(Color.LIGHT_GRAY);
        		Ouest.dark_theme.setBackground(Color.LIGHT_GRAY);
        		Ouest.AutoPas_check.setBackground(Color.LIGHT_GRAY);
        		Ouest.Pas_label.setForeground(Color.BLACK);
        		Ouest.Xmax_label.setForeground(Color.BLACK);
        		Ouest.Xmin_label.setForeground(Color.BLACK);
        		Ouest.Ymax_label.setForeground(Color.BLACK);
        		Ouest.Ymin_label.setForeground(Color.BLACK);
        		Ouest.AutoPas_check.setForeground(Color.BLACK);
        		Ouest.dark_theme.setForeground(Color.BLACK);
        	}
        }
    }
    
    public void refreshInfos() {
        DecimalFormat df = new DecimalFormat( "#.##" );
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator( '.' );
        df.setDecimalFormatSymbols( dfs );
        Ouest.setXmin( String.valueOf( df.format( Centre.getXmin() ) ) );
        Ouest.setXmax( String.valueOf( df.format( Centre.getXmax() ) ) );
        Ouest.setYmin( String.valueOf( df.format( Centre.getYmin() ) ) );
        Ouest.setYmax( String.valueOf( df.format( Centre.getYmax() ) ) );

        df = new DecimalFormat( "#.#####" );
        df.setDecimalFormatSymbols( dfs );
        Ouest.setPas( String.valueOf( df.format( Centre.getPas() ) ) );
    }
    

}
