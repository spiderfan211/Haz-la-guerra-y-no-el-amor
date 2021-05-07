package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable{

	//TODO definir caracter�sticas exclusivas de Player respecto a NPC, m�todos de subir de nivel y tal
	//Si no encontramos una raz�n de ser para esta clase, deber�a fusionarse con NPC, 
	//quitarle la abstracci�n a Character.java, y eliminar la herencia

	/**
	 * 
	 */
	private static final long serialVersionUID = 5563218870108425511L;

	public Player() {
		super();
	}
	
	public Player( String name, Room room) {
		super( name, room );
	}
	
	public Player( String name, int AD, int HP, int charisma, int money, String faction, Room room) {
		super( name, AD, HP, charisma, money, faction, room );
	}
	

	//M�todos de desplazamiento
	
	public void movePlayerTo( ArrayList<Room> rooms, int index ) {
		try {
			super.setRoom(rooms.get(index)) ;
		}catch(Exception e){
			System.out.println("Error accediendo a la nueva sala");
		};
	}
	
	// mover un personaje en la direccion dir
    public int moveInDirection(String dir, ArrayList<Room> map) {
        // return: Constant representing the room number moved to
        // or NOEXIT
        //
        // try to move any Person (typically but not necessarily player)
        // in direction indicated by dir
        Room room = super.getRoom();
        int exit;
        dir = dir.toUpperCase();
        switch (dir) {
            case "NORTH":
                exit = room.getN();
                break;
            case "SOUTH":
                exit = room.getS();
                break;
            case "EAST":
                exit = room.getE();
                break;
            case "WEST":
                exit = room.getW();
                break;
            default:
                exit = Direction.NOEXIT; // noexit - stay in same room
                break;
        }
        if (exit != Direction.NOEXIT) {
            this.movePlayerTo(map, exit);	//si es una direcci�n v�lida, modifica la habitaci�n actual
        }
        return exit;
    }
    
	public void takeItemsFromRoom() {
		if (this.getRoom().atLeastOneItem()) {
			this.setItems(this.getRoom().takeItems());
		}
		else {
			System.out.println("No hay objetos que coger en la habitaci�n");
		}
	}

	public void options() {
		this.getRoom().options();
	}
	
}
