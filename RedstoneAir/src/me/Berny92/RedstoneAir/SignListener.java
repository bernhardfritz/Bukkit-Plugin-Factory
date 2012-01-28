package me.Berny92.RedstoneAir;

import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener extends BlockListener{
	
	public static RedstoneAir plugin;
	
	public SignListener(RedstoneAir instance) {
		plugin = instance;
	}
	
	public void onSignChange(SignChangeEvent event) {
		String lines[]=event.getLines();
		if(lines.length>2 && lines[0].length()>0 && lines[1].length()>0 && lines[2].length()>0 && lines[0].equals("[ra]") && lines[1].equals("output") && !plugin.mvm.myVectors.containsKey(lines[2])) {
			for(int i=0; i<lines.length; i++) {
				plugin.getServer().broadcastMessage(lines[i]);	
			}
			plugin.mvm.myVectors.put(lines[2], new MyVector(lines[2], event.getBlock().getLocation().toVector().serialize()));
			plugin.mvm.saveMyVectors();
		}
	}
}

