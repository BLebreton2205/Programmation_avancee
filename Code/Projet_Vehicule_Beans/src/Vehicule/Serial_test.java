package Vehicule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serial_test {

	public static void main(String args[]) throws Exception {
		ArrayList<Vehicule> List_V = new ArrayList<Vehicule>();
	    List_V.add(new Voiture("Citroen","C4","Blanc"));
	    List_V.add(new Voiture("Lotus","Elise","Blanc"));
	    List_V.add(new Voiture("Audi","R7","Rouge"));
	    List_V.add(new Moto("Yamaha","2A8", "Noir"));
	    List_V.add(new Moto("Honda","CBR650R", "Bleu"));
	    List_V.add(new Camion("Scania", "V8", "Rouge"));
	    List_V.add(new Camion("Volkswagen", "Constellation", "Jaune"));
	    
	    
	    ObjectOutputStream oos = null;
	    ObjectInputStream ois = null;
	    try {
	        final FileOutputStream fichierOut = new FileOutputStream("Serial_out.txt");
	        oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(List_V);
	        oos.flush();
	        final FileInputStream fichierIn = new FileInputStream("Serial_out.txt");
	        ois = new ObjectInputStream(fichierIn);
	        List_V = (ArrayList<Vehicule>) ois.readObject();
	        
	        for(int i = 0; i<List_V.size(); i++) {
	        	System.out.println(List_V.get(i).decristoi());
			}
	        
	    } catch (final java.io.IOException e) {
	            e.printStackTrace();
	    } catch (final ClassNotFoundException e) {
	          e.printStackTrace();
	    } finally {
    		try {
    			if (ois != null) {
	              ois.close();
	            }
	            if (oos != null) {
	              oos.close();
	            }
    		} catch (final IOException ex) {
	            ex.printStackTrace();
    		}
	    }
	}	
}
