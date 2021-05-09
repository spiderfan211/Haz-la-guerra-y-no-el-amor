package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;

public class Stats implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1077801330251971755L;
	private static final int DEFAULT_AD = 1;
	private static final int DEFAULT_HP = 1;
	private static final int DEFAULT_DEFENSE = 1;
	private static final int DEFAULT_CHARISMA = 1;
	private static final int DEFAULT_MONEY = 1;
	private static final String DEFAULT_FACTION = "NONE";



	//AD es el daño del ataque básico del personaje
	//HP son los puntos de vida del personaje
	//charisma es la variable que usaremos para las interacciones de no-combate con otros personajes
	//money es el dinero acumulado en el inventario del personaje
	
	private int AD;
	private int HP;
	private int defense;
	private int charisma;
	private int money;
	private Faction faction;
	//TODO añadir stats según las necesitemos
	
	public Stats() {
		this ( DEFAULT_AD, DEFAULT_HP, DEFAULT_DEFENSE, DEFAULT_CHARISMA, DEFAULT_MONEY, DEFAULT_FACTION );
	}
	
	public Stats ( int AD, int HP, int defense, int charisma, int money, String faction ) {
		this.AD = AD;
		this.HP = HP;
		this.defense= defense;
		this.charisma = charisma;
		this.money = money;
		this.faction = Faction.valueOf(faction);
	}
	
	
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAD() {
		return AD;
	}
	public void setAD(int aD) {
		AD = aD;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int HP) {
		this.HP = HP;
	}
	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public int getCharisma() {
		return charisma;
	}
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void addMoney(int money) {
		this.money = this.money + money;
	}

	public void showStats() {
		System.out.println("\nPuntos de vida: " + this.HP
				+ "\nPuntos de ataque: " + this.AD
				+ "\nPuntos de defensa: " + this.defense
				+ "\nPuntos de carisma: " + this.charisma
				+ "\nMonedas de oro: " + this.money
				+ "\nFacción: " + this.faction);
	}
	
	
}
