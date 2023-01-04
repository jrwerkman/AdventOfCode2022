package nl.jrwer.challenge.advent.day07;

import java.util.ArrayList;
import java.util.List;

class Directory extends Item {
	Directory parent = null;
	
	List<Directory> childDirectories = new ArrayList<>();
	List<File> childFiles = new ArrayList<>();
	
	public void addFileSize(File file) {
		size += file.size;
		
		if(parent != null) 
			parent.addFileSize(file);
	}

}