package org.mql.java.ui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	private JPanel container; 
	
	
	public Panel() {
		setLayout( new FlowLayout(FlowLayout.LEFT));
		container = new JPanel();
		add(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	}
	

}