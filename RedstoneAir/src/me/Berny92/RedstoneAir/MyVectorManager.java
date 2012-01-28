package me.Berny92.RedstoneAir;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyVectorManager {
	public static RedstoneAir plugin;
	public Map<String, MyVector> myVectors;
	
	public MyVectorManager(RedstoneAir instance) {
		plugin = instance;
		myVectors=new HashMap<String, MyVector>();
		loadMyVectors();
	}
	
	public void saveMyVectors() {
		try {
			plugin.oio.write(myVectors, "plugins/RedstoneAir/myvectors.object");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMyVectors() {
		if(myVectorsExist())
			try {
				myVectors=(Map<String, MyVector>) plugin.oio.read("plugins/RedstoneAir/myvectors.object");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public boolean myVectorsExist() {
		File f=new File("plugins/RedstoneAir/myvectors.object");
		return f.exists();
	}
}
