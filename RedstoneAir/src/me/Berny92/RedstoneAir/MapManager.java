package me.Berny92.RedstoneAir;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.util.Vector;

public class MapManager {
	public static RedstoneAir plugin;
	public Map<String, Vector> map=new HashMap<String, Vector>();
	
	public MapManager(RedstoneAir instance) {
		plugin = instance;
		loadMap();
		//plugin.getServer().broadcastMessage(""+map.containsKey("abc"));
	}
	
	public void saveMap() {
		try {
			plugin.oio.write((Object)map, "plugins/RedstoneAir/map.object");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		if(configExists())
			try {
				map=(Map<String, Vector>) plugin.oio.read("plugins/RedstoneAir/map.object");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public boolean configExists() {
		File f=new File("plugins/RedstoneAir/map.object");
		return f.exists();
	}
}
