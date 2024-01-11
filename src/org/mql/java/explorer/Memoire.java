package org.mql.java.explorer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mql.java.annotation.isObject;
import org.mql.java.models.Etudiant;

	public class Memoire {
	
		public Memoire() {
			
		}
		//// Explore classes in the specified package
		public List<String> explorerClass(String packageName) {
			List<String> li = new ArrayList<>();
			String classpath= System.getProperty("java.class.path");
			String packageFolder = packageName.replace('.', '\\');
			//System.out.println(classpath + "\\" + packageFolder);
			File dir = new File(classpath + "\\" + packageFolder);
			File classes[] = dir.listFiles(); //recuperer la liste de classe du package "packageName"
			 if (classes != null) {
			for (File f : classes) {
				li.add(packageName+ "." +  f.getName().replace(".class", ""));
				//System.out.println("-" + packageName+ "." +  f.getName().replace(".class", ""));
			}
			 }
			return li;
			
		}
		 /*
		  * Explore the package of a given class
		  * */
		 
		 public String explorerPackage(String qname) {
			 Package classPackage=null;
		    	//Set<String> packags = new HashSet<>();
		        try {
					Class<?> cls =  Class.forName(qname);
					 classPackage = cls.getPackage();	
				} catch (ClassNotFoundException e ) {
					
					System.out.println("<<Classe introuvable "+ qname);
				}catch (Exception e) {
					System.out.println("Error : " + e.getMessage());
				}
		        
		        
	
		        if (classPackage != null) {
		            return classPackage.getName();
		        } else {
		            return "Unknown Package";
		        }
		  }
		 //Parse package names based on the project path
		 public  Set<String> parserPath(String path) {
			    Set<String> packags = new HashSet<>();
		        File folder = new File(path);
	
		        if (folder.exists() && folder.isDirectory()) {
		        	packags.addAll(parserFolder(folder,path+"\\bin\\"));
		        } else {
		            System.out.println("The specified folder does not exist or is not a folder");
		        }
		        
		        return packags;
		    }
	//
		    private  Set<String> parserFolder(File folder, String startedPath) {
		    	//startedPath+="\\bin";
		    	Set<String> packags = new HashSet<>();
		        File[] files = folder.listFiles();
	
		        if (files != null) {
		            for (File file : files) {
		                if (file.isDirectory()) {
		                	packags.addAll(parserFolder(file, startedPath));
		                    //parserFolder(file,startedPath);
		                } else {
		                    if (file.getName().endsWith(".class")) {
		                       // System.out.println("Fichier .class trouvé : " + file.getAbsolutePath());
		                    	String path = file.getAbsolutePath().replace("\\", ".");
		                    	if (path.endsWith(".class")) {
		                    		path = path.substring(0, path.length() - 6);
		                        }
		                    	//path.replaceFirst("C:.MQL.Hassib Hassnae-UML.", " ");
		                    	startedPath=startedPath.replace("\\", ".");
		                    	path=removePrefix(path, startedPath);
		                    	
		                        //System.out.println(path);
		                    	String pack = explorerPackage(path);
		                    	//System.out.println(explorerPackage(path));
		                        packags.add(pack);
		                        
		                    }
		                }
		            }
		            
		        }
		        return packags;
		    }
		    //Helper method to remove a specified prefix from a string
		    public  String removePrefix(String s, String prefix) {
		        if (s.startsWith(prefix)) {
		            return s.substring(prefix.length());
		        } else {
		            return s;
		        }
		    }
		    public void relation(String qname) {
		    	String parent = "";
				try {
					Class<?> c = Class.forName(qname);
					Field field[] = c.getDeclaredFields();
					 for (Field f : field) {
						 isObject ob = f.getDeclaredAnnotation(isObject.class);
						if(ob!=null) {
							//System.out.println(f.getType().getSimpleName());
			                System.out.println(c.getSimpleName() + " has a/an " + f.getType().getSimpleName() + " field: " + f.getName());
						}
					}
					 parent = c.getSuperclass().getSimpleName();
					 if(parent != null) {
						 System.out.println(parent + "  is a parent of : " + c.getSimpleName());
					 }
				} catch (Exception e) {
					System.out.println("Error : "+ e.getMessage());
				}
				 
		    }
	

}
