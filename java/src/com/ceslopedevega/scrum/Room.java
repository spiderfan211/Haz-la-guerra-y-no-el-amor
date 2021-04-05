package com.ceslopedevega.scrum;

import com.ceslopedevega.scrum.Character.Direction;

public class Room {
	

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
		//más descripciones
		
	}
	
	private String describeSalidas() {
		boolean alguna_salida = false;
		String salida_norte = "";
		String salida_sur = "";
		String salida_oeste = "";
		String salida_este = "";

		if( this.n != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_norte = "una salida al norte ";
		}
		if( this.n != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_sur = " una salida al sur ";
		}
		if( this.n != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_oeste = " una salida al oeste ";
		}
		if( this.n != Direction.NOEXIT ) {
			alguna_salida = true;
			salida_este = " una salida al este ";
		}
		
		
		if( alguna_salida == true ) {
			return "Ves " + salida_norte + salida_sur + salida_oeste + salida_este ;
			/*Hay que implementar mejor el string para que se vea mejor redactado, con comas y tal*/
		}
		else {
			return "La estancia no tiene salidas";	/*realmente esto no ocurrirá nunca porque siempre tienen al menos una salida, 
																	pero por si implementáramos en algún momento algo que cierre las salidas 
																	temporalmente de la sala*/
		}
	}
	
	
}
