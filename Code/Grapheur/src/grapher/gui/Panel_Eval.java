package grapher.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_Eval extends JPanel {
    public JButton Eval_btn;
    public JPanel fct_panel;
    public JTextField fct_text;
    public JLabel fx;
    
    public Panel_Eval() {
    	super( new GridBagLayout() );
        this.setBackground( Color.LIGHT_GRAY );

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets( 5, 50, 0, 0 );

        Eval_btn = new JButton( "Eval" );
        this.add( Eval_btn, c );

        c.gridx = 1;
        
        fct_panel = new JPanel();
        fct_panel.setLayout(new FlowLayout( FlowLayout.CENTER ));
        
        //Création du label et ajout
        fx = new JLabel( "f(x) = " );
        fct_panel.add( fx );
        
        //Création de la zone de texte et ajout
        fct_text = new JTextField("x*x");
        fct_text.setPreferredSize( Eval_btn.getPreferredSize() );
        fct_text.setBorder( BorderFactory.createCompoundBorder(
        		fct_text.getBorder(),
                BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
        fct_panel.add( fct_text );
        fct_panel.setBackground(Color.LIGHT_GRAY);
        
        
        this.add(fct_panel, c);
    }

    public String getFct() {
        return fct_text.getText();
    }
    public void addEvalListener( ActionListener al ) {
        Eval_btn.addActionListener( al );
    }
}