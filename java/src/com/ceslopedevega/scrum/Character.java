package com.ceslopedevega.scrum;

public abstract class Character {
	
	private static final String DEFAULT_NAME = "Jeff";

	
	
	
	private String name;
	private Stats stats;
	private Room room;
	
	
	public Character() {
		name = DEFAULT_NAME;
		stats = new Stats();
		room = new Room();
	}

	public Character( String name, int AD, int HP, int charisma, int money, String faction, String room_name, String description, int n, int s, int w, int e) {
		
		this.name = name;
		stats = new Stats( AD, HP, charisma, money, faction );
		room = new Room ( room_name, description, n, s, w, e );
	}
	
	
}
