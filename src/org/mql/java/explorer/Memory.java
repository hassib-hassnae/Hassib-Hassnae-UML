package org.mql.java.explorer;

import java.util.List;
import java.util.Set;

public class Memory {
	
	private Set<String> packages;
    private ExplorerPackages explorerPackages;
    private ExplorerClasses explorerClasses;
    
    public Memory(String source) {
        packages = new ExplorerProject().scanProject(source);
        explorerPackages = new ExplorerPackages();
        explorerClasses = new ExplorerClasses();
    }
	
    
    public Set<String> getPackages() {
        return packages;
    }

    public List<Class<?>> getClasses(String packageName, String classPath) {
        return explorerPackages.scan(packageName, classPath);
    }

    public String[][] getFields(Class<?> classe) {
        return explorerClasses.getFields(classe);
    }

    public String[][] getMethods(Class<?> classe) {
        return explorerClasses.getMethods(classe);
    }
    
    public static void main(String[] args) {
			Memory memory  = new Memory("C:\\MQL\\Hassib Hassnae-UML");
			Set<String> packages = memory.getPackages();
	        System.out.println("Packages: " + packages);
	}

}
