package com.ceslopedevega.scrum;

import java.util.*;

public abstract class Character {
	
	private static final String DEFAULT_NAME = "Jeff";

	protected enum Direction {	//preferiría encapsular Direction en una clase aparte pero no lo veo claro
	    NORTH, 
	    SOUTH, 
	    EAST, 
	    WEST;
		
	    public static final int NOEXIT = -1;
	}	
	
	
	private String name;
	private Stats stats;
	private Room room;
	private ArrayList<Item> items;
	//TODO añadir más atributos 
	
	
	
	public Character() {
		name = DEFAULT_NAME;
		stats = new Stats();
		room = new Room();
		items = new ArrayList<Item>();
	}
	
	public Character ( String name, Room room ) {
		
		this.name = name;
		this.room = room;
		this.stats = new Stats();
		this.items = new ArrayList<Item>();
		
	}

	public Character( String name, int AD, int HP, int charisma, int money, String faction, Room room) {
		
		this.name = name;
		this.stats = new Stats( AD, HP, charisma, money, faction );
		this.room = room;
		this.items = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
	
	
	
	
	
}
