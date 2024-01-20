package org.mql.java.xml;

import java.util.HashSet;
import java.util.Set;

import org.mql.java.xml.XmlNode;
import org.mql.java.explorer.ExplorerProject;

public class DomParser {
	
	public DomParser() {
		parse("resources/projet.xml");
		
	}
	
	public void parse(String source) {
	    XmlNode root = new XmlNode(source);
	    String projectName = root.getAttribute("name");
	    System.out.println("Project : " + projectName);

	    for (int i = 0; i < root.getChildren().length; i++) {
	        System.out.println("\t Package: " + root.getChildren()[i].getAttribute("name"));

	        for (int j = 0; j < root.getChildren()[i].getChildren().length; j++) {
	            System.out.println("\t\t Classe: " + root.getChildren()[i].getChildren()[j].getAttribute("name"));

	            // Afficher les méthodes
	            XmlNode methodsNode = root.getChildren()[i].getChildren()[j].getChild("methods");
	            if (methodsNode != null) {
	                XmlNode[] methods = methodsNode.getChildren();
	                    for (int k = 0; k < methods.length; k++) {
	                        XmlNode methodNode = methods[k];
	                        if (methodNode != null) {
	                            System.out.println("\t\t\t Method : " + methodNode.getAttribute("name"));
	                        }
	                    }
	               
	            } else {
	                System.out.println("\t\t\t No methods within this class");
	            }

	            // Afficher les champs
	            XmlNode fieldsNode = root.getChildren()[i].getChildren()[j].getChild("fields");
	            if (fieldsNode != null) {
	                XmlNode[] fields = fieldsNode.getChildren();
	                    for (int k = 0; k < fields.length; k++) {
	                        XmlNode fieldNode = fields[k];
	                        if (fieldNode != null) {
	                            System.out.println("\t\t\t field : " + fieldNode.getAttribute("name"));
	                        }
	                    }
	               
	            } else {
	                System.out.println("\t\t\t No fields within this class");
	            }

	        }
	    }

	}


	
	public static void main(String[] args) {
		new DomParser();
		
	}

}
