package com.ceslopedevega.scrum;

import java.util.*;


public class Loop {
	
	/**
     * ****** THE MAP ******* 
     *  Para tener una referencia, esto es un ejemplo 
     *  de cómo estarían conectadas las habitaciones:
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
		
		// --- Construcción del mapa ---
        // --- Room( name, description,N, S, W, E )
        map.add(new Room("room0", 0,  "Te encuentras en la calle, ves una casa blanca al este y una dorada al sur", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        map.add(new Room("room1", 1, "Te encuentras en la casa blanca. Al sur tienes la salida y no se ve ninguna puerta más", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        map.add(new Room("room2", 2, "Estás en la casa dorada. Ves la vuelta a la calle al norte, una habitación de trolls al este y una cueva oscura al sur", 0, 4, Direction.NOEXIT, 3));
        map.add(new Room("room3", 3, "Estás en la habitación de los trolls. Al oeste ves la tenue luz de la habitación dorada. No hay más salidas", Direction.NOEXIT, 5, 2, Direction.NOEXIT));
        map.add(new Room("room4", 4, "Has llegado a la cueva oscura. No ves nada, pero sabes que has venido desde el norte, e intuyes que hay una puerta al este", 2, Direction.NOEXIT, Direction.NOEXIT, 5));
        map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", 3, Direction.NOEXIT, 4, Direction.NOEXIT));
        
        // Crear jugador y posicionarlo en la habitación inicial ( la 0 )
        Player player = new Player( "Ganon", map.get(0) );
		
        /*
         * Concepto inicial de loop jugable:
         * El jugador comienza en la sala de inicio.
         * Cada vez que se cambie de sala O haga una acción
         * (P.ej: hablar con un NPC, abrir un cofre, coger un objeto),
         * se volverá a mostrar la descripción de la sala.
         * 
         */
        
        int input = -1;
        
        do {
        	/* Idea para el bucle: un switch 
        	 * dependiendo del índice del arraylist 
        	 * map donde esté el jugador, saca por pantalla 
        	 * la descripción de la sala, seguido 
        	 * de un mensaje genérico de petición de 
        	 * acción por parte del jugador*/
        	if(input != 1) {    		
        		System.out.println( "\n" + player.getRoom().getDescription());
			}
        	System.out.println("\n¿Qué quieres hacer?"
        			+ "\n\t1) Describir la estancia"
        			+ "\n\t2) Desplazarse en una dirección"
        			+ "\n\t3) Terminar el juego");
        	/*
        	 * Para la pregunta de ingreso por consola
        	 * se podría preguntar qué se desea hacer,
        	 * seguido de opciones que se valorarían 
        	 * con un switch, como:
        	 *  - Entablar conversación con un NPC
        	 *  - Recoger un item
        	 *  - Entrar en combate
        	 *  - Pasar por una puerta
        	 * Las opciones disponibles deberán depender
        	 * de lo que contenga la habitación
        	 * (si tiene un enemigo, un cofre y x salidas, 
        	 * tendrá más opciones que si solo tiene salidas y ya está)
        	 * */
        	try {
        	input = scan.nextInt();
        	}catch(InputMismatchException e) {
        		System.out.println("ERROR: Tipo erróneo");
        	}
        	scan.nextLine();
        	switch( input ) {
        	case 1:
        		player.getRoom().describeRoom();
        		break;
        	case 2:
        		System.out.println("Introduzca North, West, South o East para avanzar\n");
        		String dir = scan.nextLine();
        		if( dir.equalsIgnoreCase(Direction.SOUTH.name())|| dir.equalsIgnoreCase(Direction.NORTH.name()) || dir.equalsIgnoreCase(Direction.WEST.name()) || dir.equalsIgnoreCase(Direction.EAST.name())) {
	        		
	        		if( player.moveInDirection(dir, map) == Direction.NOEXIT ) {
	        			System.out.println("No puedes avanzar en esa dirección");
	        		}
	        		else {
	        			
	        		}
        		}
        		else {
        			System.out.println("ERROR. Introduzca una dirección válida");
        		}
        		break;
        	case 3:
        		System.out.println("Terminando el juego");
        		break;
        	default:
        		System.out.println("Introduzca un input válido");
        		break;
        	}
        	
        }while( input != 3 );
        
        System.out.println("\n\tThe end \n\tGRACIAS POR JUGAR");
        
        
	}
	
	
}
