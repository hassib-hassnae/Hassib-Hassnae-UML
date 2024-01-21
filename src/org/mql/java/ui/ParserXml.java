package org.mql.java.ui;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXml extends DefaultHandler{
	
	private String tag = ""; //current tag :)
	private String clsName = "";
	private int insideClasses=0;
	private Panel panel = new Panel();
	private PackagePanel packagePanel;
	private int  insidePackage  =0;
	private List<String> fields = new ArrayList<>();
	private List<String> methods = new ArrayList<>();
	private int nbrFields = 0;
	
	public ParserXml() {
		
	}
	
	public Panel parse(String source) {
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		try {
			SAXParser parser =  factory.newSAXParser();
			parser.parse(source,this);//la classe en cours (this) agira comme un gestionnaire d'événements XML.
			
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return panel;
		
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("package".equals(qName)) {
			insidePackage = 1;
			packagePanel = new PackagePanel();
		}
		else if(("classe".equals(qName) || "annotation".equals(qName) || "interface".equals(qName)) & insidePackage == 1) {
			insideClasses = 1;
			clsName = attributes.getValue("name");
			
		}
		else if("field".equals(qName) & insideClasses == 1) {
			//nbrFields ++;
			fields.add(attributes.getValue("name"));
			//System.out.println(nbrFields);
		}
		else if("method".equals(qName)& insideClasses == 1) {
			methods.add(attributes.getValue("name")+ "()");
		}
		
     
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	 if("classe".equals(qName) & insidePackage == 1) {
    		insideClasses = 0;
    		//PanelClass classes = new PanelClass(qName, nbrFields, fields);
    		//System.out.println(clsName + "---->"+ nbrFields);
    		packagePanel.add(clsName,fields,methods);
    		clsName = "";
    		fields.removeAll(fields);
    		methods.removeAll(methods);
    		
    	}
    	else if("package".equals(qName)) {
    		panel.add(packagePanel);
    		insidePackage = 0;	
    	}
    	
       
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
    }


   public static void main(String[] args) {
        ParserXml sax = new ParserXml();
        sax.parse("resources/projet.xml");
    }
}
