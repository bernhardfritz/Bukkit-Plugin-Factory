package me.Berny92.PoweredSpawner;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class SignListener extends BlockListener{
	public static PoweredSpawner plugin;
	public SpawnMob sm;
	
	public SignListener(PoweredSpawner instance) {
		plugin = instance;
	}
	
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		if(event.getBlock().getType()==Material.WALL_SIGN && event.getNewCurrent()>0 && event.getNewCurrent()<15) {
			// For testing purposes only - plugin.getServer().broadcastMessage("BlockPower: "+event.getNewCurrent());
			plugin.logger.log(Level.INFO,"BlockPower: "+event.getNewCurrent());
			Sign s = (Sign)event.getBlock().getState();
			String lines[]=s.getLines();
			if(lines.length>0 && lines[0].length()>0 && lines[0].equals("[ps]")) {
				sm=new SpawnMob(lines[1], event.getBlock());
				sm.newMobs(lines[2]);
			}
		}
	}
}

