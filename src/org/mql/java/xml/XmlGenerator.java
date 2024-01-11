package org.mql.java.xml;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.mql.java.explorer.ExplorerPackages;
import org.mql.java.explorer.ExplorerProject;
import org.mql.java.explorer.ExplrerClasses;

public class XmlGenerator {
	static Element racine = new Element("projet");
	static org.jdom2.Document document  = new Document(racine);
	static String path="C:\\MQL\\Hassib Hassnae-UML";
	static String classpath = "C:\\MQL\\Hassib Hassnae-UML\\bin";
	
	static void affiche(){
		   try
		   {
		      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		      sortie.output(document, System.out);
		   }
		   catch (java.io.IOException e){}
	}
	

	static void enregistre(String fichier){
		   try
		   {
		      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		      sortie.output(document, new FileOutputStream(fichier));
		   }
		   catch (java.io.IOException e){}
	}
	public static void main(String[] args) {
		ExplorerProject packages = new ExplorerProject();
		for (String p : packages.scanProject(path)) {
			Element pack = new Element("package");
			Attribute packageNames = new Attribute("name", p);
			//pack.setText(p);
			pack.setAttribute(packageNames);
			//*************************************************************************************************************************
			ExplorerPackages classes = new ExplorerPackages();
			for (Class<?> c : classes.scan(p,classpath)) {
				Element cls = new Element("classe");
				Attribute classeNames = new Attribute("name",c.getSimpleName());
				cls.setAttribute(classeNames);
				pack.addContent(cls);
				// Utilisation de la méthode getFields dans votre boucle
				ExplrerClasses properties = new ExplrerClasses(); // Je suppose qu'ExplorerClasses est la classe contenant getFields

				
				String[][] fieldsData = properties.getFields(c);
				if(fieldsData != null) {
					
					Element fields = new Element("fields");

				for (int i = 0; i < fieldsData[0].length; i++) {
				    Element field = new Element("field");

				    Attribute modifier = new Attribute("modifier", fieldsData[0][i]);
				    field.setAttribute(modifier);

				    Attribute type = new Attribute("type", fieldsData[1][i]);
				    field.setAttribute(type);

				    Attribute name = new Attribute("name", fieldsData[2][i]);
				    field.setAttribute(name);

				    fields.addContent(field);
				}
				

				cls.addContent(fields);
				}

			}
			//***************************************************************************************************************************
			racine.addContent(pack);
		}
		
		Attribute classe = new Attribute("name",path);
	      racine.setAttribute(classe);
	      
	      
	      
	      enregistre("resources/projet.xml");
	}

}
