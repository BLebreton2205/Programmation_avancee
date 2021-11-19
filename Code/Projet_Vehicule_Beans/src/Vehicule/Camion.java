package Vehicule;

public class Camion  extends Vehicule {
	private static final String Type = "Camion";
	
	public Camion(String Marque, String Modele, String Couleur) {
		super(Marque, Modele, Couleur,Type);
	}
	
	public String decristoi() {
		String description = super.decristoi();
		description = description+"   - Type : "+Type+"\n";
		return description;
		
	}

}
