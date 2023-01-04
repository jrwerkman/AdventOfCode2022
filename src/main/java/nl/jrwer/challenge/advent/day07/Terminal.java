package nl.jrwer.challenge.advent.day07;

import java.util.ArrayList;
import java.util.List;

class Terminal {
	List<Directory> directories = new ArrayList<>();
	List<File> files = new ArrayList<>();
	
	Directory root = new Directory();
	Directory currentDir = root;
	
	public Terminal(List<String> input) {
		processInput(input);
	}
	
	private void processInput(List<String> input) {
		
		boolean isLs = false;
		
		for(String line : input) {
			if(line.startsWith("$")) {
				isLs = executeCommand(line);
			} else {
				if(isLs) {
					addItem(line);
				}
			}
		}
	}
	
	private boolean executeCommand(String line) {
		String command = line.substring(2);
		
		if(command.startsWith("cd")) {
			currentDir = executeCdCommand(command, currentDir);
			return false;
		} else if(command.startsWith("ls")) {
			return true;
		}
		
		return false;
	}
	
	private Directory executeCdCommand(String command, Directory currentDir) {
		String cdCommand = command.substring(3);
		
		if(cdCommand.equals("/")) {
			return root;
		} else if(cdCommand.equals("..")) {
			return currentDir.parent;
		} else {
			for(Directory d : currentDir.childDirectories)
				if(d.name.equals(cdCommand))
					return d;
		}
		
		throw new RuntimeException("Directory not found: " + command);
	}
	
	private void addItem(String line) {
		String[] split = line.split(" ");
		
		if(split.length != 2)
			throw new RuntimeException("Expected 1 space in line: " + line);
		
		if(split[0].equals("dir")) {
			Directory dir = new Directory();
			dir.parent = currentDir;
			dir.name = split[1];
			
			currentDir.childDirectories.add(dir);
			directories.add(dir);
		} else {
			File file = new File();
			file.name = split[1];
			file.size = Integer.parseInt(split[0]);
			
			files.add(file);
			currentDir.childFiles.add(file);
			currentDir.addFileSize(file);
		}
	}
}