package org.mql.java.explorer;
import java.lang.reflect.Field;

import org.mql.java.annotation.isObject;

public class ExplorerRelation {

	public ExplorerRelation() {
		
	}
	
	public void relation(String qname) {
    	String parent = "";
		try {
			Class<?> c = Class.forName(qname);
			Field field[] = c.getDeclaredFields();
			 for (Field f : field) {
				 isObject ob = f.getDeclaredAnnotation(isObject.class);
				if(ob!=null) {
	                System.out.println("\t"+ob.type()+"=============>"+c.getSimpleName() + " has a/an " + f.getType().getSimpleName() + " field: " + f.getName());
				}
			}
			 parent = c.getSuperclass().getSimpleName();
			 if(parent != null) {
				 System.out.println("\t Héritage=============>"+parent + "  is a parent of : " + c.getSimpleName());
			 }
		} catch (Exception e) {
			System.out.println("Error : "+ e.getMessage());
		}
		 
    }

}