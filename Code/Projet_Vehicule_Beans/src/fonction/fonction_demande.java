package fonction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Vehicule.Camion;
import Vehicule.Moto;
import Vehicule.Voiture;
import frame.boutton;

public class fonction_demande implements ActionListener{
	// ---------------------------- Fonction Demandées ----------------------------
	/*
		 Cette méhtode prend aucun argument et ne renvoie rien.
		 Son but est d'afficher dans interface_Text la liste de tous les véhicules
		 S'il n'a aucune voiture, il renvoie : "Vous ne possédez pas de véhicule."
	 */
	public void List_Nom(ArrayList<boutton> List, JLabel bouton_label, JTextArea interface_Text, JFrame ad) {	
		boolean b = true;
		interface_Text.setText("Liste des véhicules :\n");
		for(int i=0; i<List.size();i++) {
			b = false;
			interface_Text.append(List.get(i).vehicule.decristoi()+"\n");
		}
		if(b) interface_Text.append("Vous ne possédez pas de véhicule.");
	}

	/*
	 Cette méhtode prend en argument une chaîne de caractère mais ne renvoie rien.
	 Son but est d'afficher dans interface_Text tous les véhicules ayant pour couleur ce qui est écrit dans string color.
	 S'il n'y a aucune voiture de cette couleur, alors il renvoie "Vous ne possédez pas de véhicule "+color+"."
	 */
	
	public void Couleur_list(ArrayList<boutton> List, JTextArea interface_Text, String color) {
		boolean b = true;
		interface_Text.setText("Liste des voiture de couleur "+color+" :\n");
		for(int i=0; i<List.size();i++) {
			if(List.get(i).vehicule.getCouleur() == color ) {
				b = false;
				interface_Text.append("   - "+List.get(i).vehicule.getMarque()+" "+List.get(i).vehicule.getModele()+" "+List.get(i).vehicule.getCouleur()+"\n");
			}
		}if(b) interface_Text.append("Vous ne possédez pas de véhicule "+color+".");
	}

	/*
		 Cette méhtode prend deux chaîne de caractère mais ne renvoie rien.
		 Son but est d'afficher dans interface_Text tous les véhicules correspondant aux caractéristique demandés.
		 S'il aucune des voitures ne correspond, alors il renvoie "Aucun résultat"
	*/

	public void Recherche(ArrayList<boutton> List, JTextArea interface_Text, String marque, String modele) {
		interface_Text.setText("Résultats de la recherche pour \""+marque+" "+modele+"\" :\n");
		boolean res = true;
		for(int i=0; i<List.size();i++) {
			if(List.get(i).vehicule.getMarque().equals(marque) && List.get(i).vehicule.getModele().equals(modele)) {
				interface_Text.append("   - "+List.get(i).vehicule.getMarque()+" "+List.get(i).vehicule.getModele()+" "+List.get(i).vehicule.getCouleur()+"\n");
				res= false;
			}
		}if(res)interface_Text.append("Aucun résultat\n");
	}

	/*
		 Cette méhtode prend en argument trois chaîne de caractère, un JLabel et un entier correspondant au2 mais ne renvoie rien.
		 Son but est d'enlever de List le véhicule correspondant aux caractéristique demandés.
		 S'il aucun des véhicules ne correspond, alors il renvoie "Aucun résultat"
	*/
	
	public void Vendre(ArrayList<boutton> List, JLabel bouton_label, JTextArea interface_Text, String marque, String modele, String couleur, int tp,JFrame ad) {
		boolean res = true;
		for(int i=0; i<List.size();i++) {
			if(List.get(i).vehicule.getMarque().equals(marque) && List.get(i).vehicule.getModele().equals(modele) && List.get(i).vehicule.getCouleur().equals(couleur)) {
				interface_Text.setText("Vente de ce véhicule :\n");
				interface_Text.append(List.get(i).vehicule.decristoi());
				res= !res;
				bouton_label.remove(List.get(i).bouton);
				List.remove(i);
				i=List.size(); //pour arrêter ici
			}
		}
		if(res) {
			interface_Text.setText("Vous ne possédez pas ce véhicule\n");
		}else reBouton(List, bouton_label, tp, ad);	
	}

	/*
		 Cette méhtode prend en argument trois chaîne de caractère, un JLabel et un entier correspondant au2 mais ne renvoie rien.
		 Son but est d'ajouter un véhicule à List correspondant aux caractéristique demandés.
	*/
	
	public void Acheter(ArrayList<boutton> List, JTextArea interface_Text, String marque, String modele, String couleur, JLabel bouton_label, String tp, JFrame ad) {
		int type = 0;
		boutton b = new boutton();
		if(tp.equals("Camion")) {
			type = 1;
			Camion v = new Camion(marque,modele,couleur);
			v.addPropertyChangeListener(new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent event) {
			          System.out.println("propertyChange : valeur = "+ event.getNewValue());
			        }
			      } );
			b.vehicule = v;
			b.bouton = null;
			List.add(b);
		}
		if(tp.equals("Moto")) {
			type = 2;
			Moto v = new Moto(marque,modele,couleur);
			v.addPropertyChangeListener(new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent event) {
			          System.out.println("propertyChange : valeur = "+ event.getNewValue());
			        }
			      } );
			b.vehicule = v;
			b.bouton = null;
			List.add(b);
		}
		if(tp.equals("Voiture")) {
			type = 3;
			Voiture v = new Voiture(marque,modele,couleur);
			v.addPropertyChangeListener(new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent event) {
			          System.out.println("propertyChange : valeur = "+ event.getNewValue());
			        }
			      } );
			b.vehicule = v;
			b.bouton = null;
			interface_Text.setText("Achat de ce véhicule :\n");
			interface_Text.append(List.get(List.size()-1).vehicule.decristoi());
			List.add(b);
		}
		reBouton(List, bouton_label, type, ad);
	}
	/*
	 Cette méhtode prend en argument trois chaîne de caractère, un JLabel et un entier qui correspondant au type mais ne renvoie rien.
	 Son but est d'afficher dans bouton_label tout les boutons correspondant aux critères.
	*/
	
	public void Bouton(ArrayList<boutton> List, JLabel bouton_label, int tp, JFrame ad) {
		int l = 0,c = 0;
		String type = null;
		int num = 0;
		if(tp==0) type = "Véhicule";
		if(tp==1) type = "Camion";
		if(tp==2) type = "Moto";
		if(tp==3) type = "Voiture";
		for(int i=0; i<List.size();i++) {
			if(tp == 0 || List.get(i).vehicule.getType().equals(type)) {
				num++;
				JButton button = new JButton(type+" "+(num)+" : "+List.get(i).vehicule.getMarque());
				button.setBounds(5+180*c,5+55*l,175,50);
				button.addActionListener((ActionListener) ad);
				List.get(i).bouton = button;
				bouton_label.add(List.get(i).bouton);
				c++;
				if(c==4) {
					c=0; l++;
				}
			}
		}
	}
	
	/*
		 Cette méhtode prend aucun argument et ne renvoie rien.
		 Son but est de retirer dans bouton_label tout les boutons.
	*/
	
	public void Remove_Bouton(ArrayList<boutton> List, JLabel bouton_label) {
		for(int i=0; i<List.size();i++) {
			if(List.get(i).bouton!=null) {
				bouton_label.remove(List.get(i).bouton);
				List.get(i).bouton=null;				
			}
		}
	}

	/*
		 Cette méhtode prend aucun argument mais renvoie aucune variables.
		 Son but est d'enchainer supression et création de bouton
	*/
	
	public void reBouton(ArrayList<boutton> List, JLabel bouton_label, int tp, JFrame ad) {
		bouton_label.setVisible(false);
		Remove_Bouton(List, bouton_label);
		Bouton(List, bouton_label, tp, ad);
		bouton_label.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {;}
}
