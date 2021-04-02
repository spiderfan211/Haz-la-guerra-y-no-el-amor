package com.ceslopedevega.scrum;

public class NPC extends Character{
	private static final int DEFAULT_LOYALTY = 0;

	private int loyalty;
	
	public NPC() {
		this ( DEFAULT_LOYALTY );
	}
	
	public NPC ( int loyalty ) {
		super();	// TODO poner parametros al constructor
		this.loyalty = loyalty;
	}
	
}
