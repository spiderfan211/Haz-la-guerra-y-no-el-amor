package com.ceslopedevega.scrum;

import java.util.*;


public class Loop {
	
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
	private static Scanner scan = new Scanner(System.in);
	
	public static void play() {
		
		map = new ArrayList<Room>();
		
		// --- Construcci�n del mapa ---
        // --- Room( name, description,N, S, W, E )
        map.add(new Room("room0", 0,  "Te encuentras en la calle, ves una casa blanca al este y una dorada al sur", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        map.add(new Room("room1", 1, "Te encuentras en la casa blanca. Al sur tienes la salida y no se ve ninguna puerta m�s", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        map.add(new Room("room2", 2, "Est�s en la casa dorada. Ves la vuelta a la calle al norte, una habitaci�n de trolls al este y una cueva oscura al sur", 0, 4, Direction.NOEXIT, 3));
        map.add(new Room("room3", 3, "Est�s en la habitaci�n de los trolls. Al oeste ves la tenue luz de la habitaci�n dorada. No hay m�s salidas", Direction.NOEXIT, 5, 2, Direction.NOEXIT));
        map.add(new Room("room4", 4, "Has llegado a la cueva oscura. No ves nada, pero sabes que has venido desde el norte, e intuyes que hay una puerta al este", 2, Direction.NOEXIT, Direction.NOEXIT, 5));
        map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", 3, Direction.NOEXIT, 4, Direction.NOEXIT));
        
        // Crear jugador y posicionarlo en la habitaci�n inicial ( la 0 )
        Player player = new Player( "Ganon", map.get(0) );
		
        /*
         * Concepto inicial de loop jugable:
         * El jugador comienza en la sala de inicio.
         * Cada vez que se cambie de sala O haga una acci�n
         * (P.ej: hablar con un NPC, abrir un cofre, coger un objeto),
         * se volver� a mostrar la descripci�n de la sala.
         * 
         */
        
        String input;
        
        do {
        	/* Idea para el bucle: un switch 
        	 * dependiendo del �ndice del arraylist 
        	 * map donde est� el jugador, saca por pantalla 
        	 * la descripci�n de la sala, seguido 
        	 * de un mensaje gen�rico de petici�n de 
        	 * acci�n por parte del jugador*/
        	System.out.println( "\n" + player.getRoom().getDescription());
        	System.out.println("\nOpcion:");
        	/*
        	 * Para la pregunta de ingreso por consola
        	 * se podr�a preguntar qu� se desea hacer,
        	 * seguido de opciones que se valorar�an 
        	 * con un switch, como:
        	 *  - Entablar conversaci�n con un NPC
        	 *  - Recoger un item
        	 *  - Entrar en combate
        	 *  - Pasar por una puerta
        	 * Las opciones disponibles deber�n depender
        	 * de lo que contenga la habitaci�n
        	 * (si tiene un enemigo, un cofre y x salidas, 
        	 * tendr� m�s opciones que si solo tiene salidas y ya est�)
        	 * */
        	input = scan.nextLine().toLowerCase();
        	switch( input ) {
        	case "nombre":
        		System.out.println("El nombre de la sala es " + player.getRoom().getName());
        		break;
        	case "mover":
        		System.out.println("Habitaci�n actual: " + player.getRoom().getIndex());
        		String dir = scan.nextLine();	//faltar�an filtros
        		
        		System.out.println("Resultado de moveInDirection: " + player.moveInDirection(dir.toUpperCase(), map));
        		
        		
        		break;
        	case "terminar":
        		break;
        	default:
        		System.out.println("Introduzca un input v�lido");
        		break;
        	}
        	
        }while( input.equalsIgnoreCase("terminar") == false );
        
        System.out.println("\n\tThe end \n\tGRACIAS POR JUGAR");
        
        
	}
	
	
}
