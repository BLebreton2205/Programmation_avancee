package test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TestMonBean {
	public static void main(String[] args) {
		new TestMonBean();
	}
				  
	public TestMonBean() {
		MonBean monBean = new MonBean();
					    
		monBean.addPropertyChangeListener( new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("propertyChange : valeur = "+ event.getNewValue());
			}
		} );
		                           
		System.out.println("valeur = " + monBean.getValeur());
		monBean.setValeur(10);
		System.out.println("valeur = " + monBean.getValeur());
		                           
	}
}