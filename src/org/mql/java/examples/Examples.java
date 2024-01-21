package org.mql.java.examples;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.explorer.ExplorerPackages;
import org.mql.java.explorer.ExplorerProject;
import org.mql.java.explorer.ExplorerRelation;
import org.mql.java.explorer.Memory;
import org.mql.java.ui.Panel;
import org.mql.java.ui.ParserXml;

public class Examples {

	public Examples() {
		exp02();
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
	  void exp02() {
		    ParserXml parser = new ParserXml();

		    JFrame frame = new JFrame("ClassParserFrame");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    JScrollPane scrollPane = new JScrollPane(parser.parse("resources/projet.xml"));

		    frame.setContentPane(scrollPane);
		    frame.pack();
		    frame.setVisible(true);
		}
	
	
	public static void main(String[] args) {
		new Examples();

	}

}
