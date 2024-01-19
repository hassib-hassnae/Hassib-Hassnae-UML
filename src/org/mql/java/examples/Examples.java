package org.mql.java.examples;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.mql.java.explorer.ExplorerPackages;
import org.mql.java.explorer.ExplorerProject;
import org.mql.java.explorer.ExplorerRelation;
import org.mql.java.explorer.Memory;

public class Examples {

	public Examples() {
		exp01();
	}
	  void exp01() {
		  Memory memory = new Memory("C:\\MQL\\Hassib Hassnae-UML");
		  String classpath = "C:\\MQL\\Hassib Hassnae-UML\\bin";
		  Set<String> packages = memory.getPackages();
		  for (String s : packages) {
			  System.out.println(s);
			  for (Class<?> c : memory.getClasses(s, classpath)) {
					System.out.println("\t>"+c.getSimpleName());
				}
		}
	  }
	
	
	public static void main(String[] args) {
		new Examples();

	}

}
