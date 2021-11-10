package grapher.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Panel Ouest
@SuppressWarnings( "serial" )
public class ActionPanel extends JPanel {
    private JTextField txtXmin, txtXmax, txtYmin, txtYmax, txtPas, txtStroke;
    private JLabel     lblXmin, lblXmax, lblYmin, lblYmax, lblPas, lblStroke;
    private JButton    bRefresh, bReset, bZoomPlus, bZoomMoins;
    private JCheckBox  checkAutoPas, checkGrid;

    public ActionPanel() {
        super( new GridBagLayout() );
        this.setBackground( Color.LIGHT_GRAY );
        GridBagConstraints gbc = new GridBagConstraints();

        lblXmin = new JLabel( "xmin =" );
        this.add( lblXmin, createGbc( 0, 0 ) );

        txtXmin = new JTextField( 5 );
        this.add( txtXmin, createGbc( 1, 0 ) );

        lblXmax = new JLabel( "xmax =" );
        this.add( lblXmax, createGbc( 0, 1 ) );

        txtXmax = new JTextField( 5 );
        this.add( txtXmax, createGbc( 1, 1 ) );

        lblYmin = new JLabel( "ymin =" );
        this.add( lblYmin, createGbc( 0, 2 ) );

        txtYmin = new JTextField( 5 );
        this.add( txtYmin, createGbc( 1, 2 ) );

        lblYmax = new JLabel( "ymax =" );
        this.add( lblYmax, createGbc( 0, 3 ) );

        txtYmax = new JTextField( 5 );
        this.add( txtYmax, createGbc( 1, 3 ) );

        lblPas = new JLabel( "pas =" );
        this.add( lblPas, createGbc( 0, 4 ) );

        txtPas = new JTextField( 5 );
        this.add( txtPas, createGbc( 1, 4 ) );

        lblStroke = new JLabel( "épaisseur =" );
        this.add( lblStroke, createGbc( 0, 5 ) );

        txtStroke = new JTextField( 5 );
        this.add( txtStroke, createGbc( 1, 5 ) );

        bRefresh = new JButton( "Rafraîchir" );
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets( 20, 15, 0, 15 );
        this.add( bRefresh, gbc );

        bReset = new JButton( "Réinitialiser" );
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets( 10, 15, 0, 15 );
        this.add( bReset, gbc );

        bZoomMoins = new JButton( "-" );
        this.add( bZoomMoins, createGbc( 0, 8 ) );

        bZoomPlus = new JButton( "+" );
        gbc.insets = new Insets( 10, 15, 10, 10 );
        this.add( bZoomPlus, createGbc( 1, 8 ) );

        checkAutoPas = new JCheckBox( "Auto Pas" );
        checkAutoPas.setMnemonic( KeyEvent.VK_C );
        checkAutoPas.setSelected( false );
        checkAutoPas.setBackground( Color.LIGHT_GRAY );
        this.add( checkAutoPas, createGbc( 0, 9 ) );

        checkGrid = new JCheckBox( "Grille" );
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets( 0, 15, 0, 5 );
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        gbc.fill = GridBagConstraints.NONE;
        checkGrid.setMnemonic( KeyEvent.VK_C );
        checkGrid.setSelected( false );
        checkGrid.setBackground( Color.LIGHT_GRAY );
        this.add( checkGrid, gbc );
    }

    private GridBagConstraints createGbc( int col, int row ) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets( 15, 15, 0, 15 );
        if ( col == 0 ) {
            gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        } else {
            gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
        }
        return gbc;
    }

    public String getTxtXmin() {
        return txtXmin.getText();
    }

    public String getTxtXmax() {
        return txtXmax.getText();
    }

    public String getTxtYmin() {
        return txtYmin.getText();
    }

    public String getTxtYmax() {
        return txtYmax.getText();
    }

    public String getTxtPas() {
        return txtPas.getText();
    }

    public String getTxtStroke() {
        return txtStroke.getText();
    }

    public void setTxtXmin( String txt ) {
        txtXmin.setText( txt );
    }

    public void setTxtXmax( String txt ) {
        txtXmax.setText( txt );
    }

    public void setTxtYmin( String txt ) {
        txtYmin.setText( txt );
    }

    public void setTxtYmax( String txt ) {
        txtYmax.setText( txt );
    }

    public void setTxtPas( String txt ) {
        txtPas.setText( txt );
    }

    public void setTxtStroke( String txt ) {
        txtStroke.setText( txt );
    }

    public void addRefreshListener( ActionListener al ) {
        bRefresh.addActionListener( al );
    }

    public void addResetListener( ActionListener al ) {
        bReset.addActionListener( al );
    }

    public void addZoomPlusListener( ActionListener al ) {
        bZoomPlus.addActionListener( al );
    }

    public void addZoomMoinsListener( ActionListener al ) {
        bZoomMoins.addActionListener( al );
    }

    public void addAutoPasListener( ItemListener il ) {
        checkAutoPas.addItemListener( il );
    }

    public void addGridListener( ItemListener il ) {
        checkGrid.addItemListener( il );
    }
}
