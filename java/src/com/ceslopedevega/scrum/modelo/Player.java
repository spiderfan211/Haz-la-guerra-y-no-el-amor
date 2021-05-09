package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable{

	//TODO definir características exclusivas de Player respecto a NPC, métodos de subir de nivel y tal
	//Si no encontramos una razón de ser para esta clase, debería fusionarse con NPC, 
	//quitarle la abstracción a Character.java, y eliminar la herencia

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
	
	public Player( String name, int AD, int HP, int defense, int charisma, int money, String faction, Room room) {
		super( name, AD, HP, defense, charisma, money, faction, room );
	}
	

	//Métodos de desplazamiento
	
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
            this.movePlayerTo(map, exit);	//si es una dirección válida, modifica la habitación actual
        }
        return exit;
    }
    
	public void takeItemsFromRoom() {
		if (this.getRoom().atLeastOneItem()) {
			this.setItems(this.getRoom().takeItems());
		}
		else {
			System.out.println("No hay objetos que coger en la habitación");
		}
	}

	public void options() {
		this.getRoom().options();
	}
	
	
	
	
	public int attack() {	//returns 0, if both contestants are still alive, -1 if the player has died, or 1 if the enemy is dead after the fight
		int herodmg = (super.getStats().getAD() - super.getRoom().getEnemy().getStats().getDefense());
		int enemydmg = (super.getRoom().getEnemy().getStats().getAD() - super.getStats().getDefense());
		super.getRoom().getEnemy().getStats().setHP(super.getRoom().getEnemy().getStats().getHP()- herodmg);
		super.getStats().setHP(super.getStats().getHP()-enemydmg);
		
		if(super.isDead()) {
			return -1;
		}
		else if(super.getRoom().getEnemy().isDead()) {
			return 1;
		}
		else return 0;
	}
	
	public String battleStatus() {
		return ("\nPlayer HP: " + super.getStats().getHP() + "\nEnemy HP: " + super.getRoom().getEnemy().getStats().getHP());
	}
	
	public void enemyDeath() {
		super.addMoney(super.getRoom().enemyDeath());
	}
	
	
	
}
