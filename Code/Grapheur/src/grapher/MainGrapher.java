package grapher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainGrapher extends JFrame{
	/*public MainGrapher() {
	      super("titre de l'application");

	      WindowListener l = new WindowAdapter() {
	         public void windowClosing(WindowEvent e){
	            System.exit(0);
	         }
	      };
	      
	      this.setLayout(null);
	      
	      Grapher panel = new Grapher();
	      
	      this.add(panel);

	      addWindowListener(l);
	      setSize(1500,600);
	      setVisible(true);
	   }*/
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        Grapher panel = new Grapher();
        frame.getContentPane().add( panel, BorderLayout.CENTER );
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.pack();
        frame.setSize( 3 * width / 4, 3 * height / 4 );
        // On centre la fenêtre au milieu de l'écran
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
	}
}
