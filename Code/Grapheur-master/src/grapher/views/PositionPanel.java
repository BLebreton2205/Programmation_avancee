package grapher.views;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Panel Nord permettant d'afficher les coordonnées x et y de la souris 
// sur le graphique ainsi que la valeur de la fonction f(x) correspondante.
@SuppressWarnings( "serial" )
public class PositionPanel extends JPanel {
    private JTextField   txtX, txtY;
    private JTextField[] txtFct;
    private JLabel       lblX, lblY;
    private JLabel[]     lblFct;

    public PositionPanel() {
        super( new FlowLayout( FlowLayout.CENTER ) );
        this.setBackground( Color.LIGHT_GRAY );

        lblX = new JLabel();
        lblX.setText( "x=" );
        this.add( lblX );

        // Coordonnée de la souris en abscisse
        txtX = new JTextField( 5 );
        txtX.setEditable( false );
        this.add( txtX );

        lblY = new JLabel();
        lblY.setText( "y=" );
        this.add( lblY );

        // Coordonnée de la souris en ordonnée
        txtY = new JTextField( 5 );
        txtY.setEditable( false );
        this.add( txtY );

        // Noms et valeurs des fonctions
        txtFct = new JTextField[2];
        lblFct = new JLabel[2];
        char fctName = 'f';
        for ( int i = 0; i < 2; i++ ) {
            // Le nom des fonctions est incrementé à partir de la lettre f
            String txt = (char) ( fctName + i ) + "(x)=";
            lblFct[i] = new JLabel( txt );
            this.add( lblFct[i] );

            // Valeurs des fonctions au point d'abscisse x de la souris
            txtFct[i] = new JTextField( 5 );
            txtFct[i].setEditable( false );
            this.add( txtFct[i] );
        }
    }

    public void setTxtX( String txt ) {
        txtX.setText( txt );
    }

    public void setTxtY( String txt ) {
        txtY.setText( txt );
    }

    public void setTxtFct( int index, String txt ) {
        txtFct[index].setText( txt );
    }
}
