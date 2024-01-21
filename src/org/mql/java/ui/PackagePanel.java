package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PackagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	private JPanel container; 
	
	
	public PackagePanel() {
		setLayout( new FlowLayout(FlowLayout.LEFT));
		container = new JPanel();
		add(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		add(separator);
	}
	public void add(String className,List<String> fields,List<String> methods) {
		container.add(new PanelClass(className,fields ,methods ));
		container.add(Box.createRigidArea(new Dimension(0, 20)));
			
	}

}
