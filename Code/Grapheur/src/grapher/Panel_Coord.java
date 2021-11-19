package grapher;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_Coord extends JPanel {
    public JTextField   X_txt, Y_txt, fct_txt;
    public JLabel       X_label, Y_label, fct_label;
    
    public Panel_Coord() {
        super( new FlowLayout( FlowLayout.CENTER ) );
        this.setBackground( Color.LIGHT_GRAY );

        X_label = new JLabel();
        X_label.setText( "x = " );
        this.add( X_label );
    	
        X_txt = new JTextField( 5 );
        X_txt.setEditable( false );
        this.add( X_txt );

        Y_label = new JLabel();
        Y_label.setText( "y = " );
        this.add( Y_label );
        
        Y_txt = new JTextField( 5 );
        Y_txt.setEditable( false );
        this.add( Y_txt );
        
        fct_label = new JLabel();
        fct_label.setText( "f(x) = " );
        this.add( fct_label );

        fct_txt = new JTextField( 5 );
        fct_txt.setEditable( false );
        this.add( fct_txt );        
    }
    
    public void setX( String txt ) {
        X_txt.setText( txt );
    }

    public void setY( String txt ) {
        Y_txt.setText( txt );
    }

    public void setFct( String txt ) {
        fct_txt.setText( txt );
    }
}
