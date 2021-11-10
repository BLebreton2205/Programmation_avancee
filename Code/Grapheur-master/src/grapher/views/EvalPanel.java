package grapher.views;

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

// Panel Sud
@SuppressWarnings( "serial" )
public class EvalPanel extends JPanel {
    private SingleFunction[] functions;
    private JButton          bEval;

    public EvalPanel() {
        super( new GridBagLayout() );
        this.setBackground( Color.LIGHT_GRAY );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 5, 50, 0, 0 );

        bEval = new JButton( "Eval" );
        this.add( bEval, gbc );

        gbc.gridx = 1;

        functions = new SingleFunction[2];
        functions[0] = new SingleFunction( "f(x):", "x*x", Color.RED );
        this.add( functions[0], gbc );

        gbc.gridx = 2;

        functions[1] = new SingleFunction( "g(x):", "", Color.BLUE );
        this.add( functions[1], gbc );
    }

    public String getFctText( int index ) {
        return functions[index].getFctText();
    }

    public void addColorListener( int index, ActionListener al ) {
        functions[index].addColorListener( al );
    }

    public void setBtColor( int index, Color newColor ) {
        functions[index].setBtColor( newColor );
    }

    public void addEvalListener( ActionListener al ) {
        bEval.addActionListener( al );
    }

    private class SingleFunction extends JPanel {
        protected JTextField textField;
        protected JLabel     lblF;
        protected JButton    colorChooser;

        public SingleFunction( String name, String defaultFct, Color defaultColor ) {
            super( new FlowLayout( FlowLayout.CENTER ) );
            this.setBackground( Color.LIGHT_GRAY );

            lblF = new JLabel( name );
            this.add( lblF );

            textField = new JTextField( 10 );
            textField.setText( defaultFct );
            // textField.setPreferredSize( bEval.getPreferredSize() );
            // On crée une autre bordure pour pouvoir mettre une marge de chaque
            // coté de la zone de texte
            textField.setBorder( BorderFactory.createCompoundBorder(
                    textField.getBorder(),
                    BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
            this.add( textField );

            colorChooser = new JButton();
            colorChooser.setBackground( defaultColor );
            colorChooser.setPreferredSize( new Dimension( 15, 15 ) );
            this.add( colorChooser );
        }

        public String getFctText() {
            return textField.getText();
        }

        public void setBtColor( Color newColor ) {
            colorChooser.setBackground( newColor );
        }

        public void addColorListener( ActionListener al ) {
            colorChooser.addActionListener( al );
        }
    }
}
