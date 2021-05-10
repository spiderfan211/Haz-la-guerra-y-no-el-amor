package com.ceslopedevega.scrum.modelo;

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
	private static final int DEFAULT_E = Direction.NOEXIT;
	private static final NPC DEFAULT_ENEMY = new NPC();


	private String name;
	private int index;
	private String description;
	private int n, s, w, e;
	private ArrayList<Item> items;
	private NPC enemy;
	
	public Room () {
        this( DEFAULT_NAME, DEFAULT_INDEX, DEFAULT_DESCRIPTION, DEFAULT_N, DEFAULT_S, DEFAULT_W, DEFAULT_E, DEFAULT_ENEMY);
	}
	
	public Room(String name, int index, String description, int n, int s, int w, int e, NPC enemy) {
        this.name = name;
        this.index = index;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
        this.items = new ArrayList<Item>();
        this.enemy = enemy;
    }
	
	public Room(String name, int index, String description, int n, int s, int w, int e, NPC enemy, ArrayList<Item> items) {
        this.name = name;
        this.index = index;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
        this.items = items;
        this.enemy = enemy;
    }
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}

	public NPC getEnemy() {
		return enemy;
	}

	public void setEnemy(NPC enemy) {
		this.enemy = enemy;
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
		System.out.println("\n" + this.description);
		System.out.println(this.describeSalidas());
		System.out.println(this.describeItems());
		System.out.println(this.describeNPC());
		//más descripciones
		
	}
	
	private String describeNPC() {
		try {
			return this.enemy.toString();
		}catch(NullPointerException e) {
			return "";
		}
	}

	
	
	private String describeItems() {
		String temp = "";
		for(int i=0; i < this.items.size(); i++) {
			temp = (temp + this.items.get(i).showItem());
		}
		return temp;
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
			return "La estancia no tiene salidas";	/*realmente esto no ocurrirá nunca porque siempre tienen al menos una salida, 
																	pero por si implementáramos en algún momento algo que cierre las salidas 
																	temporalmente de la sala*/
		}
	}
	
	public boolean atLeastOneItem() {
		return !(this.items.isEmpty());
	}
	
	public ArrayList<Item> takeItems() {
		ArrayList<Item> temp = new ArrayList<>();
		
		for(int i = 0; i < this.items.size(); i++) {
			temp.add(items.get(i));
		}
		
		this.items.clear();

		return temp;
	}

	public void options(Player player) {
		System.out.println("\n¿Qué quieres hacer?");
		System.out.println("\n\t1) Describir la estancia");
		System.out.println("\n\t2) Mostrar las estadísticas del jugador");
		System.out.println("\n\t3) Mostrar los objetos del inventario del jugador");
		System.out.println("\n\t4) Desplazarse en una dirección");
		if(!this.items.isEmpty()){
			System.out.println("\n\t5) Coger los objetos de la habitación");
		}
		if(this.enemy!=null){
			System.out.println("\n\t6) Pelear con el enemigo de la sala");
		}
		System.out.println("\n\t7) Usar un objeto de tu inventario");
		System.out.println("\n\t9) Guardar la partida");
		
		System.out.println("\n\t10) Terminar el juego");
		if(this.enemy!=null){
			System.out.println("\n¡La batalla se acerca! Las salidas permanecerán cerradas mientras el enemigo siga con vida. ¡Destrúyelo!");
		}
	}
	
	public void enemyDeath(Player player) {
		int money = this.enemy.getStats().getMoney();
		ArrayList<Item> items = this.enemy.getItems();
		this.enemy = null;
		player.addMoney(money);
		player.addItems(items);
		
	}
	
	public void addMonster(NPC enemy) {
		this.enemy = enemy;
	}
	
	public boolean closedRoom() {
		return (this.enemy != null);
	}
	
}
