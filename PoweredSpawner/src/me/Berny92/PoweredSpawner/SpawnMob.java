package me.Berny92.PoweredSpawner;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;


public class SpawnMob {
	private Mob mob;
	private Location loc;
	private Block block;
	public static final Logger logger = Logger.getLogger("Minecraft");
	
	private String color[] =
		{
			"black", "blue", "brown", "cyan", "gray", "green",
			"lightblue", "lime", "magenta", "orange", "pink",
			"purple", "red", "silver", "white", "yellow"
		};
	
	public SpawnMob(String name, Block block){
		mob = new Mob(name);
		this.block=block;
		this.loc=block.getLocation();
		loc.setY(loc.getY()+2);
	}
	
	// handle creeper
	private LivingEntity newCreeper() throws MobException{
		Creeper cr = (Creeper) mob.spawn(block, loc);
		block.getWorld().strikeLightningEffect(loc);
		cr.setPowered(true);
		return cr;
	}
	
	// handle pigzombie
	private LivingEntity newPZombie() throws MobException{
		PigZombie pz = (PigZombie) mob.spawn(block, loc);
		pz.setAngry(true);
		return pz;
	}
	
	// handle wolf
	private LivingEntity newWolf() throws MobException{
		Wolf w = (Wolf) mob.spawn(block, loc);
		w.setAngry(true);
		return w;
	}
	
	// handle size - small
	private LivingEntity smallSlime() throws MobException{
		if(mob.getCreature() == CreatureType.SLIME){
			Slime s = (Slime) mob.spawn(block, loc);
			s.setSize(3);
			return s;
		}else{
			MagmaCube mc = (MagmaCube) mob.spawn(block, loc);
			mc.setSize(3);
			return mc;
		}
	}
	
	// handle size - average
	private LivingEntity averageSlime() throws MobException{
		if(mob.getCreature() == CreatureType.SLIME){
			Slime s = (Slime) mob.spawn(block, loc);
			s.setSize(6);
			return s;
		}else{
			MagmaCube mc = (MagmaCube) mob.spawn(block, loc);
			mc.setSize(6);
			return mc;
		}
	}
	
	// handle size - large
	private LivingEntity largeSlime() throws MobException{
		if(mob.getCreature() == CreatureType.SLIME){
			Slime s = (Slime) mob.spawn(block, loc);
			s.setSize(11);
			return s;
		}else{
			MagmaCube mc = (MagmaCube) mob.spawn(block, loc);
			mc.setSize(11);
			return mc;
		}
	}
	
	// handle size - colossal
	private LivingEntity colossalSlime() throws MobException{
		if(mob.getCreature() == CreatureType.SLIME){
			Slime s = (Slime) mob.spawn(block, loc);
			s.setSize(16);
			return s;
		}else{
			MagmaCube mc = (MagmaCube) mob.spawn(block, loc);
			mc.setSize(16);
			return mc;
		}
	}
	
	// handle sheep - dyed
	private LivingEntity dyedSheep() throws MobException{
		String dye = dye(mob.getName());
		Sheep sh = null;
		if(dye != null){
			sh= (Sheep) mob.spawn(block, loc);
			sh.setColor(DyeColor.valueOf(dye.toUpperCase()));
		}
		return sh;
	}
	
	// handle Jockey
	private void newJockey() throws MobException{
		LivingEntity rider = mob.spawn(block, loc);
		Spider mount = (Spider) mount("spider");
		mount.setPassenger(rider);
	}
	
	// handle Rainbow
	private void Rainbow() throws MobException{
		for(DyeColor c : DyeColor.values()){
			Sheep sh = (Sheep) mob.spawn(block, loc);
			sh.setColor(c);
		}
	}
	
	// handle riding NPC
	private void NPC() throws MobException{
		LivingEntity rider = mob.spawn(block, loc);
		Pig mount = (Pig) mount("pig");
		mount.setSaddle(true);
		mount.setPassenger(rider);
	}
	
	// handle spawn mount
	private LivingEntity mount(String moun) throws MobException{
		Mob mount = new Mob(moun);
		Location tc = loc;
		tc.setX(tc.getX()+1);
		LivingEntity mountt = mount.spawn(block, tc);
		return mountt;
	}
	
	// get dye
	private String dye(String sheep){
		String dye=null;
		for(String c : color){
			if(sheep.contains(c)){
				dye = c;
			}
		}
		return dye;
	}
	
	// internal - spawn 1 mob
	private LivingEntity newMob(){
		LivingEntity ent;
		try{
			if(!mob.getExtended()){
				ent = mob.spawn(block, loc);
				return ent;
			}else{
				switch(mob.getExtype()){
				case 1:
					ent=newCreeper();
					break;
				case 2:
					ent=newWolf();
					break;
				case 3:
					ent=smallSlime();
					break;
				case 4:
					ent=averageSlime();
					break;
				case 5:
					ent=largeSlime();
					break;
				case 6:
					ent=colossalSlime();
					break;
				case 7:
					ent=dyedSheep();
					break;
				case 8:
					ent=newPZombie();
					break;
				case 9:
					ent=null;
					newJockey();
					break;
				case 10:
					ent=null;
					Rainbow();
					break;
				case 11:
					ent=null;
					NPC();
					break;
				default:
					ent=null;
					break;
				}
				return ent;
			}
		}catch(Exception ex){
			logger.log(Level.WARNING, "unable to spawn mob!");
			ent=null;
			return ent;
		}
	}
	
	// spawn specified number of mobs
	public void newMobs(String anz){
		int anzahl = 1;
		if(anz.length()>0){
			try{
				anzahl = Integer.parseInt(anz);
			}catch(NumberFormatException nfe){
				anzahl = 1;
			}
			if((anzahl <1) || (anzahl >10)){
				anzahl = 1;
			}
		}
		if(mob.getName().equals("rainbow")){
			anzahl =1;
		}
		for(int i=0; i<anzahl; i++){
			newMob();
		}
	}
}
