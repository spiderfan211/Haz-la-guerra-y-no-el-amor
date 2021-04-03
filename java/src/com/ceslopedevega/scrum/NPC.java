package com.ceslopedevega.scrum;

import java.util.*;

public class NPC extends Character{
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
	
}
