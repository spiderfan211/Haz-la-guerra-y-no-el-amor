package com.ceslopedevega.scrum;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1060844547780279432L;
	private static final String DEFAULT_NAME = "Sala inicial";
	private static final int DEFAULT_INDEX = 0;
	private static final String DEFAULT_DESCRIPTION = "Primera sala del juego";
	private static final int DEFAULT_N = Direction.NOEXIT;
	private static final int DEFAULT_S = Direction.NOEXIT;
	private static final int DEFAULT_W = Direction.NOEXIT;
	private static final int DEFAUL_E = Direction.NOEXIT;

	
	private String name;
	private int index;
	private String description;
	private int n, s, w, e;
	private ArrayList<Item> items;
	
	public Room () {
        this( DEFAULT_NAME, DEFAULT_INDEX, DEFAULT_DESCRIPTION, DEFAULT_N, DEFAULT_S, DEFAULT_W, DEFAUL_E);
	}
	
	public Room(String name, int index, String description, int n, int s, int w, int e) {
        this.name = name;
        this.index = index;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
        this.items = new ArrayList<Item>();
    }

    // norte
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n; 
    }

    // sur
    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    // este
    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    // oeste
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void describeRoom() {
		System.out.println(this.description);
		System.out.println(this.describeSalidas());
		System.out.println(this.describeItems());
		//m�s descripciones
		
	}
	
	private String describeItems() {
		String todos = "";
		for( Item i : items) {
			todos += i;
		}
		return todos;
	}

	private String describeSalidas() {
		boolean alguna_salida = false;
		String salida_norte = "";
		String salida_sur = "";
		String salida_oeste = "";
		String salida_este = "";

		if( this.n != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_norte = " una salida al norte";
		}
		if( this.s != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_sur = " una salida al sur";
		}
		if( this.w != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_oeste = " una salida al oeste";
		}
		if( this.e != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_este = " una salida al este";
		}
		
		
		if( alguna_salida == true ) {
			return "Ves" + salida_norte + salida_sur + salida_oeste + salida_este ;
			/*Hay que implementar mejor el string para que se vea mejor redactado, con comas y tal*/
		}
		else {
			return "La estancia no tiene salidas";	/*realmente esto no ocurrir� nunca porque siempre tienen al menos una salida, 
																	pero por si implement�ramos en alg�n momento algo que cierre las salidas 
																	temporalmente de la sala*/
		}
	}
	
	public boolean atLeastOneItem() {
		return !(this.items.isEmpty());
	}
	
	public ArrayList<Item> takeItems() {
		ArrayList<Item> temp = this.items;
		this.items.clear();
		return temp;
	}

	public void options() {
		int option = 3;
		System.out.println("\n�Qu� quieres hacer?");
		System.out.println("\n\t1) Describir la estancia");
		System.out.println("\n\t2) Desplazarse en una direcci�n");
		if(!this.items.isEmpty()){
			System.out.println("\n\t" + option + ") Coger los objetos de la habitaci�n");
			option++;
		}
		System.out.println("\n\t" + option +  ") Terminar el juego");
	}
	
}
