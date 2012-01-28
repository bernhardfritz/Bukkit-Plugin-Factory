package me.Berny92.RedstoneAir;

import java.io.Serializable;
import java.util.Map;

import org.bukkit.util.Vector;

public class MyVector implements Serializable{
	private static final long serialVersionUID = 8877789035120892822L;
	public String name;
	public Map<String, Object> vs;
	
	public MyVector(String name, Map<String, Object> vs) {
		this.name = name;
		this.vs = vs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getVs() {
		return vs;
	}
	public void setVs(Map<String, Object> vs) {
		this.vs = vs;
	}
	public Vector getVector() {
		return Vector.deserialize(vs);
	}	
}
