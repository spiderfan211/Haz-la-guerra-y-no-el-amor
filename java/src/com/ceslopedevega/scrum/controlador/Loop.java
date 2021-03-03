package com.ceslopedevega.scrum.controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import com.ceslopedevega.scrum.modelo.Direction;
import com.ceslopedevega.scrum.modelo.Player;
import com.ceslopedevega.scrum.modelo.Room;


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
	private static Player player;
	
	public static void play() {
		
		map = new ArrayList<Room>();
		
		// --- Construcci�n del mapa ---
        // --- Room( name, description,N, S, W, E )
		/*
        map.add(new Room("room0", 0,  "Te encuentras en una plaza, vac�a y pavimentada", Direction.NOEXIT, 2, Direction.NOEXIT, 1));
        map.add(new Room("room1", 1, "Te encuentras en una casa blanca al este de la plaza.", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT));
        map.add(new Room("room2", 2, "Est�s en la casa dorada, al sur de la plaza.", 0, 4, Direction.NOEXIT, 3));
        map.add(new Room("room3", 3, "Est�s en la habitaci�n de los trolls, al este de la casa dorada.", Direction.NOEXIT, 5, 2, Direction.NOEXIT));
        map.add(new Room("room4", 4, "Has llegado a una cueva oscura. No ves casi nada, pero sabes que has venido desde el norte.", 2, Direction.NOEXIT, Direction.NOEXIT, 5));
        map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", 3, Direction.NOEXIT, 4, Direction.NOEXIT));
        */
        // Crear jugador y posicionarlo en la habitaci�n inicial ( la 0 )
        
		
        /*
         * Concepto inicial de loop jugable:
         * El jugador comienza en la sala de inicio.
         * Cada vez que se cambie de sala O haga una acci�n
         * (P.ej: hablar con un NPC, abrir un cofre, coger un objeto),
         * se volver� a mostrar la descripci�n de la sala.
         * 
         */
        
        /*TODO System.out.println("�Quiere cargar los datos de juego de otra partida?");
        System.out.println("Estos los archivos de guardado existentes: ");
        listofRecentFile();*/
        
        System.out.println("�Desea cargar una partida guardada? \n(Pulse 1 para cargar, cualquier otro n�mero para empezar un juego nuevo.)");
        int cargar = 0;
        try {
        	cargar = scan.nextInt();
        }catch(InputMismatchException e) {
        	System.out.println("\nValor no v�lido. No se cargar� la partida.");
        }finally {
        	scan.nextLine();
        }
        
        if(cargar==1) {
        	if(loadGame()) {
            	System.out.println("\nCargando partida guardada...");
        	}else {
        		System.out.println("\nError al cargar la partida");
        	}
        }
        else {
        	System.out.println("\nNo se cargar� la partida. Empezando aventura nueva...");
        }
        
        player = new Player( "Ganon", map.get(0) );
        
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
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado alg�n error.");
				}
        		break;
        	case 2:
        		player.showStats();
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado alg�n error.");
				}
        		break;
        	case 3:
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
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado alg�n error.");
				}
        		break;
        	case 4:
        		/*Coger objetos*/
        		break;
        	case 5:
        		int continuebattle;
        		System.out.println("\nComienza la batalla");
        		do{
        			continuebattle = player.attack();
        			System.out.println(player.battleStatus());
        		}while(continuebattle!=0);
        		
        		if(continuebattle == 1) {
        			player.enemyDeath();
        			System.out.println("\n�Enhorabuena! Has ganado la pelea.");
        		}
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado alg�n error.");
				}
        		break;
        	case 6:
        		saveGame();
        		break;
        	case 10:
        		System.out.println("Terminando el juego");
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado alg�n error.");
				}
        		break;
        	default:
        		System.out.println("Introduzca un input v�lido");
        		break;
        	}
        }while( input != 10 );
        
        System.out.println("\n\tThe end \n\tGRACIAS POR JUGAR");
        
        
	}

	
	public static void saveGame()
	{
        FileOutputStream file = null;
        ObjectOutputStream out = null;

        try 
        {
            // Se crea el fichero de salida
        	file = new FileOutputStream("./saves/gameplayer.dat");
            out = new ObjectOutputStream(file);
                        
            out.writeObject(player);
           
            System.out.println("Se han guardado los datos del personaje en gameplayer.dat\n");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("1"+e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("2"+e.getMessage());
        } 
        finally 
        {
            try 
            {
                if(file!=null) file.close();
                if(out!=null) out.close();
            } 
            catch (IOException e) 
            {
                System.out.println("3"+e.getMessage());
            }
        }
        
        FileOutputStream file2 = null;
        ObjectOutputStream out2 = null;

        try 
        {
            // Se crea el fichero de salida
        	file2 = new FileOutputStream("./saves/gamemap.dat");
            out2 = new ObjectOutputStream(file2);
                        
            for(Room r : map) {
            	out2.writeObject(r);
            }
           
            System.out.println("Se han guardado los datos del mapa en gamemap.dat\n");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("1"+e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("2"+e.getMessage());
        } 
        finally 
        {
            try 
            {
                if(file!=null) file.close();
                if(out!=null) out.close();
            } 
            catch (IOException e) 
            {
                System.out.println("3"+e.getMessage());
            }
        }
	}
	
	public static boolean loadGame()
	{
		FileInputStream file = null;
	    ObjectInputStream in = null;
	    boolean ok = true;
	    try 
	    {
            // Se crea el fichero de entrada
	    	file = new FileInputStream("./saves/gamemap.dat");
	        in = new ObjectInputStream(file);

            ArrayList<Room> rooms = new ArrayList<>();
            
            Room room = null;
            boolean eof = false;
            do { 
            	try {
            	room = (Room) in.readObject();
            	rooms.add(room);
            	}catch(EOFException e) {
            		eof = true;
            	}
            }while(!eof);
            
	        for (Room p : rooms)
	        	map.add(p);
	        
	        System.out.println("\nMapa cargado. \n");
	    } 
	    catch (FileNotFoundException e) 
	    {
	    	e.printStackTrace();
	    	ok = false;
	    	
	    }
	    catch (ClassNotFoundException e) 
	    {
	    	e.printStackTrace();
	    	ok = false;
	    } 
	    catch (IOException e)
	    {
	        e.printStackTrace();
	        ok = false;
	    } 
	    finally 
	    {
	    	try
	    	{
	    		if (file != null) 
	    		{
	    			file.close();
	            }
	            if (in != null) 
	            {
	            	in.close();
	            }
	        } 
	    	catch (IOException e)
	    	{
	    		e.printStackTrace();
	        }
	    }
	    
	    FileInputStream file2 = null;
	    ObjectInputStream in2 = null;
	    
	    try 
	    {
            // Se crea el fichero de entrada
	    	file2 = new FileInputStream("./saves/gameplayer.dat");
	        in2 = new ObjectInputStream(file2);

            player = (Player) in2.readObject();
            
            System.out.println("\nDatos del jugador cargados. \n");
	    } 
	    catch (FileNotFoundException e) 
	    {
	    	e.printStackTrace();
	    	ok = false;
	    }
	    catch (ClassNotFoundException e) 
	    {
	    	e.printStackTrace();
	    	ok = false;
	    } 
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	        ok = false;
	    } 
	    finally 
	    {
	    	try
	    	{
	    		if (file2 != null) 
	    		{
	    			file2.close();
	            }
	            if (in2 != null) 
	            {
	            	in2.close();
	            }
	        } 
	    	catch (IOException e)
	    	{
	    		e.printStackTrace();
	        }
	    }
	    return ok;
	}
	
}