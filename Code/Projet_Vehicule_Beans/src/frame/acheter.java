package frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class acheter {
	JTextField  fieldMarque;
	JTextField  fieldModele;
	JComboBox<String> fieldCouleur;
	JComboBox<String> type;
	JButton  acheter;
	
	public acheter(String[] Couleur, String[] Types) {
		fieldMarque = new JTextField ("Marque");
		fieldModele = new JTextField ("Modèle");
		fieldCouleur = new JComboBox<String>(Couleur);
		type = new JComboBox<String>(Types);
		acheter = new JButton ("Acheter");
	}
}
