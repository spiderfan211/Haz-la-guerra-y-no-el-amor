package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;
import java.util.*;

public class NPC extends Character implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5882170143125291894L;

	private static final int DEFAULT_LOYALTY = 0;

	private int loyalty;
	
	public NPC() {
		super();
		this.loyalty = DEFAULT_LOYALTY;
	}
	
	public NPC ( String name, Room room, int loyalty ) {
		super( name, room );
		this.loyalty = loyalty;
	}
	
	public NPC ( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room, int loyalty) {
		super( name, AD, HP, maxHP, defense, charisma, money, faction, room );
		this.loyalty = loyalty;
	}
	
	public NPC ( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room, int loyalty, ArrayList<Item> items) {
		super( name, AD, HP, maxHP, defense, charisma, money, faction, room, items );
		this.loyalty = loyalty;
	}
	
	public void setRoom(Room room) {
		super.setRoom(room);
	}

	@Override
	public String toString() {
		return ("Hay un enemigo en la sala. Su nombre es " + this.getName() + ", y sus estadísticas, \n" + this.showStats());
	}
	
	
}
