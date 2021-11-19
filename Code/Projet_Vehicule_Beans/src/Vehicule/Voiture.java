package Vehicule;

public class Voiture extends Vehicule {
	private static final String Type = "Voiture";

	public Voiture(String Marque, String Modele, String Couleur) {
		super(Marque, Modele, Couleur, Type);
	}
	
	public String decristoi() {
		String description = super.decristoi();
		description = description+"   - Type : "+Type+"\n";
		return description;
		
	}

}
