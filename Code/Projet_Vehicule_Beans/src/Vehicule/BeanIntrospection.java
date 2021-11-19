package Vehicule;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import frame.boutton;

public class BeanIntrospection {

	  static String nomBean; 

	  public static void main(String args[]) throws Exception {
	    nomBean = "Vehicule.Vehicule";
	    new BeanIntrospection();
	  }
	  
	  public BeanIntrospection() throws Exception {
	    Class monBeanClasse = Class.forName(nomBean);
	    MethodDescriptor[] methodDescriptor;
	    
	    BeanInfo bi = Introspector.getBeanInfo(monBeanClasse, monBeanClasse.getSuperclass());
	    BeanDescriptor unBeanDescriptor = bi.getBeanDescriptor();
	    System.out.println("Nom du bean    : " + unBeanDescriptor.getName());
	    System.out.println("Classe du bean : " + unBeanDescriptor.getBeanClass());
	    System.out.println("");
	  
	    PropertyDescriptor[] propertyDescriptor = bi.getPropertyDescriptors();
	    for (int i=0; i<propertyDescriptor.length; i++) {
	      System.out.println(" Nom propriete    : " + 
	                          propertyDescriptor[i].getName());
	      System.out.println(" Type propriete   : " 
	                + propertyDescriptor[i].getPropertyType());
	      System.out.println(" Getter propriete : " 
	                  + propertyDescriptor[i].getReadMethod());
	      System.out.println(" Setter propriete : " 
	                 + propertyDescriptor[i].getWriteMethod());
	    }
	    System.out.println("");
	    methodDescriptor = bi.getMethodDescriptors();
	    for (int i=0; i < methodDescriptor.length; i++) {
	      System.out.println(" Methode : "+methodDescriptor[i].getName());
	    }
	    System.out.println("");
	    EventSetDescriptor[] unEventSetDescriptor = bi.getEventSetDescriptors();
	    for (int i = 0; i < unEventSetDescriptor.length; i++) {
	      System.out.println(" Nom evt             : " 
	                        + unEventSetDescriptor[i].getName());
	      System.out.println(" Methode add evt     : " +
	             unEventSetDescriptor[i].getAddListenerMethod());
	      System.out.println(" Methode remove evt  : " +
	          unEventSetDescriptor[i].getRemoveListenerMethod());
	      methodDescriptor = unEventSetDescriptor[i].getListenerMethodDescriptors();
	      for (int j = 0; j < methodDescriptor.length; j++) {
	          System.out.println(" Event Type: " + methodDescriptor[j].getName());
	      }
	    }
	    System.out.println("");
	  }
	}