package com.nequissimus.library.file;

import java.io.File;


// Create folders, check for folders, get current path
public class FileFolderOperations {
	
	private boolean DEBUG;
	
	public FileFolderOperations(boolean debugMode){DEBUG = debugMode;}
	
	public boolean createDirectory(String name) throws Exception {
		
		File dir = new File(".");
		try {
			
			File newDir = new File(dir.getCanonicalPath() + File.separator + name);
			if (!newDir.exists()) {
				
				newDir.mkdirs();
				return true;
				
			} else return false;
			
		} catch (Exception e) {
			
			if (DEBUG)
				System.out.println("FileFolderOps: " + e.getMessage());
			return false;
			
		}
		
	}
	
	public boolean createDirectory(String name,String path) throws Exception {
		
		try {
			
			File newDir = new File(path + name);
			if (!newDir.exists()) {
				
				newDir.exists();
				return true;
				
			} else return false;
			
		} catch (Exception e) {
			
			if (DEBUG)
				System.out.println(e.getMessage());
			return false;
			
		}
		
	}
	
	public String getCurrentPath() {
		
		try {
			
			File currDir = new File(".");
			return currDir.getCanonicalPath() + File.separator;
			
		} catch (Exception e) {
			
			if (DEBUG)
				System.out.println(e.getMessage());
			return null;
			
		}
		
	}

}
