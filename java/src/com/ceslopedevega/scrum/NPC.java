package com.ceslopedevega.scrum;

import java.util.*;

public class NPC extends Character{
	private static final int DEFAULT_LOYALTY = 0;

	private int loyalty;
	//TODO añadir más atributos pertinentes a NPCs
	
	public NPC() {
		super();
		this.loyalty = DEFAULT_LOYALTY;
	}
	
	public NPC ( String name, Room room, int loyalty ) {
		super( name, room );
		this.loyalty = loyalty;
	}
	
	public NPC ( String name, int AD, int HP, int charisma, int money, String faction, Room room, int loyalty) {
		super( name, AD, HP, charisma, money, faction, room );
		this.loyalty = loyalty;
	}
	
	
	
}
