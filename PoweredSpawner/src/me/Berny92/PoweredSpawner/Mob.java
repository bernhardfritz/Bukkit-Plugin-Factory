package me.Berny92.PoweredSpawner;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;

public class Mob {
	private CreatureType type;
	public static final Logger logger = Logger.getLogger("Minecraft");
	private int extype;
	/*
	 * kompliziertere Mobs
	 * 1=Creeper
	 * 2=wolf
	 * 3=small
	 * 4=average
	 * 5=large
	 * 6=colossal
	 * 7=sheep
	 * 8=pigzombie
	 * 9=jockeys
	 * 10=c4yourself
	 * 11=riding NPC
	 */
	private String name;
	private boolean ex; // Gibt an das es sich um einen extended Mob handelt
	
	public Mob(String name){
		this.ex=false;
		this.extype=0;
		this.name = name.toLowerCase();
		handleMob();
	}
	
	// get the mob type
	private void handleMob(){
		if(name.equals("rainbow")){ Rainbow(); return; }
		// not - supported: if(name.equals("monster")){ type= CreatureType.MONSTER; return; }
		if(name.contains("wolf")){ Wolf(); return; }
		if(name.equals("squid")){ type= CreatureType.SQUID; return; }
		if(name.equals("chicken")){ type = CreatureType.CHICKEN; return; }
		if(name.equals("cow")){ type= CreatureType.COW; return; }
		if(name.contains("creeper")){ Creeper(); return; }
		if(name.equals("pig")){ type= CreatureType.PIG; return; }
		if(name.equals("silverfish")){ type= CreatureType.SILVERFISH; return; }
		if(name.equals("skeleton")){ type= CreatureType.SKELETON; return; }
		if(name.equals("zombie")){ type= CreatureType.ZOMBIE; return; }
		if(name.equals("blaze")){ type= CreatureType.BLAZE; return; }
		if(name.contains("slime")){ Slime(); return; }
		if(name.contains("magma")){ Magma(); return; }
		if(name.equals("cave")){ type= CreatureType.CAVE_SPIDER; return; }
		if(name.equals("enderman")){ type= CreatureType.ENDERMAN; return; }
		if(name.equals("enderdragon")){ type= CreatureType.ENDER_DRAGON; return; }
		if(name.equals("npc")){ type= CreatureType.VILLAGER; return; }
		if(name.equals("ghast")){ type= CreatureType.GHAST; return; }
		if(name.equals("giant")){ type= CreatureType.GIANT; return; }
		if(name.equals("spider")){ type= CreatureType.SPIDER; return; }
		if(name.equals("mushroomcow")){ type= CreatureType.MUSHROOM_COW; return; }
		if(name.equals("pigzombie")){ PigZombie(); return; }
		if(name.equals("snowman")){ type= CreatureType.SNOWMAN; return; }
		if(name.contains("sheep")){ Sheep(); return; }
		if(name.equals("skeletonjockey")){ SkeletonJockey(); return; }
		if(name.equals("pigzomjockey")){ PigZombieJockey(); return; }
		if(name.equals("zombiejockey")){ ZombieJockey(); return; }
		if(name.equals("blazejockey")){ BlazeJockey(); return; }
		if(name.equals("ridingnpc")){ RideNPC(); return; }
	}
	
	// if the name contains creeper
	private void Creeper(){
		type= CreatureType.CREEPER;
		if(name.contains("charged")){
			ex=true;
			extype=1;
		}
	}
	
	// if the equals pigzombie
	private void PigZombie(){
		type= CreatureType.PIG_ZOMBIE;
		ex=true;
		extype=8;
	}
	
	// if the name equals skeletonjockey
	private void SkeletonJockey(){
		type= CreatureType.SKELETON;
		Jockey();
	}
	
	//if the name equals rainbow
	private void Rainbow(){
		type= CreatureType.SHEEP;
		ex=true;
		extype=10;
	}
	
	// if the name equals pigzombiejockey
	private void PigZombieJockey(){
		type = CreatureType.PIG_ZOMBIE;
		Jockey();
	}
	
	//if the name equals zombiejockey
	private void ZombieJockey(){
		type = CreatureType.ZOMBIE;
		Jockey();
	}
	
	// if the name equals blazejockey
	private void BlazeJockey(){
		type= CreatureType.BLAZE;
		Jockey();
	}
	
	// set extended mob = jockey
	private void Jockey(){
		ex=true;
		extype=9;
	}
	
	// if name contains wolf
	private void Wolf(){
		type= CreatureType.WOLF;
		if(name.contains("angry")){
			ex=true;
			extype=2;
		}
	}
	
	// if name contains slime
	private void Slime(){
		type= CreatureType.SLIME;
		size();
	}
	
	// if name contains magma
	private void Magma(){
		type= CreatureType.MAGMA_CUBE;
		size();
	}
	
	// if name equals ridingnpc
	private void RideNPC(){
		type= CreatureType.VILLAGER;
		ex=true;
		extype=11;
	}
	
	// get size for slime/magma
	private void size(){
		if(name.equals("slime")){ extype=0; return; }
		if(name.equals("magma")){ extype=0; return; }
		if(name.contains("small")){ extype=3; return; }
		if(name.contains("average")){ ex=true; extype=4; return; }
		if(name.contains("large")){ ex=true; extype=5; return; }
		if(name.contains("colossal")){ ex=true; extype=6; return; }
	}
	
	// if name contains sheep
	private void Sheep(){
		type= CreatureType.SHEEP;
		if(name.equals("sheep")){ 
			return; 
		}else{
			extype=7;
			ex=true;
			return;
		}
	}
	
	// spawn a new entity
	public LivingEntity spawn(Block block, Location loc) throws MobException{
		LivingEntity en;
		en = block.getWorld().spawnCreature(loc, type);
		if(en == null){
			logger.log(Level.WARNING, "unable to spawn mob!");
			throw new MobException();
		}
		return en;
	}
	
	public int getExtype(){
		return extype;
	}
	
	public boolean getExtended(){
		return ex;
	}
	
	public CreatureType getCreature(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
}
