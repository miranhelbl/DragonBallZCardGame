package com.example.dragonballzcardgame.data;

public class Karte {
	private String filename;
	private String name;
	private int ki;
	private int force;
	private int intelligence;
	private int ability;
	
	public Karte(String filename, String name, int ki, int force,
			int intelligence, int ability)
	{
		super();
		this.filename = filename;
		this.name = name;
		this.ki = ki;
		this.force = force;
		this.intelligence = intelligence;
		this.ability = ability;
	}
	
	public String getFileName()
	{
		return filename;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getKi()
	{
		return ki;
	}
	
	public int getForce()
	{
		return force;
	}
	
	public int getIntelligence()
	{
		return intelligence;
	}
	
	public int getAbility()
	{
		return ability;
	}
	
	@Override
	public String toString() {
		return "Karte [filename=" + filename + ", name="
				+ name + ", ki=" + ki + ", force=" + force + ", intelligence=" 
				+ intelligence + ", ability=" + ability + "]";
	}
}
