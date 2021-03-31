package com.ceslopedevega.scrum;

public class Stats {
	
	private static final int DEFAULT_AD = 1;
	private static final int DEFAULT_HP = 1;
	private static final int DEFAULT_CHARISMA = 1;
	private static final int DEFAULT_MONEY = 1;


	//AD es el daño del ataque básico del personaje
	//HP son los puntos de vida del personaje
	//charisma es la variable que usaremos para las interacciones de no-combate con otros personajes
	//money es el dinero acumulado en el inventario del personaje
	
	private int AD;
	private int HP;
	private int charisma;
	private int money;
	
	public Stats() {
		AD = DEFAULT_AD;
		HP = DEFAULT_HP;
		charisma = DEFAULT_CHARISMA;
		money = DEFAULT_MONEY;
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
	
	
	
	
}
