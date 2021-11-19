package Vehicule;

public class Moto extends Vehicule {
	private static final String Type = "Moto";
	
	public Moto(String Marque, String Modele, String Couleur) {
		super(Marque, Modele, Couleur,Type);
	}
	
	public String decristoi() {
		String description = super.decristoi();
		description = description+"   - Type : "+Type+"\n";
		return description;
		
	}

}
