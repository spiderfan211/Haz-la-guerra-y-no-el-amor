package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;
import java.util.*;

public abstract class Character implements Serializable{
	
	private static final long serialVersionUID = 3696731703160360752L;



	private static final String DEFAULT_NAME = "Jeff";

	
	
	private String name;
	private Stats stats;
	private Room room;
	private ArrayList<Item> items;
	//TODO a�adir m�s atributos 
	
	
	
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

	public Character( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room) {
		
		this.name = name;
		this.stats = new Stats( AD, HP, maxHP, defense, charisma, money, faction );
		this.room = room;
		this.items = new ArrayList<Item>();
	}
	
	public Character( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room, ArrayList<Item> items) {
		
		this.name = name;
		this.stats = new Stats( AD, HP, maxHP, defense, charisma, money, faction );
		this.room = room;
		this.items = items;
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

	

	public void describeRoom() {
		this.room.describeRoom();
	}	
	
	public boolean isDead() {
		return (this.stats.getHP()<=0);
	}
	
	public void addMoney(int money) {
		this.getStats().addMoney(money);
	}
	
	public String showStats() {
		return this.stats.showStats();
	}
	
	public String showItems() {
		String temp = "";
		for(int i=0; i < this.items.size(); i++) {
			temp = (temp + this.items.get(i).showItem());
		}
		if (temp == "") temp = "\nEl inventario est� vac�o";
		return temp;
	}
	
}
