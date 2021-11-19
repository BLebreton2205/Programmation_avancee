package frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Vehicule.Vehicule;
import Vehicule.Voiture;
import Vehicule.Moto;
import Vehicule.Camion;

import fonction.*;

public class VehiculeFrame extends JFrame implements ActionListener {
	fonction_utile fu = new fonction_utile();
	
	//J'initialise des variables qui me seront utiles dans la suite du programme
	
	ArrayList<boutton> List = new ArrayList<boutton>();	//Notre ArrayList principal.
	JTextArea interface_Text;							//La zone de texte où s'affichera les textes
	JLabel bouton_label = new JLabel();					//Le label où seront afficher les boutons
	int tp = 0;											//Il s'agit d'un variable qui me servira a l'affichage de mes boutons selon leur types
	String[] Types = new String[] {"Moto","Camion",		//Il s'agit de notre liste de type de véhicules.
			"Voiture"};
	String[] Couleur = new String[] {"Blanc","Bleu",		//Il s'agit de notre liste de couleur possible pour les véhicules.
			"Gris","Jaune","Noir","Rouge","Vert","Autre"};
	
	//Création des composants pour la recherche
	
	recherche r = new recherche();
	
	//Création des composants pour la vente

	vendre ve = new vendre(Couleur);
	
	//Création des composants pour l'achat
	
	acheter a = new acheter(Couleur, Types);
	
	public VehiculeFrame(){			//ma méthode constructrice pour créer ma fenêtre
		super("Mes Véhicules");		//Elle aura pour titre : "Mes Véhicules"
		ArrayList<Vehicule> v = new ArrayList<Vehicule>();	//cette ArrayList va nous servir à créer notre liste de véhicule
		
		//On crée nos véhicules
		v.add(new Voiture("Citroen","C4","Blanc"));
		v.add(new Voiture("Lotus","Elise","Blanc"));
		v.add(new Voiture("Audi","R7","Rouge"));
		v.add(new Moto("Yamaha","2A8", "Noir"));
		v.add(new Moto("Honda","CBR650R", "Bleu"));
		v.add(new Camion("Scania", "V8", "Rouge"));
		v.add(new Camion("Volkswagen", "Constellation", "Jaune"));
		
		//Et on remplie notre liste
		for(int i = 0; i<v.size(); i++) {
			boutton b = new boutton();		//on créer un élément de type boutton
			b.vehicule=v.get(i);			//on lui ajoute comme composante vehicule le véhicule en indice i
			b.bouton=null;					//Comme on initie, on ne lui donne pas encore de JButton
			List.add(b);					//et on ajoute ce magnifique bouton à List
		}
		
		bouton_label.setBounds(0,0,725,550);	//on donne la taille du JLabel contenant les boutons
		this.add(bouton_label);					//et on l'ajoute à notre JFrame
		
		//Création de la zone de texte
		interface_Text = new JTextArea();	
		interface_Text.setEditable(false);			//cela permet d'avoir une zone de texte où il est impossible d'écrire
		interface_Text.setBounds(725,5,250,730);	//On lui donne sa taille
		fu.List_Nom(List,bouton_label,interface_Text,this);			//On utilise la méthode List_Nom pour afficher dans la interface_Text la liste des véhicule
		JScrollPane scrollPane = new JScrollPane(interface_Text);
		scrollPane.setBounds(725,5,260,730);	//On lui donne sa taille
		this.add(scrollPane);					//et on l'ajoute à notre JFrame		
		
		//On gères les tailles des composants de :
		//	-la recherche
		r.fieldMarque.setBounds(185,600,100,25); r.fieldModele.setBounds(310,600,100,25); r.recherche.setBounds(435,600,105,25);

		//	-la vente
		ve.fieldMarque.setBounds(125,650,100,25); ve.fieldModele.setBounds(250,650,100,25); ve.fieldCouleur.setBounds(375,650,100,25); ve.vendre.setBounds(500,650,100,25);

		//	-l'achat
		a.fieldMarque.setBounds(65,700,100,25); a.fieldModele.setBounds(190,700,100,25); a.fieldCouleur.setBounds(315,700,100,25); a.type.setBounds(440,700,100,25); a.acheter.setBounds(565,700,100,25);
		
		//On donnes des actions aux boutons de :				
		r.recherche.addActionListener(this);//	-la recherche
		ve.vendre.addActionListener(this);//		-la vente
		a.acheter.addActionListener(this);//		-l'achat

		//On à la JFrame les des composants de :
		//	-la recherche
		this.add(r.fieldMarque); this.add(r.fieldModele); this.add(r.recherche);

		//	-la vente
		this.add(ve.fieldMarque); this.add(ve.fieldModele); this.add(ve.fieldCouleur); this.add(ve.vendre);
	    
	    //	-l'achat
		this.add(a.fieldMarque); this.add(a.fieldModele); this.add(a.fieldCouleur); this.add(a.acheter); this.add(a.type);
	    
		fu.Bouton(List, bouton_label, tp, this);	//On utilise la mthode Bouton pour afficher tous les boutons (comme tp = 0)
		bouton_label.setVisible(true);	//On rend le JPanel visible
		
		initMenu();					//On appelle la méthode initMenu() pour crée la barre de menu
	}

	public static void main(String[] string) {
		VehiculeFrame ad = new VehiculeFrame();				//On crée notre JFrame
		
	    ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//On s'assure que lorsque l'on ferme la fenêtre, le programme s'arrête
		ad.setSize(1000, 800);			//On initie la taille de notre JFrame
		ad.setLayout(null);
		ad.setVisible(true);			//Et on la rend visible		
	}

	//Ici, on s'occupe de la création de la barre de menu	
	public void initMenu() {
		JMenuBar m = new JMenuBar();								//on créer la barre de menu
		JMenu list = new JMenu("Liste");							//puis le premier bouton du menu
		JMenuItem list_v = new JMenuItem("Liste des véhicules");	//cette item sera placer dans le premier bouton
		list_v.addActionListener(this);								//on lui rajoute une action lors de l'enclenchement
		list.add(list_v);											//on ajoute l'item au bouton
		m.add(list);												//et le bouton au menu

		JMenu T = new JMenu("Trier par type");
		JMenuItem V = new JMenuItem("Tout");
		V.addActionListener(this);
		T.add(V);
		m.add(T);
		V = new JMenuItem("Camion");
		V.addActionListener(this);
		T.add(V);
		m.add(T);
		V = new JMenuItem("Moto");
		V.addActionListener(this);
		T.add(V);
		m.add(T);
		V = new JMenuItem("Voiture");
		V.addActionListener(this);
		T.add(V);
		m.add(T);
		
		JMenu Trie = new JMenu("Trier par couleur");
		JMenuItem item;
		for(int i = 0; i < Couleur.length; i++) {
			item = new JMenuItem(Couleur[i]);
		    item.addActionListener(this);
		    Trie.add(item);
		}
	    m.add(Trie);
	    
		setJMenuBar(m);									//ici, on place la barre de menu pourqu'elle soit visible
	}
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();		//on utilise la variable event qui représente l'action enclenché pour switcher dessus
		switch(event) {
			case "Liste des véhicules":				//on vérifie son égalité
				fu.List_Nom(List,bouton_label,interface_Text,this);							//on lance la méthode voulue
				break;								//puis on stop le switch
				
			//et ainsi de suite pour chaque cas
			case "Recherche":
				if(this.r.fieldMarque.getText().equals("Marque")||this.r.fieldMarque.getText().equals("")||this.r.fieldMarque.getText().equals(" ")) this.interface_Text.setText("Remplissez la partie \"Marque\" avant de\nlancer la recherche");
				else if(this.r.fieldModele.getText().equals("Modèle")||this.r.fieldModele.getText().equals("")||this.r.fieldModele.getText().equals(" ")) this.interface_Text.setText("Remplissez la partie \"Modèle\" avant de\nlancer la recherche");
				else fu.Recherche(List, interface_Text, this.r.fieldMarque.getText(), this.r.fieldModele.getText());
				break;
				
			case "Vendre":
				if(ve.fieldMarque.getText().equals("Marque")||ve.fieldMarque.getText().equals("")||ve.fieldMarque.getText().equals(" ")) interface_Text.setText("Remplissez la partie \"Marque\" avant de vendre");
				else if(ve.fieldModele.getText().equals("Modèle")||ve.fieldModele.getText().equals("")||ve.fieldModele.getText().equals(" ")) interface_Text.setText("Remplissez la partie \"Modèle\" avant de vendre");
				else fu.Vendre(List, bouton_label, interface_Text,ve.fieldMarque.getText(), ve.fieldModele.getText(), ve.fieldCouleur.getSelectedItem().toString(), tp, this);
				break;
				
			case "Acheter":
				if(a.fieldMarque.getText().equals("Marque")||a.fieldMarque.getText().equals("")||a.fieldMarque.getText().equals(" ")) interface_Text.setText("Remplissez la partie \"Marque\" avant d'acheter");
				else if(a.fieldModele.getText().equals("Modèle")||a.fieldModele.getText().equals("")||a.fieldModele.getText().equals(" ")) interface_Text.setText("Remplissez la partie \"Modèle\" avant d'acheter");
				else fu.Acheter(List, interface_Text, a.fieldMarque.getText(), a.fieldModele.getText(), a.fieldCouleur.getSelectedItem().toString(), bouton_label, a.type.getSelectedItem().toString(), this);
				break;
				
			case "Tout":
				tp = 0;		//on change le type lors de l'enclenchement du bouton
				fu.reBouton(List,bouton_label,tp, this);
				break;
			
			case "Camion":
				tp = 1;
				fu.reBouton(List,bouton_label,tp, this);
				break;
			
			case "Moto":
				tp = 2;
				fu.reBouton(List,bouton_label,tp, this);
				break;
				
			case "Voiture":
				tp = 3;
				fu.reBouton(List,bouton_label,tp, this);
				break;
			default:
				if(fu.is_in(Couleur,e.getActionCommand())){
					tp = 0;
					bouton_label.setVisible(false);
					fu.Remove_Bouton(List, bouton_label);
					fu.Bouton_couleur(List, bouton_label, interface_Text, e.getActionCommand());
					bouton_label.setVisible(true);
						
				}else {
					for(int i=0; i<List.size();i++) {
						if(List.get(i).bouton!=null) {
							String[] bu = List.get(i).bouton.getActionCommand().split(" ");
							if(e.getActionCommand()==List.get(i).bouton.getActionCommand() && bu[bu.length-1].equals(List.get(i).vehicule.getMarque())) {
								interface_Text.setText(List.get(i).vehicule.decristoi());
							}
						}
					}
				}
				break;
		}
	}

}