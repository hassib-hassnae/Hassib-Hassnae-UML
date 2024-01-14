package org.mql.java.explorer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mql.java.annotation.isObject;
import org.mql.java.models.Etudiant;

	public class Memoire {
	
		public Memoire() {
			ExplorerProject p = new ExplorerProject();
			ExplorerPackages pa = new ExplorerPackages();
	    	String classpath = "C:\\MQL\\Hassib Hassnae-UML\\bin";
	    	Set<String> packageNames = new HashSet<>();
	    	packageNames = p.scanProject("C:\\MQL\\Hassib Hassnae-UML");
	    	for (String s : packageNames) {
				System.out.println(s);
				for (Class<?> c : pa.scan(s, classpath)) {
					System.out.println("\t>"+c.getSimpleName());
				}
				
			}
			
		}
		
}
