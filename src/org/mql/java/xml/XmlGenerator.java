package org.mql.java.xml;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.mql.java.explorer.ExplorerPackages;
import org.mql.java.explorer.ExplorerProject;
import org.mql.java.explorer.ExplorerRelation;
import org.mql.java.explorer.ExplrerClasses;
import org.w3c.dom.Attr;

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
		ExplorerRelation explorerRelation = new ExplorerRelation();
		for (String p : packages.scanProject(path)) {
			Element pack = new Element("package");
			Attribute packageNames = new Attribute("name", p);
			//pack.setText(p);
			pack.setAttribute(packageNames);
			//*************************************************************************************************************************
			ExplorerPackages classes = new ExplorerPackages();
			for (Class<?> c : classes.scan(p,classpath)) {
				String tagName = "classe";
				//Nom de la balise selon le type de la classe 
				if(c.isInterface()) {
					tagName = "interface";
				}
				if(c.isAnnotation()) {
					tagName = "annotation";
				}
				
				Element cls = new Element(tagName);
				Attribute classeNames = new Attribute("name",c.getSimpleName());
				cls.setAttribute(classeNames);
				pack.addContent(cls);
				ExplrerClasses properties = new ExplrerClasses(); 

				
				String[][] fieldsData = properties.getFields(c);
				String[][] methodsData = properties.getMethods(c);
				Element relations = new Element("relations");
				//System.out.println(c.getSimpleName()+"----->"+fieldsData[0].length);
				if(fieldsData[0].length > 0) {
					
					Element fields = new Element("fields");

				for (int i = 0; i < fieldsData[0].length; i++) {
				    Element field = new Element("field");

				    Attribute modifier = new Attribute("modifier", fieldsData[0][i]);
				    field.setAttribute(modifier);

				    Attribute type = new Attribute("type", fieldsData[1][i]);
				    field.setAttribute(type);

				    Attribute name = new Attribute("name", fieldsData[2][i]);
				    field.setAttribute(name);
				    if(fieldsData[3][i]!=null) {
				    	Element relation = new Element("relation");
				    	Attribute classe = new Attribute("classe",fieldsData[1][i]);
				    	relation.setAttribute(classe);
				    	relation.setText(fieldsData[3][i]);	
				    	relations.addContent(relation);
				    }

				    fields.addContent(field);
				}
				
				

				cls.addContent(fields);
				
				}
				
                if(methodsData[0].length > 0) {
					
					Element methods = new Element("methods");

				for (int i = 0; i < methodsData[0].length; i++) {
				    Element method = new Element("method");

				    Attribute modifier = new Attribute("modifier", methodsData[0][i]);
				    method.setAttribute(modifier);

				    Attribute type = new Attribute("type", methodsData[1][i]);
				    method.setAttribute(type);

				    Attribute name = new Attribute("name", methodsData[2][i]);
				    method.setAttribute(name);

				    methods.addContent(method);
				}
				

				cls.addContent(methods);
				}
                //Ajouter la classe mére
                if(explorerRelation.getSuperClasse(c) != null) {
                	Element relation = new Element("relation");
                	Attribute classe = new Attribute("classe",explorerRelation.getSuperClasse(c));
                	relation.setAttribute(classe);
                	relation.setText("extension");
                	relations.addContent(relation);
                }
                if(explorerRelation.getInterfaces(c) != null) {
                	for (String inteface : explorerRelation.getInterfaces(c)) {
                		Element relation = new Element("relation");
                    	Attribute classe = new Attribute("classe",inteface);
                    	relation.setAttribute(classe);
                    	relation.setText("implementation");
                    	relations.addContent(relation);
					}
                }
                
                cls.addContent(relations);

			}
			//***************************************************************************************************************************
			
			racine.addContent(pack);
		}
		
		Attribute classe = new Attribute("name",path);
	      racine.setAttribute(classe);
	      
	      
	      
	      enregistre("resources/projet.xml");
	}

}
