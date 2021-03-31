package com.ceslopedevega.scrum;

public class Character {
	
	private static final String DEFAULT_NAME = "Jeff";

	
	
	
	private String name;
	private Stats stats;
	private Room room;
	
	
	public Character() {
		name = DEFAULT_NAME;
		stats = new Stats();
		room = new Room();
	}
	
	
}
