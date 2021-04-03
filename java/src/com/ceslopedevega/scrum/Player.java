package com.ceslopedevega.scrum;

import java.util.ArrayList;

public class Player extends Character{

		//TODO definir características exclusivas de Player respecto a NPC, métodos de subir de nivel y tal
		//Si no encontramos una razón de ser para esta clase, debería fusionarse con NPC, 
		//quitarle la abstracción a Character.java, y eliminar la herencia
	
	public Player() {
		super();
	}
	
	public Player( String name, Room room) {
		super( name, room );
	}
	
	public Player( String name, int AD, int HP, int charisma, int money, String faction, Room room) {
		super( name, AD, HP, charisma, money, faction, room );
	}
	

	//Métodos de desplazamiento
	
	public void MoveTo( ArrayList<Room> rooms, int index ) {
		try {
			super.setRoom(rooms.get(index)) ;
		}catch(Exception e){
			System.out.println("Error accediendo a la nueva sala");
		};
	}
	
	
}
