package frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class vendre {
	JTextField  fieldMarque;
	JTextField  fieldModele;
	JComboBox<String> fieldCouleur;
	JButton  vendre;
	
	public vendre(String[] Couleur) {
		fieldMarque = new JTextField ("Marque");
		fieldModele = new JTextField ("Mod�le");
		fieldCouleur = new JComboBox<String>(Couleur);
		vendre = new JButton ("Vendre");
	}
}
