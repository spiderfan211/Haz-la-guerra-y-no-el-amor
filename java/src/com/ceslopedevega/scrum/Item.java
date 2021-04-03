package com.ceslopedevega.scrum;

public class Item {
	
	private static final String DEFAULT_NAME = "";
	private static final int DEFAULT_AMOUNT= 0;

	

	private String name;
	private int amount;
	//TODO añadir/modificar clase Stats para que los items tambien tengan sus stats
	
	public Item() {
		this( DEFAULT_NAME, DEFAULT_AMOUNT );
	}
	
	public Item( String name, int amount ) {
		this.name = name;
		this.amount = amount;
	}
	
	
	//TODO metodos para manejar items ( intercambiar, vender, comprar )
	
	
}
