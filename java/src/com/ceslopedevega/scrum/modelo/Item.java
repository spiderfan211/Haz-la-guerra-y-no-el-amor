package com.ceslopedevega.scrum.modelo;

import java.io.Serializable;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6349212670979354845L;
	private static final String DEFAULT_NAME = "";
	private static final int DEFAULT_AMOUNT= 0;

	

	private String name;
	private int amount;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean extraDefense;
	private boolean extraAttack;
	private boolean usable; //usable por ahora es solo para pociones -> implica que solo curan
	//TODO añadir/modificar clase Stats para que los items tambien tengan sus stats
	
	public Item() {
		this( DEFAULT_NAME, DEFAULT_AMOUNT );
	}
	
	public Item( String name, int amount ) {
		this.name = name;
		this.amount = amount;
		this.usable = false;
	}
	
	public Item( String name, int amount, boolean usable ) {
		this.name = name;
		this.amount = amount;
		this.usable = usable;
	}
	
	public Item( String name, int amount, boolean extraDefense, boolean extraAttack ) {
		this.name = name;
		this.amount = amount;
		this.usable = false;
		this.extraAttack = extraAttack;
		this.extraDefense = extraDefense;
	}
	
	public String showItem() {
		return (name + ": " + amount + " unidades |");
	}

	public boolean useItem(Player player) {	//por ahora, solo curar (pociones)
		//se ha comprobado ya que el objeto es usable
		this.amount -=1;
		player.potionHeal();
		if(this.amount>0)return true;
		else return false;
	}
	
	
	public boolean usableItem() {
		return this.usable;
	}
	
	public void upgradeStats(Player player) {
		if(this.extraAttack) {
			player.upgradeAttack();
		}
		if(this.extraDefense) {
			player.upgradeDefense();
		}
	}
	
}
