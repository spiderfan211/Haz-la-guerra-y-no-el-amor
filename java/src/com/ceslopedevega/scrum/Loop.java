package com.ceslopedevega.scrum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        map.add(new Room("room0", 0,  "Te encuentras en una plaza, vac�a y pavimentada", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        map.add(new Room("room1", 1, "Te encuentras en una casa blanca al este de la plaza.", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        map.add(new Room("room2", 2, "Est�s en la casa dorada, al sur de la plaza.", 0, 4, Direction.NOEXIT, 3));
        map.add(new Room("room3", 3, "Est�s en la habitaci�n de los trolls, al este de la casa dorada.", Direction.NOEXIT, 5, 2, Direction.NOEXIT));
        map.add(new Room("room4", 4, "Has llegado a una cueva oscura. No ves casi nada, pero sabes que has venido desde el norte.", 2, Direction.NOEXIT, Direction.NOEXIT, 5));
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
        
        System.out.println("�Quiere cargar los datos de juego de otra partida?");
        System.out.println("Estos los archivos de guardado existentes: ");
        listofRecentFile();
        
        int input = -1;
        
        do {
        	
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
        	
        	if(input != 1) {    		
        		player.describeRoom();
			}
        	
        	try {
	        	player.options();
        	
        		input = scan.nextInt();
	        	scan.nextLine();
	        	
        	}catch(InputMismatchException e) {
        		System.out.println("ERROR: Tipo err�neo");
        		scan.nextLine();
        	}
        	switch( input ) {
        	case 1:
        		player.describeRoom();
        		break;
        	case 2:
        		System.out.println("Introduzca North, West, South o East para avanzar\n");
        		String dir = scan.nextLine();
        		if( dir.equalsIgnoreCase(Direction.SOUTH.name())|| dir.equalsIgnoreCase(Direction.NORTH.name()) || dir.equalsIgnoreCase(Direction.WEST.name()) || dir.equalsIgnoreCase(Direction.EAST.name())) {
	        		
	        		if( player.moveInDirection(dir, map) == Direction.NOEXIT ) {
	        			System.out.println("No puedes avanzar en esa direcci�n");
	        		}
	        		else {
	        			
	        		}
        		}
        		else {
        			System.out.println("ERROR. Introduzca una direcci�n v�lida");
        		}
        		break;
        	case 3:
        		System.out.println("Terminando el juego");
        		break;
        	default:
        		System.out.println("Introduzca un input v�lido");
        		break;
        	}
        }while( input != 3 );
        
        System.out.println("\n\tThe end \n\tGRACIAS POR JUGAR");
        
        
	}

	
	private static void listofRecentFile() {

        try (Stream<Path> walk = Files.walk(Paths.get("..\\..\\..\\..\\..\\saves"))) {

            List<Path> result = walk.filter(f -> f.toString().endsWith(".dat")).collect(Collectors.toList());

            result.forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
}
