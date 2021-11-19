package grapher.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import grapher.Panel_Coord;
import grapher.control.Eval_Adaptateur;
import grapher.control.GUI_Adaptateur;
import grapher.control.Grapheur_Adaptateur;

public class Grapher_Frame extends JFrame{

    private static Panel_Coord Nord;
    private static Grapher Centre;
    private static Panel_Eval Sud;
    private static MenuGUI   Ouest;
    private static Panel_Est   Est;
    
	public Grapher_Frame() {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                GUI();
            }
        } );
	}
	
	public void GUI() {
		JFrame frame = new JFrame("Grapheur");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Nord = new Panel_Coord();
        frame.add( Nord, BorderLayout.PAGE_START );
		Centre = new Grapher();
        frame.getContentPane().add( Centre, BorderLayout.CENTER );
        Sud = new Panel_Eval();
        frame.add( Sud, BorderLayout.PAGE_END );
		Ouest = new MenuGUI();
        frame.add( Ouest, BorderLayout.WEST );
        Est = new Panel_Est();
        frame.add( Est, BorderLayout.EAST );
        

        Eval_Adaptateur SudAdapt = new Eval_Adaptateur( Centre, Sud, Nord );
        GUI_Adaptateur OuestAdapt = new GUI_Adaptateur( Centre, Ouest, Nord, Sud, Est);
        Grapheur_Adaptateur CentreAdapt = new Grapheur_Adaptateur( Centre, Nord, OuestAdapt );

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.pack();
        frame.setSize( 3 * width / 4, 3 * height / 4 );
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
	}
}