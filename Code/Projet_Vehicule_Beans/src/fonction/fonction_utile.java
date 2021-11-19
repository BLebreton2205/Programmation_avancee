package fonction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import frame.boutton;

public class fonction_utile extends fonction_demande  implements ActionListener {
	/*
		 Cette m�htode prend en argument trois cha�ne de caract�re, un JLabel et un entier qui correspondant au type mais ne renvoie rien.
		 Son but est d'afficher dans bouton_label tout les boutons correspondant aux crit�res.
	*/
	
	public void Bouton_couleur(ArrayList<boutton> List, JLabel bouton_label, JTextArea interface_Text, String color) {
		int l = 0,c = 0;
		int num = 0;
		for(int i=0; i<List.size();i++) {
			if(List.get(i).vehicule.getCouleur() == color ) {
				num++;
				JButton button = new JButton("V�hicule "+(num)+" : "+List.get(i).vehicule.getMarque());
				button.setBounds(5+180*c,5+55*l,175,50);
				button.addActionListener(this);
				List.get(i).bouton = button;
				bouton_label.add(List.get(i).bouton);
				c++;
				if(c==4) {
					c=0; l++;
				}
			}
		}
		Couleur_list(List, interface_Text,color);
	}

	/*
		 Cette m�htode prend aucun argument mais renvoie un tableau de String.
		 Son but est de cr�er une liste avec toutes les marques.
	*/
	
	public String[] List_marque(ArrayList<boutton> List) {
		String marque[] = new String[100];
		for(int i=0; i<List.size();i++) {
			if(!is_in(marque,List.get(i).vehicule.getMarque())) {
				marque[i] = List.get(i).vehicule.getMarque();
			}
		}
		return marque;		
	}

	/*
		 Cette m�htode prend aucun argument mais renvoie un tableau de String.
		 Son but est de cr�er une liste avec toutes les modeles.
	*/
	
	public String[] List_modele(ArrayList<boutton> List) {
		String modele[] = new String[100];
		for(int i=0; i<List.size();i++) {
			if(!is_in(modele,List.get(i).vehicule.getModele())) {
				modele[i] = List.get(i).vehicule.getModele();
			}
		}
		return modele;		
	}

	/*
		 Cette m�htode prend en argument un tableau de String et une chaine de caract�re mais renvoie un bool�en.
		 Son but est de cr�er une liste avec toutes les marques.
	*/
	
	public boolean is_in(String[] tab, String is) {
		for(int i=0; i<tab.length;i++) {		
			if(is.equals(tab[i])) return true;
		}
		return false;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
