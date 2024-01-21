package org.mql.java.ui;


import java.awt.Color;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelClass extends JPanel {

    public PanelClass(String className ,  List<String> fields , List<String> methods ) {
    	String f = "";
    	String m = "";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel j1 = new JLabel(className);
        for (String field : fields) {
			f+=field + "\n";
		}
        for (String method : methods) {
			m+=method + "\n";
		}
        
        JTextArea area1 = new JTextArea(f.toString());
        JTextArea area2 = new JTextArea(m.toString());
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        add(j1);
        add(separator);
        add(area1);
        add(separator);
        add(area2);
        //add(j2);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Définir une taille spécifique (width, height)
        setPreferredSize(new Dimension(200, 150)); // Ajuster la taille selon vos besoins
    }
}
