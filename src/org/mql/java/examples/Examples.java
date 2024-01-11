package org.mql.java.examples;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.mql.java.explorer.ExplorerPackages;
import org.mql.java.explorer.ExplorerProject;
import org.mql.java.explorer.ExplorerRelation;
import org.mql.java.explorer.Memoire;

public class Examples {

	public Examples() {
		exp03();
	}
	
	 private void exp01() {
	    Memoire memoire = new Memoire();
	    Set<String> packages = memoire.parserPath("C:\\MQL\\Hassib Hassnae-UML");

		   if (packages.isEmpty()) {
		       System.out.println("Aucun package trouvé.");
		   } else {
		       for (String pack : packages) {
		           System.out.println("-" + pack);
		           List<String> classes = memoire.explorerClass(pack);
					for (String cls : classes) {
						System.out.println("\t" + cls);
						//memoire.relation(cls);
					}
		       }
		   }
		
	}
	
	/*private void exp02() {
		 ExplorerPackage explorerPack = new ExplorerPackage();
		 ExplorerClass explorerCls = new ExplorerClass();
		 Set<String> packages = explorerPack.parserPath("C:\\MQL\\Hassib Hassnae-UML");
		 if (packages.isEmpty()) {
		       System.out.println("Aucun package trouvé.");
		   }  
		 else {
		       for (String pack : packages) {
		    	   System.out.println("******************************");
		           System.out.println("-" + pack);
		           List<Class<?>> classes = explorerCls.scan(pack,"C:\\MQL\\Hassib Hassnae-UML\\bin");
					for (Class<?> cls : classes) {
						System.out.println("\t" + cls.getSimpleName());
					}
		       }
		   }
	}*/
	
	void exp03() {
		
		 //ExplorerClass explorerCls = new ExplorerClass();
        //List<Class<?>> classes = explorerCls.scan("org.mql.java.explorer","C:\\MQL\\Hassib Hassnae-UML\\bin");
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
    	//pa.scan(packageNames,classpath);
	}


	public static void main(String[] args) {
		new Examples();

	}

}
