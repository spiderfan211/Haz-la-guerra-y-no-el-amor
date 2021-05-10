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
	
	
	private static ArrayList<Room> map;
	private static Scanner scan = new Scanner(System.in);
	private static Player player;
	
	public static void play() {
		
		map = new ArrayList<Room>();
        /*
         * Concepto inicial de loop jugable:
         * El jugador comienza en la sala de inicio.
         * Cada vez que se cambie de sala O haga una acción
         * (P.ej: hablar con un NPC, abrir un cofre, coger un objeto),
         * se volverá a mostrar la descripción de la sala.
         * 
         */
        
        System.out.println("¿Desea cargar una partida guardada? \n(Pulse 1 para cargar, cualquier otro número para empezar un juego nuevo.)");
        int cargar = 0;
        try {
        	cargar = scan.nextInt();
        }catch(InputMismatchException e) {
        	System.out.println("\nValor no válido. No se cargará la partida.");
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
        	/**
             * ****** THE MAP ******* 
             *  Para tener una referencia, esto es un ejemplo 
             *  de cómo estarían conectadas las habitaciones (por defecto):
             *
             * room0 -- room1 
             *   | 
             * room2 -- room3 
             *   |        |
             * room4 -- room5
             *
             ************************
             */
        	System.out.println("\nNo se cargará la partida. Empezando aventura nueva con el mapa por defecto...");
        	
        	map.add(new Room("room0", 0,  "Te encuentras en una plaza, vacía y pavimentada", Direction.NOEXIT, 2, Direction.NOEXIT, 1, null));
            map.add(new Room("room1", 1, "Te encuentras en una casa blanca al este de la plaza.", Direction.NOEXIT, Direction.NOEXIT, 0, Direction.NOEXIT, null));
            map.add(new Room("room2", 2, "Estás en la casa dorada, al sur de la plaza.", 0, 4, Direction.NOEXIT, 3, null));
            map.add(new Room("room3", 3, "Estás en la habitación de los trolls, al este de la casa dorada.", Direction.NOEXIT, 5, 2, Direction.NOEXIT, null));
            map.add(new Room("room4", 4, "Has llegado a una cueva oscura. No ves casi nada, pero sabes que has venido desde el norte.", 2, Direction.NOEXIT, Direction.NOEXIT, 5, null));
            map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", 3, Direction.NOEXIT, 4, Direction.NOEXIT, null));
            player = new Player( "Ganon", map.get(0) );
        }
        
        
        
        int input = -1;
        
        do {
        	if(input != 1) {    		
        		player.describeRoom();
			}
        	
        	try {
	        	player.options();
        	
        		input = scan.nextInt();
	        	scan.nextLine();
	        	
        	}catch(InputMismatchException e) {
        		System.out.println("ERROR: Tipo erróneo");
        		scan.nextLine();
        	}
        	switch( input ) {
        	case 1:
        		player.describeRoom();
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado algún error.");
				}
        		break;
        	case 2:
        		System.out.println(player.showStats());
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado algún error.");
				}
        		break;
        	case 3:
        		advance();
        		break;
        	case 4:
        		/*Coger objetos*/
        		break;
        	case 5:
        		battle();
        		break;
        	case 9:
        		saveGame();
        		break;
        	case 10:
        		System.out.println("Terminando el juego");
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("La espera de 1 s ha dado algún error.");
				}
        		break;
        	default:
        		System.out.println("Introduzca un input válido");
        		break;
        	}
        }while( input != 10 );
        
        System.out.println("\n\tThe end \n\tGRACIAS POR JUGAR");
        
        
	}

	
	private static void battle() {
		int continuebattle;
		System.out.println("\nComienza la batalla");
		
		do{
			System.out.println(player.battleStatus());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("La espera de 1 s ha dado algún error.");
			}
			continuebattle = player.attack();
			System.out.println(player.battleStatus());
			
		}while(continuebattle==0);
		
		if(continuebattle == 1) {
			player.enemyDeath();
			System.out.println("\n¡Enhorabuena! Has ganado la pelea.");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("La espera de 1 s ha dado algún error.");
		}
		//TODO añadir manejo de muerte del jugador
		
	}


	private static void advance() {
		System.out.println("Introduzca norte, sur, este u oeste para avanzar\n");
		String dir = scan.nextLine();
		if( dir.equalsIgnoreCase(Direction.SUR.name())|| dir.equalsIgnoreCase(Direction.NORTE.name()) || dir.equalsIgnoreCase(Direction.OESTE.name()) || dir.equalsIgnoreCase(Direction.ESTE.name())) {
    		
    		if( player.moveInDirection(dir, map) == Direction.NOEXIT ) {
    			System.out.println("No puedes avanzar en esa dirección");
    		}
    		else {
    			
    		}
		}
		else {
			System.out.println("ERROR. Introduzca una dirección válida");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("La espera de 1 s ha dado algún error.");
		}
		
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
