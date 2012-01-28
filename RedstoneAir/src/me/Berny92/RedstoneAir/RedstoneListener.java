package me.Berny92.RedstoneAir;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RedstoneListener extends BlockListener{
	
	public static RedstoneAir plugin;
	
	public RedstoneListener(RedstoneAir instance) {
		plugin = instance;
	}
	
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		if(event.getBlock().getType()==Material.SIGN_POST && (event.getBlock().isBlockPowered()||event.getBlock().isBlockIndirectlyPowered())) {
			Sign s = (Sign)event.getBlock().getState();
			String lines[]=s.getLines();
			if(lines.length>2 && lines[0].length()>0 && lines[1].length()>0 && lines[2].length()>0 && lines[0].equals("[ra]") && lines[1].equals("input") && plugin.mvm.myVectors.containsKey(lines[2])) {
				String o=lines[2];
				Location output=plugin.mvm.myVectors.get(o).getVector().toLocation(event.getBlock().getWorld());
				output.getBlock().setType(Material.REDSTONE_TORCH_ON);
			}
		}
		if(event.getBlock().getType()==Material.SIGN_POST && (!event.getBlock().isBlockPowered()||!event.getBlock().isBlockIndirectlyPowered())) {
			Sign s = (Sign)event.getBlock().getState();
			String lines[]=s.getLines();
			if(lines.length>2 && lines[0].length()>0 && lines[1].length()>0 && lines[2].length()>0 && lines[0].equals("[ra]") && lines[1].equals("input") && plugin.mvm.myVectors.containsKey(lines[2])) {
				String o=lines[2];
				Location output=plugin.mvm.myVectors.get(o).getVector().toLocation(event.getBlock().getWorld());
				output.getBlock().setType(Material.SIGN_POST);
				Sign temp = (Sign)output.getBlock().getState();
				temp.setLine(0,"[ra]");
				temp.setLine(1,"output");
				temp.setLine(2,o);
			}
		}
	}
}
