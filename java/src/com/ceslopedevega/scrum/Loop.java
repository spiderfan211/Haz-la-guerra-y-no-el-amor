package com.ceslopedevega.scrum;

import java.util.*;


public class Loop {
	
	private enum Direction {	//preferir�a encapsular Direction en una clase aparte pero no lo veo claro
	    NORTH, 
	    SOUTH, 
	    EAST, 
	    WEST;
		
	    public static final int NOEXIT = -1;
	}
	
	/**
     * ****** THE MAP ******* 
     *  Para tener una referencia, esto es un ejemplo 
     *  de c�mo estar�an conectadas las habitaciones:
     *
     * room0 -- room1 
     *   | 
     * room2 -- room3 
     *   |        |
     * room4 -- room5
     *
     ************************
     */
	private static ArrayList<Room> map;
	
	public static void play() {
		
		map = new ArrayList<Room>();
		
		// --- Construcci�n del mapa ---
        // --- Room( name, description,N, S, W, E )
        map.add(new Room("room0", "Te encuentras en la calle, ves una casa blanca al este y una dorada al sur", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        map.add(new Room("room1", "Te encuentras en la casa blanca. Al sur tienes la salida y no se ve ninguna puerta m�s", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        map.add(new Room("room2", "Est�s en la casa dorada. Ves la vuelta a la calle al norte, una habitaci�n de trolls al este y una cueva oscura al sur", 0, 4, Direction.NOEXIT, 3));
        map.add(new Room("room3", "Est�s en la habitaci�n de los trolls. Al oeste ves la tenue luz de la habitaci�n dorada. No hay m�s salidas", Direction.NOEXIT, 5, 2, Direction.NOEXIT));
        map.add(new Room("room4", "Has llegado a la cueva oscura. No ves nada, pero sabes que has venido desde el norte, e intuyes que hay una puerta al este", 2, Direction.NOEXIT, Direction.NOEXIT, 5));
        map.add(new Room("room5", "Has llegado a la sala final. Enhorabuena.", 3, Direction.NOEXIT, 4, Direction.NOEXIT));
        
        // Crear jugador y posicionarlo en la habitaci�n inicial ( la 0 )
        Player player = new Player( "Ganon", map.get(0) );
		
	}
	
	
}
