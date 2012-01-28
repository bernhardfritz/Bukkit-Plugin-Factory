package me.Berny92.RedstoneAir;

import java.io.File;

public class DirectoryManager {
	
	public String name;
	
	public DirectoryManager(String name) {
		this.name=name;
		check();
	}
	
	public void check() {
		if(!directoryExists()) {
			File f = new File("plugins/"+name);
		    f.mkdir();
		}
	}
	
	public boolean directoryExists() {
		File f = new File("plugins/"+name);
		return f.exists();
	}
}
