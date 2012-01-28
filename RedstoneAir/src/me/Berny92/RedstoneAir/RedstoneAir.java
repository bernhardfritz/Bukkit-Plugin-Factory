package me.Berny92.RedstoneAir;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RedstoneAir extends JavaPlugin {

	public static RedstoneAir plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final SignListener signListener = new SignListener(this);
	public final RedstoneListener redstoneListener = new RedstoneListener(this);
	public DirectoryManager dm;
	public MyVectorManager mvm;
	public ObjectIO oio;
	public String name;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " version " + pdffile.getVersion() + " is now disabled.");
	}

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.SIGN_CHANGE, this.signListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, this.redstoneListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdffile = this.getDescription();
		name=pdffile.getName();
		dm=new DirectoryManager(name);
		oio=new ObjectIO();
		mvm=new MyVectorManager(this);
		this.logger.info(name + " version " + pdffile.getVersion() + " is now enabled.");
	}
	
}
