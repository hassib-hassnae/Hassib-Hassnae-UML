package org.mql.java.explorer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.models.Etudiant;

public class ExplrerClasses {
	private int nbrMethods=0;
	//private int nbrfields=0;
	
	public ExplrerClasses() {
		// TODO Auto-generated constructor stub
	}
	
	/*public List<String> fields(Class<?> c) {
		List<String> data =  new ArrayList<>();
		Field field[] = c.getDeclaredFields();
        for (Field f : field) {
    		int modifiers = f.getModifiers();
        	data.add(Modifier.toString(modifiers));
    		data.add(f.getType().getSimpleName());
    		data.add(f.getName());
			}
	return data;
	}*/
	public String[][] getFields(Class<?> c) {
	    Field[] fields = c.getDeclaredFields();
	    int nbrfields = fields.length;
	    String data[][] = new String[4][nbrfields];
	    for (int i = 0; i < nbrfields; i++) {
	        Field f = fields[i];
	        int modifiers = f.getModifiers();
	        data[0][i] = Modifier.toString(modifiers);
	        data[1][i] = f.getType().getSimpleName();
	        data[2][i] = f.getName();
	        if(new ExplorerRelation().getRelation(f)!=null) {
	        	data[3][i]=new ExplorerRelation().getRelation(f);
	        }
	    }
	return data;
	}
	/*public List<String> GetMethods(Class<?> c) {
		List<String> data =  new ArrayList<>();
		Method methods[] = c.getDeclaredMethods();
		for (Method m : methods) {
			int modifiers = m.getModifiers();
			data.add(Modifier.toString(modifiers));
			data.add(m.getReturnType().getSimpleName());
			data.add(m.getName());
		}
		return data;
        
	}*/
	public String[][] getMethods(Class<?> c) {
	    Method[] methods = c.getDeclaredMethods();
	    int nbrMethods = methods.length;
	    String[][] data = new String[3][nbrMethods];

	    for (int i = 0; i < nbrMethods; i++) {
	        Method m = methods[i];
	        int modifiers = m.getModifiers();
	        data[0][i] = Modifier.toString(modifiers);
	        data[1][i] = m.getReturnType().getSimpleName();
	        data[2][i] = m.getName();
	    }
		return data;
        
	}
	public static void main(String[] args) {
		Class<?> c = Etudiant.class;
		ExplrerClasses e = new ExplrerClasses();
		/*for (String s : e.GetMethods(c)) {	
			System.out.println(s);
		}*/
		
		 for (int i = 0; i < e.getFields(c).length; i++) {
		        String modifier = e.getFields(c)[0][i];
		        String type = e.getFields(c)[1][i];
		        String name = e.getFields(c)[2][i];

		        System.out.println(modifier + "\t" + type + "\t" + name);
		    }
		 
		 for (int i = 0; i < e.getMethods(c).length; i++) {
		        String modifier = e.getMethods(c)[0][i];
		        String returnType = e.getMethods(c)[1][i];
		        String methodName = e.getMethods(c)[2][i];

		        System.out.println(modifier + "\t" + returnType + "\t" + methodName);
		    }
	}

}
