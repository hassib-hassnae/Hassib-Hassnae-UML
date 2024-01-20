package org.mql.java.sax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxProjectParser extends DefaultHandler{
	
	private String tag = ""; //current tag :)
	private String att = "";
	private int insideClasses=0;
	private String currentRelation = "";
	
	public SaxProjectParser() {
		
	}
	
	public void parse(String source) {
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		try {
			SAXParser parser =  factory.newSAXParser();
			parser.parse(source,this);//la classe en cours (this) agira comme un gestionnaire d'événements XML.
			
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tag = qName;
        att = attributes.getValue("classe");

        if ("projet".equals(qName)) {
        	
            System.out.println("Project: " + attributes.getValue("name"));
        } 
        else if ("package".equals(qName)) {
        	
            System.out.println("\tPackage: " + attributes.getValue("name"));
        }
        else if ("classe".equals(qName) || "annotation".equals(qName) || "interface".equals(qName)) {
        	
            insideClasses = 1;
            System.out.println("\t\tClasse: " + attributes.getValue("name"));
        }
        else if ("field".equals(qName) & insideClasses == 1) {
        	
            System.out.println("\t\t\tfield: " + attributes.getValue("name"));
        }
        else if ("method".equals(qName) & insideClasses == 1) {
        	
            System.out.println("\t\t\tmethode: " + attributes.getValue("name"));
        } 
        else if ("relation".equals(qName) & insideClasses == 1) {
            currentRelation = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("classe".equals(qName) || "annotation".equals(qName) || "interface".equals(qName)) {
            insideClasses = 0;
        } else if ("relation".equals(qName) & insideClasses == 1) {
            System.out.println("\t\t\t" + currentRelation + " relation with classe: " + att);
            currentRelation = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String characters = new String(ch, start, length);
        if ("relation".equals(tag) & insideClasses == 1) {
            currentRelation += characters;
        }
    }


    public static void main(String[] args) {
        SaxProjectParser sax = new SaxProjectParser();
        sax.parse("resources/projet.xml");
    }
}