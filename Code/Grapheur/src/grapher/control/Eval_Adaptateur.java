package grapher.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Eval.Evaluateur;
import grapher.Panel_Coord;
import grapher.gui.Grapher;
import grapher.gui.Panel_Eval;

public class Eval_Adaptateur {

	private Grapher  Centre;
	private Panel_Eval Sud;
	private Panel_Coord Nord;
	
	public Eval_Adaptateur( Grapher  Centre, Panel_Eval Sud, Panel_Coord Nord ) {
	    this.Centre = Centre;
	    this.Nord = Nord;
	    this.Sud = Sud;
	    this.Sud.addEvalListener( new EvalListener() );
	}
	
	private class EvalListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            Evaluateur arbre;
            try {
                String txt = Sud.getFct();
                if ( !txt.isEmpty() ) {
                    arbre = new Evaluateur( txt );
                    Centre.setFct(arbre);
                } else {
                    Nord.setFct( "" );
                }
            } catch ( Exception ex ) {;}
        }
    }

}
