package Vehicule;

import java.io.Serializable;
import java.beans.*;

public class Vehicule implements Serializable {
	
	protected String Marque;
	protected String Modele;
	protected String Couleur;
	protected String Type;
	
	PropertyChangeSupport changeSupport;
	
	public Vehicule (String Marque,String Modele,String Couleur, String Type) {
		this.Marque=Marque;
		this.Modele=Modele;
		this.Couleur=Couleur;
		this.Type=Type;
		
		changeSupport = new PropertyChangeSupport(this);
		
	}
	
	public synchronized void setMarque(String marque) {
		String oldMarque = Marque;
		Marque = marque;
	    
	    changeSupport.firePropertyChange("marque",oldMarque,Marque);
	  }
	
	public synchronized String getMarque() {
	    return Marque;
	  }
	
	public synchronized void setModele(String modele) {
		String oldModele = Modele;
		Modele = modele;
	    
	    changeSupport.firePropertyChange("modele",oldModele,Modele);
	  }
	
	public synchronized String getModele() {
	    return Modele;
	  }

	public synchronized void setCouleur(String couleur) {
		String oldCouleur = Couleur;
		Couleur = couleur;
	    
	    changeSupport.firePropertyChange("couleur",oldCouleur,Couleur);
	  }
	
	public synchronized String getCouleur() {
	    return Couleur;
	  }
	
	public synchronized void setType(String type) {
		String oldType = Type;
		Type = type;
	    
	    changeSupport.firePropertyChange("type",oldType,Type);
	  }
	
	public synchronized String getType() {
	    return Type;
	  }
	
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
	    changeSupport.addPropertyChangeListener(listener);
	  }
	  
	  public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
	    changeSupport.removePropertyChangeListener(listener);
	  }	
	
	public String decristoi() {
		String description;
		description = "Caractéristique du Véhicule :\n";
		description = description+"   - Marque : "+Marque+"\n";
		description = description+"   - Modèle : "+Modele+"\n";
		description = description+"   - Couleur : "+Couleur+"\n";
		return description;
	}

}
