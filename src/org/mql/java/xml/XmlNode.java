package org.mql.java.xml;


import java.util.LinkedList;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class XmlNode {
	private Node node; 
	private XmlNode children[];
	
	public XmlNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
				try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(source);
			Node node= document.getFirstChild();
			while(node.getNodeType() != node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
			setNode(node);
		} catch (Exception e) {
			System.out.println("Erreur: "+ e.getMessage());
		}
	}

	public XmlNode(Node node) {
		super();
		this.setNode(node);
	}


	public void setNode(Node node) {
		this.node = node;
		extractChildren();
	}
	private void extractChildren() {
		NodeList list =  node.getChildNodes();
		LinkedList<XmlNode> nodes = new LinkedList<XmlNode>();
		for (int i = 0; i < list.getLength(); i++) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodes.add(new XmlNode(list.item(i)));				
			}
			
		}
		children = new XmlNode[nodes.size()];
		nodes.toArray(children);  
	}
	
	public Node getNode() {
		return node;
	}
	public XmlNode[] getChildren() { //retourn un tableau des noudes enfants du node courant
		return children;
	}
	public String getName() {//Delegate Methode
		return node.getNodeName();
	}
	
	public boolean isNamed(String name) {
		return node.getNodeName().equals(name);
	}
	
	public XmlNode getChild(String name) { //Trouver un node a partire de son nom 
		for(XmlNode child : children) {
			if(child.isNamed(name)) {
				return child;
			}
			
		}
		return null;
	}
	//conçue pour extraire la valeur d'un nœud texte
	public String getValue() {
		Node child =  node.getFirstChild();
		if(child != null && child.getNodeType() == node.TEXT_NODE) {
			return child.getNodeValue();
		}
		return "";
	}
	
	public String getAttribute(String name) {
		//obtenire la liste des attributs 
		Node att =  node.getAttributes().getNamedItem(name);//trouver un att a partire de son nom 
		if(att == null) return "";//si l'atribut n'existe pas
		return att.getNodeValue();
		
	}
	//transforme la valeur de l'attribut en int si nécessaire
	public int getIntAttribute(String name) {
		String s = getAttribute(name);
		int value = 0;//valeur par defaut 
		try {
			value=Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Erreur : "+ e.getMessage());
		}
		return value;
	}
	

}
