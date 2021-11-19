package grapher.gui;

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
import javax.swing.JSlider;
import javax.swing.JTextField;

public class MenuGUI extends JPanel{
    public JTextField Xmin_txt, Xmax_txt, Ymin_txt, Ymax_txt, Pas_txt;
    public JLabel    Xmin_label, Xmax_label, Ymin_label, Ymax_label, Pas_label;
    public JButton   Refresh_btn, Reset_btn, ZoomPlus_btn, ZoomMoins_btn;
    public JCheckBox  AutoPas_check, dark_theme;
	
	public MenuGUI() {
        super( new GridBagLayout() );
        this.setBackground( Color.LIGHT_GRAY );
        GridBagConstraints c = new GridBagConstraints();
        
        Xmin_label = new JLabel( "xmin =" );
        this.add( Xmin_label, Place( 0, 0 ) );

        Xmin_txt = new JTextField( 8 );
        this.add( Xmin_txt, Place( 1, 0 ) );

        Xmax_label = new JLabel( "xmax =" );
        this.add( Xmax_label, Place( 0, 1 ) );

        Xmax_txt = new JTextField( 5 );
        this.add( Xmax_txt, Place( 1, 1 ) );

        Ymin_label = new JLabel( "ymin =" );
        this.add( Ymin_label, Place( 0, 2 ) );

        Ymin_txt = new JTextField( 5 );
        this.add( Ymin_txt, Place( 1, 2 ) );

        Ymax_label = new JLabel( "ymax =" );
        this.add( Ymax_label, Place( 0, 3 ) );

        Ymax_txt = new JTextField( 5 );
        this.add( Ymax_txt, Place( 1, 3 ) );

        Pas_label = new JLabel( "pas =" );
        this.add( Pas_label, Place( 0, 4 ) );

        Pas_txt = new JTextField( 5 );
        this.add( Pas_txt, Place( 1, 4 ) );
        
        Refresh_btn = new JButton( "Rafraichir" );
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.BASELINE;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets( 20, 15, 0, 15 );
        this.add( Refresh_btn, c );
        
        Reset_btn = new JButton( "Réinitialiser" );
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets( 10, 15, 0, 15 );
        this.add( Reset_btn, c );

        ZoomMoins_btn = new JButton( "-" );
        this.add( ZoomMoins_btn, Place( 0, 7 ) );

        ZoomPlus_btn = new JButton( "+" );
        c.insets = new Insets( 10, 15, 10, 10 );
        this.add( ZoomPlus_btn, Place( 1, 7 ) );

        AutoPas_check = new JCheckBox( "Auto Pas" );
        AutoPas_check.setMnemonic( KeyEvent.VK_C );
        AutoPas_check.setSelected( false );
        AutoPas_check.setBackground( Color.LIGHT_GRAY );
        this.add( AutoPas_check, Place( 0, 8 ) );
        
        dark_theme = new JCheckBox( "Théme Sombre" );
        dark_theme.setMnemonic( KeyEvent.VK_C );
        dark_theme.setSelected( false );
        dark_theme.setBackground( Color.LIGHT_GRAY );
        this.add( dark_theme, Place( 1, 8 ) );
	}
	
	public GridBagConstraints Place(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets( 15, 15, 0, 15 );
        if ( x == 0 ) {
            c.anchor = GridBagConstraints.BASELINE_LEADING;
        } else {
            c.anchor = GridBagConstraints.BASELINE_TRAILING;
            c.gridwidth = GridBagConstraints.REMAINDER;
        }
        return c;
	}


    public String getXmin() {
        return Xmin_txt.getText();
    }

    public String getXmax() {
        return Xmax_txt.getText();
    }

    public String getYmin() {
        return Ymin_txt.getText();
    }

    public String getYmax() {
        return Ymax_txt.getText();
    }

    public String getPas() {
        return Pas_txt.getText();
    }

    public void setXmin( String txt ) {
        Xmin_txt.setText( txt );
    }

    public void setXmax( String txt ) {
        Xmax_txt.setText( txt );
    }

    public void setYmin( String txt ) {
        Ymin_txt.setText( txt );
    }

    public void setYmax( String txt ) {
        Ymax_txt.setText( txt );
    }

    public void setPas( String txt ) {
        Pas_txt.setText( txt );
    }


    public void addRefreshListener( ActionListener al ) {
        Refresh_btn.addActionListener( al );
    }

    public void addResetListener( ActionListener al ) {
        Reset_btn.addActionListener( al );
    }

    public void addZoomPlusListener( ActionListener al ) {
        ZoomPlus_btn.addActionListener( al );
    }

    public void addZoomMoinsListener( ActionListener al ) {
        ZoomMoins_btn.addActionListener( al );
    }

    public void addAutoPasListener( ItemListener il ) {
        AutoPas_check.addItemListener( il );
    }
    
    public void addDarkThemeListener( ItemListener il ) {
        dark_theme.addItemListener( il );
    }

}
