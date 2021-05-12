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
	
	public Player( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room) {
		super( name, AD, HP, maxHP,  defense, charisma, money, faction, room );
	}
	
	public Player( String name, int AD, int HP, int maxHP, int defense, int charisma, int money, String faction, Room room, ArrayList<Item> items) {
		super( name, AD, HP, maxHP, defense, charisma, money, faction, room, items );
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
            case "NORTE":
                exit = room.getN();
                break;
            case "SUR":
                exit = room.getS();
                break;
            case "ESTE":
                exit = room.getE();
                break;
            case "OESTE":
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
		ArrayList<Item> temp = new ArrayList<>();
		if (this.getRoom().atLeastOneItem()) {
			temp = this.getRoom().takeItems();
			for(Item i: temp) {
				this.getItems().add(i);
				i.upgradeStats(this);
			}
			System.out.println("\n¡Has cogido los items del suelo! Han subido tus estadísticas");
		}
		else {
			System.out.println("No hay objetos que coger en la habitación");
		}
	}

	public void options() {
		this.getRoom().options(this);
	}
	
	
	
	
	public int attack() {	//returns 0, if both contestants are still alive, -1 if the player has died, or 1 if the enemy is dead after the fight
		int herodmg = ((super.getStats().getAD() - super.getRoom().getEnemy().getStats().getDefense()) * (int) (Math.random() * 5) + 1);
		int enemydmg = ((super.getRoom().getEnemy().getStats().getAD() - super.getStats().getDefense()) * (int) (Math.random() * 5) + 1);
		if(enemydmg<0) enemydmg=0;
		
		System.out.println("\n\tDaño al enemigo: " + herodmg + " puntos");
		System.out.println("\tDaño al jugador: " + enemydmg + " puntos");
		
		super.getRoom().getEnemy().getStats().setHP(super.getRoom().getEnemy().getStats().getHP()- herodmg);
		if(super.getRoom().getEnemy().getStats().getHP() < 0) {
			super.getRoom().getEnemy().getStats().setHP(0);
		}
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
		return ("\n\tVida del jugador: " + super.getStats().getHP() + "\n\tVida del enemigo: " + super.getRoom().getEnemy().getStats().getHP());
	}
	
	public void enemyDeath() {
		super.getRoom().enemyDeath(this);
	}

	public void addItems(ArrayList<Item> items) {
		for(Item i: items) {
			this.getItems().add(i);
			i.upgradeStats(this);
		}
		
	}
	
	
	public int showItemsForChoosing() {
		for(int i = 0; i < this.getItems().size(); i++) {
			System.out.println("\n" + i + ") " + this.getItems().get(i).showItem());
		}
		return this.getItems().size();
	}

	public void useItem(int i) {	//por ahora solo curar
		boolean stillare;
		if(this.getItems().get(i).usableItem()) {
			stillare = this.getItems().get(i).useItem(this);
			if (!stillare) {
				this.getItems().remove(i);
			}
		}
		else {
			System.out.println("\nEl objeto no es usable");
		}
	}
	
	public void potionHeal() {
		this.getStats().healHP();
		System.out.println("\n¡Te has curado!");
	}
	
	public void upgradeAttack() {
		this.getStats().setAD(this.getStats().getAD()+1);
	}
	
	public void upgradeDefense() {
		this.getStats().setDefense(this.getStats().getDefense()+1);
	}
	
	
}
